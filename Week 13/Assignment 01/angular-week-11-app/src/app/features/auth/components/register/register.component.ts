import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  registerForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.formBuilder.group({
      username: [''],
      password: [''],
      confirmPassword: ['']
    });
  }

  onSubmit(): void {
    if (this.registerForm.value.password !== this.registerForm.value.confirmPassword) {
      this.errorMessage = 'Passwords do not match!';
      return;
    }

    this.authService.getUsers().subscribe(
      users => {
        const newUser = new User();
        newUser.id = this.getNextId(users);
        newUser.username = this.registerForm.value.username;
        newUser.password = this.registerForm.value.password;

        this.authService.registerUser(newUser).subscribe(
          response => {
            this.errorMessage = null;
            alert('Registration Successful!');
            this.router.navigate(['/login']);
          },
          error => {
            this.errorMessage = 'An error occurred. Please try again.';
          }
        );
      },
      error => {
        this.errorMessage = 'Failed to retrieve users.';
      }
    );
  }

  private getNextId(user: User[]): string {
    const maxId = user.reduce((max, user) => {
      const id = parseInt(user.id ?? '0');
      return id > max ? id: max;
    }, 0);

    return (maxId + 1).toString();
  }
}
