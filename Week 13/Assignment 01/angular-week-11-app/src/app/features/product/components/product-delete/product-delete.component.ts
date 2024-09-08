import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-delete',
  standalone: true,
  imports: [ CommonModule ],
  templateUrl: './product-delete.component.html',
  styleUrl: './product-delete.component.scss'
})
export class ProductDeleteComponent {
  @Input() product: Product | null = null;
  @Output() close = new EventEmitter<void>();
  @Output() delete = new EventEmitter<Product>();

  displayStyle = "none";

  constructor() {}

  openModal() {
    this.displayStyle = "block";
  }

  closeModal() {
    this.displayStyle = "none";
    this.close.emit();
  }

  deleteModal() {
    if(this.product) {
      this.delete.emit(this.product);
    }
    this.closeModal();
  }
}
