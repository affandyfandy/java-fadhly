import { Routes } from '@angular/router';
import { ProductComponent } from './pages/product/product.component';

export const routes: Routes = [
    { path: 'products', component: ProductComponent },
    { path: '', redirectTo: '/products', pathMatch: 'full' }
];
