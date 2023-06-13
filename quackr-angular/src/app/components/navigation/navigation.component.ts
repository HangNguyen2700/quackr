import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserResponse } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth/auth.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  currentUser?: UserResponse;

  constructor(private authService: AuthService, private userService: UserService, private router: Router) { }
 
  ngOnInit(): void {
    this.isAuthenticated() && this.getCurrentUser();
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  signOut(): void {
    this.authService.signOut();

    this.router.navigate(['/sign-in']);
  }

  getCurrentUser(): void {
    this.userService.getCurrentUser().subscribe({
      next: response => {
        this.currentUser = response;
      },
      error: () => {}
    })
  }

  goHome(): void {
    this.router.navigate(['/']);
  }
}
