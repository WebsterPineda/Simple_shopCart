import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiAnswer } from '../models/api-answer.model';
import { Sell } from '../models/sell.model';
import { SellDetail } from '../models/sell-detail.model';

@Injectable({
  providedIn: 'root',
})
export class SellServiceService {
  private apiUrl: string = 'http://localhost:8898/api/venta';
  constructor(private http: HttpClient) {}

  getSells(): Observable<ApiAnswer<Sell>> {
    return this.http.get<ApiAnswer<Sell>>(this.apiUrl);
  }

  getSellById(id: number): Observable<ApiAnswer<Sell>> {
    return this.http.get<ApiAnswer<Sell>>(`${this.apiUrl}/${id}`);
  }

  createSell(sell: Sell): Observable<ApiAnswer<Sell>> {
    return this.http.post<ApiAnswer<Sell>>(this.apiUrl, sell);
  }

  updateSell(id: number, sell: Sell): Observable<ApiAnswer<Sell>> {
    return this.http.put<ApiAnswer<Sell>>(`${this.apiUrl}/${id}`, sell);
  }

  deleteSell(id: number): Observable<ApiAnswer<Sell>> {
    return this.http.delete<ApiAnswer<Sell>>(`${this.apiUrl}/${id}`);
  }

  getSellDetail(sellId: number): Observable<ApiAnswer<SellDetail[]>> {
    return this.http.get<ApiAnswer<SellDetail[]>>(
      `${this.apiUrl}/${sellId}/detail`
    );
  }

  createSellDetail(
    sellId: number,
    sellDetail: SellDetail
  ): Observable<ApiAnswer<SellDetail>> {
    return this.http.post<ApiAnswer<SellDetail>>(
      `${this.apiUrl}/${sellId}/detail`,
      sellDetail
    );
  }

  updateSellDetail(
    sellId: number,
    detailId: number,
    sellDetail: SellDetail
  ): Observable<ApiAnswer<SellDetail>> {
    return this.http.put<ApiAnswer<SellDetail>>(
      `${this.apiUrl}/${sellId}/detail/${detailId}`,
      sellDetail
    );
  }

  deleteSellDetail(sellId: number, detailId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${sellId}/detail/${detailId}`
    );
  }
}
