import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../models/client.model';
import { ApiAnswer } from '../models/api-answer.model';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private apiUrl = 'http://localhost:8898/api/cliente';

  constructor(private http: HttpClient) {}

  getClients(): Observable<ApiAnswer<Client[]>> {
    return this.http.get<ApiAnswer<Client[]>>(this.apiUrl);
  }

  getClient(id: number): Observable<ApiAnswer<Client>> {
    return this.http.get<ApiAnswer<Client>>(`${this.apiUrl}/${id}`);
  }

  addClient(client: Client): Observable<ApiAnswer<Client>> {
    return this.http.post<ApiAnswer<Client>>(this.apiUrl, client);
  }

  updateClient(id: number, client: Client): Observable<ApiAnswer<Client>> {
    return this.http.put<ApiAnswer<Client>>(`${this.apiUrl}/${id}`, client);
  }

  deleteClient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
