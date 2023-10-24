import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-dialog-product',
  templateUrl: './dialog-product.component.html'
})
export class DialogProductComponent {
  form: FormGroup;
  isUpdate: boolean = false;
  @Output() formSubmit: EventEmitter<any> = new EventEmitter();

  constructor (private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) private refProduct: Product, public dialog: MatDialogRef<DialogProductComponent>) {
    this.form = fb.group({
      descripcion: ['', Validators.required],
      unidadMedida: ['', Validators.required],
      precio: [0.00, Validators.required]
    });

    if (refProduct) {
      this.form.patchValue(refProduct);
      this.isUpdate = true;
    }
  }

  submit() {
    this.formSubmit.emit(this.form.value);
    this.close();
  }

  close(){
    this.dialog.close();
  }
}
