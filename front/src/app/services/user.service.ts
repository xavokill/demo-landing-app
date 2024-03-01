import { Injectable } from '@angular/core';
import { RenapoData } from '../models/renapo/renapo-data.model';
import { Observable, BehaviorSubject, ReplaySubject, Subject } from 'rxjs';
import { UserDataModel } from '../models/user/user-data.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  // Attributes
  private _$renapoDataSubject: BehaviorSubject<RenapoData | null>;
  private _$userDataSubject: BehaviorSubject<UserDataModel | null>;

  constructor() {
    this._$renapoDataSubject = new BehaviorSubject<RenapoData | null>(null);
    this._$userDataSubject = new BehaviorSubject<UserDataModel | null>(null);
  }

  setUserData(userData: UserDataModel) {
    this._$userDataSubject.next(userData);
  }

  getUserData(): Observable<UserDataModel | null> {
    return this._$userDataSubject.asObservable();
  }

  setRenapoData(renapoData: RenapoData) {
    this._$renapoDataSubject.next(renapoData);
  }

  getRenapoData(): Observable<RenapoData | null> {
    return this._$renapoDataSubject.asObservable();
  }
}
