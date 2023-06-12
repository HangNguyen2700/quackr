import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
username!: string;
password!: string;
displayname!: string;

constructor (private authService: AuthService, private router: Router){ }

signUp(): void {
  this.authService.signUp({ username: this.username, password: this.password, displayname: this.displayname})
  .subscribe(response => {
    const token = response.token;
    // Store the token in local storage or any other storage mechanism
    localStorage.setItem('token', token);
    // Redirect to the homepage or any other route
    this.router.navigate(['/']);
  });
}
}
