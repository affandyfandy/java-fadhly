import { Injectable } from '@angular/core';
import { Login } from '../models/login';
import { map, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = 'http://localhost:3000/user';

  constructor(private http: HttpClient) { }

  validateUserLogin(user: Login): Observable<boolean> {
    return this.http.get<Login[]>(`${this.baseUrl}?username=${user.username}&password=${user.password}`)
    .pipe(
      map(response => response.length > 0)
    );
  }
}
