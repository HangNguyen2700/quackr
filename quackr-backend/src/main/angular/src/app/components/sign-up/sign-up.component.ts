import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignUpRequest } from 'src/app/models/auth.model';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  signUpForm: FormGroup = new FormGroup({});

constructor (private authService: AuthService,
  private router: Router,
  private fb: FormBuilder
){ }

ngOnInit(): void {
  this.createForm();
}

createForm(): void {
  this.signUpForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
    displayName: ['', Validators.required]
  });
}

signUp(): void {
  if (!this.signUpForm.valid) {
    return;
  }
  const signUpRequest: SignUpRequest = {
    username: this.signUpForm.get('username')?.value || '',
    password: this.signUpForm.get('password')?.value || '',
    displayname: this.signUpForm.get('displayname')?.value || '',
  }
  this.authService.signUp(signUpRequest)
    .subscribe(response => {
      const token = response.token;
      // Store the token in local storage or any other storage mechanism
      localStorage.setItem('token', token);
      // Redirect to the homepage or any other route
      this.router.navigate(['/']);
      this.authService.getEmitter().emit(token);
    });
}
}
