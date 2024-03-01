import { Component, EventEmitter, Output} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CurpModel } from '../../../../models/curp/curp.model';

@Component({
  selector: 'input-curp',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './curp.component.html',
  styleUrl: './curp.component.css'
})
export class CurpComponent{  
  // Attributes
  @Output() curpChange = new EventEmitter<CurpModel>;

  model= new CurpModel()
  
  updateCurp():void{
    this.curpChange.emit(this.model);
  }
}
