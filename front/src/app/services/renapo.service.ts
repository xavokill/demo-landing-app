import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { RenapoData } from '../models/renapo/renapo-data.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class RenapoService {

  constructor(private http: HttpClient) {}

  validateCurp(curp: string): Observable<RenapoData>{
    const url = `${environment.POSTMAN_SERVER}/curp/validaCurp?curp=${curp}`
    // One way to set http headers
    return this.http.get<RenapoData>(url, { headers: new HttpHeaders({"x-api-key": `${environment.SERVER_KEY}`})});
  }
}
