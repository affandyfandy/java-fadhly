import { Routes } from '@angular/router';
import { ProductComponent } from './features/product/components/product-list/product.component';
import { ProductAddComponent } from './features/product/components/product-add/product-add.component';
import { LoginComponent } from './features/auth/components/login/login.component';
import { RegisterComponent } from './features/auth/components/register/register.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent, title: 'Login'},
    { path: 'register', component: RegisterComponent, title: 'Register'},
    { path: 'product', component: ProductComponent, title: 'List Product'},
    { path: 'product/add', component: ProductAddComponent, title: 'Add New Product'}
];
