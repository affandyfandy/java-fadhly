import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Login } from '../models/login';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private formBuilder: FormBuilder, private loginService: LoginService) {
    this.loginForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  onSubmit(): void {
    const user = new Login(
      this.loginForm.value.username,
      this.loginForm.value.password
    );

    this.loginService.validateUserLogin(user).subscribe(
      isValid => {
        if(this.loginService.validateUserLogin(user)) {
          this.errorMessage = null;
          alert('Login Successful!');
        } else {
          this.errorMessage = 'Invalid username or password';
        }
      }
    )
  }
}
