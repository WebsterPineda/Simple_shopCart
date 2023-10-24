import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Client } from 'src/app/models/client.model';

@Component({
  selector: 'app-read-only',
  templateUrl: './read-only.component.html'
})
export class ReadOnlyComponent {
  frm: FormGroup;

  constructor (private dialogRef: MatDialogRef<ReadOnlyComponent>, private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) private refClient: Client) {
    this.frm = fb.group({
      nombre: [''],
      apellido: [''],
      direccion: ['']
    });

    this.frm.patchValue(refClient);
  }

  close(): void {
    this.dialogRef.close();
  }
}
