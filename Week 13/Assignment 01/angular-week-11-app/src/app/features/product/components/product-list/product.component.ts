import { Component, OnInit, ViewChild } from '@angular/core';
import { AgGridAngular } from 'ag-grid-angular';
import { ColDef, RowClassRules } from 'ag-grid-community';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { CommonModule } from '@angular/common';
import { ProductEditComponent } from '../product-edit/product-edit.component';
import { ProductDeleteComponent } from '../product-delete/product-delete.component';


@Component({
  selector: 'app-product',
  standalone: true,
  imports: [ 
    CommonModule,
    AgGridAngular,
    ProductEditComponent,
    ProductDeleteComponent ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss'
})

export class ProductComponent implements OnInit {
  @ViewChild(ProductEditComponent) editComponent!: ProductEditComponent;
  @ViewChild(ProductDeleteComponent) deleteComponent!: ProductDeleteComponent;

  constructor(private productService: ProductService) {}

  products: Product[] = [];
  colDefs: ColDef[] = [];
  selectedProduct: Product | null = null;
  clickEditButton: boolean | null = null;

  public defaultColDef: ColDef = {
    floatingFilter: true,
    flex: 1
  };

  public themeClass: string = "ag-theme-alpine-dark";
  public paginationPageSize: number = 10;
  public rowClassRules: RowClassRules = {
    'rag-green': (params) => { return params.data.status === 'Active'; },
    'rag-red': (params) => { return params.data.status === 'Deactive'; }
  };

  ngOnInit(): void {
    this.initColumnDefs();
    this.loadProducts();
  }

  private initColumnDefs(): void {
    this.colDefs = [
      { field: 'name', 
        headerName: 'Name',
        cellDataType: 'text',
        filter: "agTextColumnFilter", },
      { field: 'price',
        headerName: 'Price',
        cellDataType: 'number',
        filter: 'agNumberColumnFilter'
      },
      { field: 'quantity',
        headerName: 'Quantity',
        cellDataType: 'number',
        filter: 'agNumberColumnFilter'
      },
      {
        headerName: 'Action',
        cellRenderer: () => {
          return `
          <button class="btn btn-secondary btn-sm info-btn">Info</button>
          <button class="btn btn-primary btn-sm edit-btn">Edit</button>
          <button class="btn btn-danger btn-sm delete-btn">Delete</button>
          `;
        },
        onCellClicked: (params: any) => {
          if (params.event.target.classList.contains('edit-btn')) {
            this.onEditClick(params.data);
          } else if(params.event.target.classList.contains('info-btn')) {
            this.onInfoClick(params.data);
          } else if(params.event.target.classList.contains('delete-btn')) {
            this.onDeleteClick(params.data);
          }
        }
      }
    ];
  }

  onEditClick(product: Product) {
    this.selectedProduct = { ...product };
    this.editComponent.openModal();
    this.clickEditButton = true;
  }

  onInfoClick(product: Product) {
    this.selectedProduct = { ...product };
    this.editComponent.openModal();
    this.clickEditButton = false;
  }

  onDeleteClick(product: Product) {
    this.selectedProduct = { ...product };
    this.deleteComponent.openModal();
  }

  onModalClose() {
    this.selectedProduct = null;
    this.clickEditButton = null;
  }

  onProductSave(updatedProduct: Product) {
    updatedProduct.updatedAt = new Date();

    this.productService.updateProduct(updatedProduct).subscribe({
      next: () => {
        this.loadProducts();
      },
      error: (error) => {
        console.error('Error updating product:', error);
      }
    })
  }

  onProductDelete(product: Product) {
    this.productService.deleteProduct(product).subscribe({
      next: () => {
        alert('Delete Product Successful!');
        this.loadProducts();
      },
      error: (error) => {
        console.error('Error Deleting Product', error);
      }
    })
  }

  private loadProducts(): void {
    this.productService.getAll().subscribe({
      next: (data) => {
        this.products = data;
      }
    });
  }
}