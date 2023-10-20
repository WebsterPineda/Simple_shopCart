import { Component, OnInit } from '@angular/core';
import { ClientService } from '../services/client.service';
import { Client } from '../models/client.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];

  constructor(private clientService: ClientService, private router: Router) { }

  ngOnInit() {
    this.getClients();
  }

  getClients() {
    this.clientService.getClients().subscribe(clients => {
      this.clients = clients;
    });
  }

  viewDetails(client: Client) {
    this.router.navigate(['/client', client.id]);
  }

  editClient(client: Client) {
    this.router.navigate(['/client', client.id, 'edit']);
  }

  deleteClient(client: Client) {
    if (confirm("Are you sure to delete this client?")) {
      this.clientService.deleteClient(client.id).subscribe(() => {
        this.clients = this.clients.filter(c => c !== client);
      });
    }
  }
}
