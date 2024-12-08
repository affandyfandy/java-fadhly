import { Injectable } from '@angular/core';
import { Login } from '../models/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() { }

  validateUserLogin(user: Login): boolean {
    if( user.username === "admin" && user.password === "admin123") {
      return true;
    }

    return false;
  }
}
