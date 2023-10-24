import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiAnswer } from '../models/api-answer.model';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiUrl: string = 'http://localhost:8898/api/producto';

  constructor(private http: HttpClient) {}

  getProducts(): Observable<ApiAnswer<Product[]>> {
    return this.http.get<ApiAnswer<Product[]>>(this.apiUrl);
  }

  getProductById(id: number): Observable<ApiAnswer<Product>> {
    return this.http.get<ApiAnswer<Product>>(`${this.apiUrl}/${id}`);
  }

  addProduct(producto: Product): Observable<ApiAnswer<Product>> {
    return this.http.post<ApiAnswer<Product>>(this.apiUrl, producto);
  }

  updateProduct(id: number, producto: Product): Observable<ApiAnswer<Product>> {
    return this.http.put<ApiAnswer<Product>>(`${this.apiUrl}/${id}`, producto);
  }

  deleteProduct(id: number): Observable<ApiAnswer<Product>> {
    return this.http.delete<ApiAnswer<Product>>(`${this.apiUrl}/${id}`);
  }
}
