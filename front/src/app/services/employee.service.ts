import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  // Attributes
  private _$employeeIdSubject: BehaviorSubject<string>;

  constructor() {
    this._$employeeIdSubject = new BehaviorSubject<string>("");
  }

  setEmployeeId(employeeId: string) {
    this._$employeeIdSubject.next(employeeId);
  }

  getEmployeeId() : Observable<string> {
    return this._$employeeIdSubject.asObservable();
  }
}
