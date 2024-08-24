import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { CommonModule } from '@angular/common';
import { AgGridAngular } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [
    CommonModule,
    AgGridAngular
  ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss'
})
export class ProductComponent implements OnInit {
  products: Product[] = [];

  colDefs: ColDef[] = [
    { field: 'id', headerName: 'ID' },
    { field: 'name', headerName: 'Name' },
    { field: 'price', headerName: 'Price' },
    { field: 'status', headerName: 'Status' },
    { field: 'quantity', headerName: 'Quantity' },
    { field: 'createdAt', headerName: 'Created At' },
    { field: 'updatedAt', headerName: 'Updated At' }
  ];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getAll().subscribe({
      next: (data) =>  {
        this.products = data;
      }
    })
  }
}
