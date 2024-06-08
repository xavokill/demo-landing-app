import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CurpComponent } from '../commons/input/curp/curp.component';
import { UserService } from '../../services/user.service';
import { UserDataModel } from '../../models/user/user-data.model';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-step-one',
  standalone: true,
  templateUrl: './step-one.component.html',
  styleUrl: './step-one.component.css',
  imports: [CommonModule, ReactiveFormsModule, CurpComponent],
})
export class StepOneComponent implements OnInit, OnDestroy {
  // Atributes
  private userDataSubscription: Subscription | null = null;
  private employeeDataSubscription: Subscription | null = null;
  private tempUserData!: UserDataModel;
  private employeeId: string = "";

  public cellPhonelLength: number;
  public userDataForm!: FormGroup;

  constructor(private router: Router, private userDataService: UserService, private employeeSevice: EmployeeService) {
    this.cellPhonelLength = 10;
    this.tempUserData = new UserDataModel();
  }

  // Life cycle hooks
  ngOnInit(): void {

    if(this.employeeDataSubscription == null){
      this.employeeDataSubscription = this.employeeSevice.getEmployeeId().subscribe((employeeId) => {
        this.employeeId = employeeId;
      });
    }

    if (this.userDataSubscription === null) {
      this.userDataSubscription = this.userDataService
        .getUserData()
        .subscribe((userData) => {
          if (userData === null) {
            userData = new UserDataModel();
            this.router.navigate(["/home", btoa(this.employeeId)]);
          }
          else{
            this.tempUserData = userData;                  
          }          

          this.initForm(userData);
        });
    }
  }

  ngOnDestroy(): void {

    if (this.userDataSubscription) {
      this.userDataSubscription.unsubscribe();
      this.userDataSubscription = null;
    }

    if (this.employeeDataSubscription){
      this.employeeDataSubscription.unsubscribe();
      this.employeeDataSubscription = null;
    }

    if (this.userDataForm) {
      this.userDataForm.reset();
    }
  }

  goBack() {
    this.router.navigate(["/home", btoa(this.employeeId)]);
  }

  validateStepOne() {
    this.tempUserData.CellPhone = this.userDataForm.value.cellPhone;
    this.tempUserData.Email = this.userDataForm.value.email;
    this.userDataService.setUserData(this.tempUserData);    
    this.router.navigate(['/step-two']);    
  }

  formatCellPhone(cellPhoneValue: string){
    // Remueve todo aquello que no sea un número usando una expresión regular
    const onlyNumbersString = cellPhoneValue.replaceAll(/[^0-9.]/g, '')
    this.userDataForm.controls['cellPhone'].setValue(onlyNumbersString);
  }

  private initForm(userData: UserDataModel) {
    this.userDataForm = new FormGroup({
      fullName: new FormControl(userData.FullName, [Validators.required]),
      curp: new FormControl(userData.Curp, [
        Validators.required,
        Validators.minLength(18),
        Validators.maxLength(18),
      ]),
      email: new FormControl(userData.Email, [
        Validators.required,
        Validators.email,
      ]),
      cellPhone: new FormControl(userData.CellPhone, [
        Validators.required,
        Validators.minLength(this.cellPhonelLength),
        Validators.maxLength(this.cellPhonelLength),
      ]),
    });
  }
}
