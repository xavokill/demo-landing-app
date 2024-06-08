import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-step-four',
  standalone: true,
  imports: [],
  templateUrl: './step-four.component.html',
  styleUrl: './step-four.component.css'
})
export class StepFourComponent implements OnInit, OnDestroy{

  private employeeDataSubscription: Subscription | null = null;
  private employeeId: string = "";

  constructor(private router: Router, private employeeSevice: EmployeeService) {
  }

  ngOnInit(): void {
    if(this.employeeDataSubscription == null){
      this.employeeDataSubscription = this.employeeSevice.getEmployeeId().subscribe((employeeId) => {
        this.employeeId = employeeId;
      });
    }
  }

  ngOnDestroy(): void {
    if (this.employeeDataSubscription) {
      this.employeeDataSubscription.unsubscribe();
      this.employeeDataSubscription = null;
    }
  }

  finish(){
    this.router.navigate(["/home", btoa(this.employeeId)]);
  }
}
