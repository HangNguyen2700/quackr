import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable, map } from 'rxjs';
import { LoginRequest, AuthResponse } from 'src/app/models/auth.model';
import { SuccessResponse } from 'src/types/payload.type';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '/api/auth';

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService) { }

  signIn(credentials: LoginRequest): Observable<AuthResponse> {
    return this.http.post<SuccessResponse<string>>(`${this.apiUrl}/login`, credentials).pipe(map(response => {
      return { token: response.data };
    }));
  }

  signUp(credentials: { username: string, password: string }): Observable<AuthResponse> {
    return this.http.post<SuccessResponse<string>>(`${this.apiUrl}/signup`, credentials).pipe(map(response => {
      return { token: response.data };
    }));
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');

    return !this.jwtHelper.isTokenExpired(token);
  }
}
