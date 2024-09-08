import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

const baseUrl = "http://localhost:3000/products";

@Injectable({
  providedIn: 'root'
})


export class ProductService {

  constructor(private http: HttpClient) { }

  formatDate(params: any): string {
    if (!params.value) return '';
    
    const date = new Date(params.value);

    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear();

    return `${day}/${month}/${year}`;
  }

  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(baseUrl);
  }

  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(baseUrl, product);
  }

  updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(`${baseUrl}/${product.id}`, product);
  }

  deleteProduct(product: Product): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${product.id}`)
  }

}
