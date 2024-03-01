import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenResponse } from '../models/prospect/token.response';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProspectService {

  constructor(private http: HttpClient) {
  }

  sendToken(curp: string, contactValue: string, channelId: number) : Observable<TokenResponse>{
    const url = `${environment.PROSPECT_SERVER}/prospectos/autenticacion/enviarCodigo`
    return this.http.post<TokenResponse>(url, {
      curp: curp,
      destinatario: contactValue,
      medioAutenticacion: channelId,
      numeroEmpleado: environment.ASESOR_ID
    });
  }

}
