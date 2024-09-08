import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-add',
  standalone: true,
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './product-add.component.html',
  styleUrl: './product-add.component.scss'
})
export class ProductAddComponent {
  productForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private productService: ProductService, private router: Router) {
    this.productForm = this.formBuilder.group({
      name: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      quantity: ['', [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit() {
    if(this.productForm.valid) {
      this.productService.getAll().subscribe(products => {
        const newId = this.getNextId(products);
        const newProduct: Product = {
          ...this.productForm.value,
          id: newId,
          status: 'Active',
          createdAt: new Date(),
          updatedAt: new Date(),
        };

        this.productService.createProduct(newProduct).subscribe(
          createdProduct => {
            if(createdProduct) {
              this.productForm.reset();
              alert('Product Created!');
              this.router.navigate(['/product']);
            }
          }
        );
      });
    } else {
      Object.keys(this.productForm.controls).forEach(key => {
        const control = this.productForm.get(key);
        control?.markAllAsTouched();
      });
    }
  }

  private getNextId(product: Product[]): string {
    const maxId = product.reduce((max, product) => {
      const id = parseInt(product.id ?? '0');
      return id > max ? id: max;
    }, 0);

    return (maxId + 1).toString();
  }
}
