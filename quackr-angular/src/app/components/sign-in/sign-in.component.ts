import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/auth.model';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({});

  constructor(private authService: AuthService,
    private router: Router,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  signIn(): void {
    if (!this.loginForm.valid) {
      return;
    }
    const loginRequest: LoginRequest = {
      username: this.loginForm.get('username')?.value || '',
      password: this.loginForm.get('password')?.value || '',
    }
    this.authService.signIn(loginRequest)
      .subscribe({
        next: response => {
          const token = response.token;
          // Store the token in local storage or any other storage mechanism
          localStorage.setItem('token', token);
          // Redirect to the homepage or any other route
          this.router.navigate(['/']);
          this.authService.getEmitter().emit(token);
        },
        error: () => {}
      });
  }
}
