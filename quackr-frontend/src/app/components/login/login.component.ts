import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent {

  constructor(private authService: AuthService, private router: Router) { }

  signIn(): void {
    if (!this.loginForm?.valid) {
      return;
    }

    const { username, password } = this.loginForm.value;

    this.authService.signIn({ username, password })
      .subscribe(response => {
        const token = response.token;
        // Store the token in local storage or any other storage mechanism
        localStorage.setItem('token', token);
        // Redirect to the homepage or any other route
        this.router.navigate(['/home']);
      });
  }
}
