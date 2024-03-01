import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { Subscription } from 'rxjs';
import { UserDataModel } from '../../models/user/user-data.model';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ProspectService } from '../../services/prospect.service';

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
  private tempUserData: UserDataModel;

  public tokenMgtForm!: FormGroup;
  public cellPhoneChannelId: number = 1;
  public emailChannelId: number = 2;  

  constructor
  (
    private router: Router, 
    private userDataService: UserService,
    private prospectService: ProspectService
  ) 
  {
    this.userDataSubscription = null;
    this.tempUserData = new UserDataModel();
  }

  // Life cycle hooks
  ngOnInit(): void {    
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
      this.tokenMgtForm.value.channelId      
      )
    .subscribe({
      next: (tokenResponse) => {
        console.log(tokenResponse);
        this.router.navigate(["/step-three"]);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
}
