import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:3000/users';

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  validateUserLogin(user: User): Observable<boolean> {
    return this.http.get<User[]>(`${this.baseUrl}?username=${user.username}&password=${user.password}`)
    .pipe(
      map(response => response.length > 0)
    );
  }

  registerUser(user: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, user);
  }
}
