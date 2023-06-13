import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable, map } from 'rxjs';
import { PostResponse } from 'src/app/models/post.model';
import { Role } from 'src/enums/user.enum';
import { SuccessResponse, Token } from 'src/types/payload.type';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private apiUrl = '/api/posts';
  private username = '';
  private role = '';

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService) {
    const decodeToken = this.jwtHelper.decodeToken() as Token;
    this.username = decodeToken.sub;
    this.role = decodeToken.role;
  }

  getAllPosts(): Observable<PostResponse[]> {
    return this.http.get<SuccessResponse<PostResponse[]>>(`${this.apiUrl}`).pipe(map(response => {
      return response.data;
    }));
  }

  deletePostById(postId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${postId}`);
  }

  canDeletePost(post: PostResponse): boolean {
    return this.role === Role.ADMIN || post.authorUsername === this.username;
  }
}
