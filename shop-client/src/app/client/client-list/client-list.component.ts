import { Component, OnInit, Type } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { Client } from '../../models/client.model';
import { ApiAnswer } from '../../models/api-answer.model';
import { DialogClienteComponent } from '../dialog/dialog-cliente/dialog-cliente.component';
import { MatDialog } from '@angular/material/dialog';
import { DeleteDialogComponent } from '../../common/dialog/delete-dialog/delete-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ReadOnlyComponent } from '../dialog/read-only/read-only.component';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss'],
})
export class ClientListComponent implements OnInit {
  public clients: Client[] = [];
  public displayColumns: string[] = ['id', 'nombre', 'apellido', 'actions'];
  readonly width: string = '400px';
  readonly animDuration: number = 2000;

  constructor(
    private clientService: ClientService,
    public dialog: MatDialog,
    private snack: MatSnackBar
  ) {}

  ngOnInit() {
    this.getClients();
  }

  async getClients() {
    this.clientService.getClients().subscribe((rsp: ApiAnswer<Client[]>) => {
      if (rsp != null && rsp.code === 0) {
        const data = rsp.data;
        if (data) {
          this.clients = data;
        }
      }
    });
  }

  createClient() {
    const dialogRef = this.dialog.open(DialogClienteComponent, {
      width: this.width,
    });

    dialogRef.componentInstance.frmSubmit.subscribe((rst: any) => {
      if (rst) {
        let cliente: ClienteImpl = new ClienteImpl();

        cliente.nombre = rst.nombre;
        cliente.apellido = rst.apellido;
        cliente.direccion = rst.direccion;

        this.clientService.addClient(cliente).subscribe({
          next: (rsp: ApiAnswer<Client>) => {
            if (rsp !== null && rsp.code === 0) {
              const data = rsp.data;
              if (data) {
                this.snack.open('El cliente fue creado con exito', '', {
                  duration: this.animDuration,
                });
              }
            }
          },
          error: (err: any) => {
            this.snack.open(
              'No ha sido posible crear el cliente ' + err.message,
              '',
              {
                duration: this.animDuration,
              }
            );
          },
        });
      }
    });

    dialogRef.afterClosed().subscribe(() => {
      this.getClients();
    });
  }

  viewDetails(client: Client) {
    const dialogRef = this.dialog.open(ReadOnlyComponent, {
      width: this.width,
      data: client,
    });

    dialogRef.afterClosed().subscribe(() => {
      this.getClients();
    });
  }

  editClient(client: Client) {
    const dialogRef = this.dialog.open(DialogClienteComponent, {
      width: this.width,
      data: client,
    });

    dialogRef.componentInstance.frmSubmit.subscribe((rst: any) => {
      if (rst) {
        client.nombre = rst.nombre;
        client.apellido = rst.apellido;
        client.direccion = rst.direccion;

        this.clientService.updateClient(client.id, client).subscribe({
          next: (rsp: ApiAnswer<Client>) => {
            if (rsp !== null && rsp.code === 0) {
              const data = rsp.data;
              if (data) {
                this.snack.open('El cliente fue actualizado con exito', '', {
                  duration: this.animDuration,
                });
              }
            }
          },
          error: (err: any) => {
            this.snack.open(
              'No ha sido posible actualizar el cliente ' + err.message,
              '',
              {
                duration: this.animDuration,
              }
            );
          },
        });
      }
    });

    dialogRef.afterClosed().subscribe(() => {
      this.getClients();
    });
  }

  deleteDialogue(client: Client) {
    const dialog = this.dialog.open(DeleteDialogComponent, {
      width: this.width,
    });
    dialog.afterClosed().subscribe((result) => {
      if (result && result === 'true') {
        this.deleteClient(client);
      }
    });
  }

  deleteClient(client: Client) {
    this.clientService.deleteClient(client.id).subscribe({
      next: () => {
        this.snack.open('Client deleted with success', '', {
          duration: 2000,
        });
        this.getClients();
      },
      error: (error) => {
        this.snack.open('Cant delete client ' + error.message, '', {
          duration: 2000,
        });
        this.getClients();
      },
    });
  }
}
export class ClienteImpl implements Client {
  id: number = -1;
  nombre: string = '';
  apellido: string = '';
  direccion: string = '';
}
