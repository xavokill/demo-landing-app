import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { Subscription } from 'rxjs';
import { UserDataModel } from '../../models/user/user-data.model';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ProspectService } from '../../services/prospect.service';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-step-two',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './step-two.component.html',
  styleUrl: './step-two.component.css'
})
export class StepTwoComponent implements OnInit, OnDestroy {
  // Atributes
  private userDataSubscription: Subscription | null;
  private employeeSubscription: Subscription | null;
  private tempUserData: UserDataModel;
  private employeeId: string;

  public tokenMgtForm!: FormGroup;
  public cellPhoneChannelId: number = 1;
  public emailChannelId: number = 2;  

  constructor
  (
    private router: Router, 
    private userDataService: UserService,
    private prospectService: ProspectService,
    private employeeDataService: EmployeeService
  ) 
  {
    this.userDataSubscription = null;
    this.employeeSubscription = null;

    this.tempUserData = new UserDataModel();
    this.employeeId = "";
  }

  // Life cycle hooks
  ngOnInit(): void {    

    if(this.employeeSubscription == null){
      this.employeeSubscription = this.employeeDataService.getEmployeeId().subscribe((employeeId) =>{
        this.employeeId = employeeId;
      });
    }

    if(this.userDataSubscription === null){
      this.userDataSubscription = this.userDataService.getUserData().subscribe((userData) => {
        if (userData === null) {
          userData = new UserDataModel();
          this.router.navigate(['/home']);
        }
        else{
          this.tempUserData = userData;
          this.tokenMgtForm = new FormGroup({
            channelId: new FormControl(this.cellPhoneChannelId),
            contactValue: new FormControl(this.tempUserData.CellPhone)
          });
        }       
      });
    }
  }

  ngOnDestroy(): void {
    if(this.userDataSubscription){
      this.userDataSubscription.unsubscribe();
      this.userDataSubscription = null;
    }

    if(this.employeeSubscription){
      this.employeeSubscription.unsubscribe();
      this.employeeSubscription = null;
    }
  }

  contactOptionChange(){
    var value = "";
    value = this.tokenMgtForm.value.channelId == this.cellPhoneChannelId 
    ? this.tempUserData.CellPhone : this.tempUserData.Email;
    this.tokenMgtForm.controls['contactValue'].setValue(value);    
  }

  goBack() {
    this.router.navigate(['/step-one']);
  }

  validateStepTwo() {

    this.prospectService.sendToken(
      this.tempUserData.Curp, 
      this.tokenMgtForm.value.contactValue,
      this.tokenMgtForm.value.channelId,
      this.employeeId      
      )
    .subscribe({
      next: (tokenResponse) => {
        this.router.navigate(["/step-three"]);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
}
