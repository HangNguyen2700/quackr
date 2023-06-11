import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.url.includes('/api/auth') || request.url.includes('/api/public')) {
      return next.handle(request);
    }

    const authToken = localStorage.getItem('token');
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${authToken}`
      }
    });
    return next.handle(request);
  }
}
