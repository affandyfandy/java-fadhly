import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

const baseUrl = 'http://localhost:3000/products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(baseUrl);
  }

  createProduct(product: any): Observable<any> {
    return this.http.post(baseUrl, product);
  }

  updateProduct(id: any, product: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, product);
  }

  
}
