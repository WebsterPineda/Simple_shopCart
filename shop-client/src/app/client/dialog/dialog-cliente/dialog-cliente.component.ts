import { Component, Inject, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Client } from 'src/app/models/client.model';

@Component({
  selector: 'app-dialog-cliente',
  templateUrl: './dialog-cliente.component.html',
})
export class DialogClienteComponent {
  public form: FormGroup;

  @Output() frmSubmit: EventEmitter<any>  = new EventEmitter();

  constructor(
    public dialogRef: MatDialogRef<DialogClienteComponent>,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public refClient: Client
  ) {
    this.form = formBuilder.group({
      nombre: ['', Validators.required],
      apellido: ['',  Validators.required],
      direccion: ['']
    })
    if (this.refClient) {
      this.form.patchValue(refClient);
    }
  }

  close() {
    this.dialogRef.close();
  }

  submit() {
    this.frmSubmit.emit(this.form.value);
    this.close();
  }
}
