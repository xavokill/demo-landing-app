import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { UserDataModel } from '../../models/user/user-data.model';
import { ProspectService } from '../../services/prospect.service';
import { TokenResponse } from '../../models/token/token.response';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';


@Component({
	selector: 'ngbd-modal-content',
	standalone: true,
	template: `
		<div class="modal-header">
			<h4 class="modal-title" id="modal-title">C&Oacute;DIGO CORRECTO</h4>
			<button
				type="button"
				class="btn-close"
				aria-label="Close button"
				aria-describedby="modal-title"
			></button>
		</div>
		<div class="modal-body">
			<p>
				<strong>¿Deseas continuar tu registro en el sistema?</strong>
			</p>
			<p>
				<span class="text-warning">Recuerda que esta operaci&oacute;n no puede ser cancelada.</span>
			</p>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-outline-secondary"  (click)="notifyAnswer(false)">Cancel</button>
			<button type="button" ngbAutofocus class="btn btn-danger" (click)="notifyAnswer(true)">Ok</button>
		</div>
	`,
})
export class NgbdModalContent {
  @Output() userAnswer: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(public activeModal: NgbActiveModal) {    
  }

  notifyAnswer(answer: boolean){
    this.userAnswer?.emit(answer);
    this.activeModal.close();
  }
}

@Component({
  selector: 'app-step-three',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './step-three.component.html',
  styleUrl: './step-three.component.css',
})
export class StepThreeComponent implements OnInit, OnDestroy {
  // Atributes
  private modalRef: NgbModalRef | undefined;

  private userDataSubscription: Subscription | null;
  private employeeSubscription: Subscription | null;
  
  private employeeId: string;
  private userData: UserDataModel;
  
  public tokenLength: number;
  public tokenForm!: FormGroup;

  constructor(
    private router: Router,
    private employeeDataService: EmployeeService,
    private userDataService: UserService,
    private prospectService: ProspectService,
    private modalService: NgbModal
  ) {
    this.employeeSubscription = null;
    this.userDataSubscription = null;
    
    this.employeeId = '';
    this.userData = new UserDataModel();
  
    this.tokenLength = 6;

    this.tokenForm = new FormGroup({
      token: new FormControl("", [Validators.required, Validators.minLength(this.tokenLength), Validators.maxLength(this.tokenLength)]),
    });    
  
  }

  ngOnInit(): void {
    if (this.employeeSubscription == null) {
      this.employeeSubscription = this.employeeDataService
        .getEmployeeId()
        .subscribe((employeeId) => {
          this.employeeId = employeeId;
        });     
    }

    if(this.userDataSubscription == null){
      this.userDataSubscription = this.userDataService
        .getUserData()
        .subscribe((userData: UserDataModel | null) => {
          if(userData === null){
            this.router.navigate(["/home", btoa(this.employeeId)]);
          }
          else{
            this.userData = userData;
          }
        });
    }
  }

  ngOnDestroy(): void {
    if (this.employeeSubscription) {
      this.employeeSubscription.unsubscribe();
      this.employeeSubscription = null;
    }

    if(this.userDataSubscription){
      this.userDataSubscription.unsubscribe();
      this.userDataSubscription = null;
    }    
  }

  goBack() {
    this.router.navigate(['/step-two']);
  }

  validateStepThree() {
    this.prospectService
    .validateToken(this.userData.Curp, this.employeeId, this.tokenForm.controls["token"].value)
    .subscribe({
      next: (response: TokenResponse) => {
        if(response.resultado){
          this.openAlert();
        }
        this.tokenForm.reset();
      }
    });    
  }

  openAlert() {		
    this.modalRef = this.modalService.open(NgbdModalContent);
    this.modalRef.componentInstance.userAnswer.subscribe((userSayYes: boolean) => {
      if(userSayYes){
        this.prospectService.registerProspectRequest(this.userData, this.employeeId)
        .subscribe(
          { 
            next: (response) => {
              console.log(response);              
              this.router.navigate(['/step-four'])
            },
            error: (error) => {
              console.log(error)
              // this.openAlert();
            }
          });
      }
      this.tokenForm.reset();
    });
	}
}