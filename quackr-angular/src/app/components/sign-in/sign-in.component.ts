import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {
  username!: string;
  password!: string

  constructor(private authService: AuthService, private router: Router) { }

  signIn(): void {
    this.authService.signIn({ username: this.username, password: this.password })
      .subscribe({
        next: response => {
          const token = response.token;
          // Store the token in local storage or any other storage mechanism
          localStorage.setItem('token', token);
          // Redirect to the homepage or any other route
          this.router.navigate(['/']);
        },
        error: () => {}
      });
  }
}
