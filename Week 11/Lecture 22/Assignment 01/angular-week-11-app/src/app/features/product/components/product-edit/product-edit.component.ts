import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-edit',
  standalone: true,
  imports: [ CommonModule, FormsModule ],
  templateUrl: './product-edit.component.html',
  styleUrl: './product-edit.component.scss'
})
export class ProductEditComponent {
  @Input() product: Product | null = null;
  @Input() clickButton: boolean | null = null;
  @Output() close = new EventEmitter<void>();
  @Output() save = new EventEmitter<Product>();

  displayStyle = "none";

  constructor() {}

  openModal() {
    this.displayStyle = "block";
  }

  closeModal() {
    this.displayStyle = "none";
    this.close.emit();
  }

  saveChanges() {
    if(this.product) {
      this.save.emit(this.product);
    }
    this.closeModal();
  }
}
