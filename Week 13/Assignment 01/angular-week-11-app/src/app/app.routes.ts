import { Routes } from '@angular/router';
import { ProductComponent } from './features/product/components/product-list/product.component';
import { ProductAddComponent } from './features/product/components/product-add/product-add.component';

export const routes: Routes = [
    { path: '', redirectTo: '/product', pathMatch: 'full' },
    { path: 'product', component: ProductComponent, title: 'List Product'},
    { path: 'product/add', component: ProductAddComponent, title: 'Add New Product'}
];
