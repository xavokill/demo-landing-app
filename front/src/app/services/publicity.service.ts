import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { PublicityData } from '../models/publicity/publicity-data.model';

@Injectable({
  providedIn: 'root',
})
export class PublicityService {

  constructor(private http: HttpClient) {
  }

  getPublicity(): Observable<PublicityData> {
    const url = `${environment.POSTMAN_SERVER}/publicity/getLatest`;
    return this.http.get<PublicityData>(url, { headers: new HttpHeaders({"x-api-key": `${environment.SERVER_KEY}`}) });
  }
}
