import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenResponse } from '../models/token/token.response';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ProspectDataModel } from '../models/prospect/prospect-data.model';
import { UserDataModel } from '../models/user/user-data.model';

@Injectable({
  providedIn: 'root'
})
export class ProspectService {

  constructor(private http: HttpClient) {
  }

  sendToken(curp: string, contactValue: string, channelId: number, employeeId: string) : Observable<TokenResponse>{
    const url = `${environment.PROSPECT_SERVER}/prospectos/autenticacion/enviarCodigo`
    return this.http.post<TokenResponse>(url, {
      curp: curp,
      destinatario: contactValue,
      medioAutenticacion: channelId,
      numeroEmpleado: employeeId
    });
  }

  validateToken(curp: string, employeeId: string, token: string) : Observable<TokenResponse>{
    const url = `${environment.PROSPECT_SERVER}/prospectos/autenticacion/validarCodigo`
    return this.http.post<TokenResponse>(url, {
      curp: curp,
      numeroEmpleado: employeeId,
      codigo: token
    });
  }
  
  registerProspectRequest(userData: UserDataModel, userId: string) : Observable<ProspectDataModel> {
    const url = `${environment.PROSPECT_SERVER}/prospectos/prospectos/prospectos`;
    return this.http.post<ProspectDataModel>(url, {
      correoElectronicoProspecto: userData.Email,
      telefonoProspecto: userData.CellPhone,
      curpProspecto: userData.Curp,
      numeroEmpleadoAsesor: userId
    });
  }
}
