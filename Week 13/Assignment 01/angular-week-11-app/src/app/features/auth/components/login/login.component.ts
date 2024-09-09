import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private formBuilder: FormBuilder, private loginService: AuthService, private router: Router) {
    this.loginForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  onSubmit(): void {
    const user = new User();

    user.username = this.loginForm.value.username;
    user.password = this.loginForm.value.password;

    this.loginService.validateUserLogin(user).subscribe(
      isValid => {
        if(isValid) {
          this.errorMessage = null;
          alert('Login Successful!');
          this.router.navigate(['/product']);
        } else {
          this.errorMessage = 'Invalid username or password';
        }
      }
    )
  }
}
