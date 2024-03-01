export class CurpModel {
  // Attributes
  private _value: string = '';
  private _length: number = 18;

  // Properties
  public set Value(v: string) {
    this._value = v?.toUpperCase();
  }
  
  public get Value() : string {
    return this._value;
  }

  public get Length(): number {
    return this._length;
  }
  public get IsValid(): boolean {
    return this.curpValida();
  }

  // Private Methods
  private curpValida(): boolean {
    var re =
        /^([A-Z][AEIOUX][A-Z]{2}\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\d])(\d)$/,
      validado = this._value.match(re);

    if (!validado)
      //Coincide con el formato general?
      return false;

    if (Number(validado[2]) != this.digitoVerificador(validado[1]))
      return false;

    return true;
  }
  private digitoVerificador(curp17: string): number {
    //Fuente https://consultas.curp.gob.mx/CurpSP/
    var diccionario = '0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ',
      lngSuma = 0.0,
      lngDigito = 0.0;
    for (var i = 0; i < 17; i++)
      lngSuma = lngSuma + diccionario.indexOf(curp17.charAt(i)) * (18 - i);
    lngDigito = 10 - (lngSuma % 10);
    if (lngDigito == 10) return 0;
    return lngDigito;
  }
  // FUNCIONES TOMADAS DE LA SIGUIENTE FUENTE
  // https://es.stackoverflow.com/questions/31039/c%C3%B3mo-validar-una-curp-de-m%C3%A9xico
}
