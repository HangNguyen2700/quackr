import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SuccessResponse } from 'src/types/payload.type';
import { AuthResponse, LoginRequest } from '../../models/auth.model';
import { JwtHelperService } from '@auth0/angular-jwt';

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
