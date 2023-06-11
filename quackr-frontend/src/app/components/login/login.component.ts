import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent {
  username!: string;
  password!: string;

  constructor(private authService: AuthService, private router: Router) { }

  signIn(): void {
    this.authService.signIn({ username: this.username, password: this.password })
      .subscribe(response => {
        const token = response.token;
        // Store the token in local storage or any other storage mechanism
        localStorage.setItem('token', token);
        // Redirect to the homepage or any other route
        this.router.navigate(['/home']);
      });
  }
}
