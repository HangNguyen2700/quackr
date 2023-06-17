import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { PostResponse } from 'src/app/models/post.model';
import { UserResponse } from 'src/app/models/user.model';
import { SuccessResponse } from 'src/types/payload.type';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = '/api/users';

  constructor(private http: HttpClient) { }

  getCurrentUser(): Observable<UserResponse> {
    return this.http.get<SuccessResponse<UserResponse>>(`${this.apiUrl}/me`).pipe(map(response => {
      return response.data;
    }));
  }

  getAllCurrentUserPosts(): Observable<PostResponse[]> {
    return this.http.get<SuccessResponse<PostResponse[]>>(`${this.apiUrl}/me/posts`).pipe(map(response => {
      return response.data;
    }));
  }
}
