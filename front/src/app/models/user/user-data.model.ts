export class UserDataModel{

    // Atributes
    private _fullName: string;
    private _curp: string;
    private _email: string;
    private _cellPhone: string;
    private _acceptedTerms: boolean;

    constructor() {
        this._fullName = "";
        this._curp = "";
        this._email = "";
        this._cellPhone = "";
        this._acceptedTerms = false;
    }
    
    public get FullName() : string {
        return this._fullName;
    }
    
    public set FullName(value : string) {
        this._fullName = value;
    }
    
    public get Curp() : string {
        return this._curp;
    }
    
    public set Curp(value : string) {
        this._curp = value;
    }
    
    public get Email () : string {
        return this._email;
    }
    
    public set Email(value : string) {
        this._email = value;
    }
    
    public get CellPhone() : string {
        return this._cellPhone;
    }
    
    public set CellPhone(value : string) {
        this._cellPhone = value;
    }

    public get AcceptedTerms(): boolean {
        return this._acceptedTerms;
    }

    public set AcceptedTerms(v: boolean) {
        this._acceptedTerms = v;
    }
    
}