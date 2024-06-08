import { Component, OnInit, OnDestroy } from '@angular/core';
import { CurpComponent } from '../commons/input/curp/curp.component';
import { CurpModel } from '../../models/curp/curp.model';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { RenapoService } from '../../services/renapo.service';
import { Subscription } from 'rxjs';
import { RenapoData } from '../../models/renapo/renapo-data.model';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserDataModel } from '../../models/user/user-data.model';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, CurpComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit, OnDestroy {
  // Attributes
  private renapoSubscription: Subscription | null;

  public curpModel: CurpModel;

  // Constructor
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private renapoService: RenapoService,
    private userDataService: UserService,
    private employeeDataService: EmployeeService
  ) {
    this.curpModel = new CurpModel();
    this.renapoSubscription = null;
  }

  // Life cycle hooks
  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      // This line of code suposes input "Id" parameter is a valid encoded B64 string.
      // TODO: Error handling for invalid B64 input strings
      const employeeId = atob(params.get('key')!);
      this.employeeDataService.setEmployeeId(employeeId);
    });
  }

  ngOnDestroy(): void {
    if (this.renapoSubscription !== null) {
      this.renapoSubscription.unsubscribe();
      this.renapoSubscription = null;
    }
  }

  // public methods
  public curpHandler(inputModel: CurpModel) {
    if (inputModel != null) {
      this.curpModel = inputModel;
    }
  }

  public validateCurp() {
    if (this.curpModel === null || !this.curpModel.IsValid) {
      return;
    }

    if (this.renapoSubscription === null) {
      this.renapoSubscription = this.renapoService
        .validateCurp(this.curpModel.Value)
        .subscribe({
          next: (renapoData: RenapoData) => {
            const userData = new UserDataModel();
            userData.AcceptedTerms = true;
            userData.FullName = `${renapoData?.renapo?.nombres} ${renapoData?.renapo?.apellidoPaterno} ${renapoData?.renapo?.apellidoMaterno}`;
            userData.Curp = renapoData.renapo?.curp
              ? String(renapoData.renapo?.curp)
              : '';

            this.userDataService.setRenapoData(renapoData);
            this.userDataService.setUserData(userData);
            this.router.navigate(['step-one']);
          },
          error: (e) => {
            console.error(e);
            this.curpModel.Value = '';
          },
        });
    }
  }
}
