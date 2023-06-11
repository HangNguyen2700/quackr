import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth/auth.service';

@Component({
  selector: 'app-root',
  template: `
    <router-outlet></router-outlet>
  `,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  title = 'quackr-frontend';
  constructor(authService: AuthService, router: Router) {
    if (authService.isAuthenticated()) {
      router.navigate(['home']);
    }
  }
}
