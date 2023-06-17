import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable, map } from 'rxjs';
import { PostRequest, PostResponse } from 'src/app/models/post.model';
import { Role } from 'src/enums/user.enum';
import { SuccessResponse, Token } from 'src/types/payload.type';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private apiUrl = '/api/posts';
  private username = '';
  private role = '';

  constructor(
    private http: HttpClient,
    private jwtHelper: JwtHelperService,
    private authService: AuthService
  ) {
    this.initToken()
    this.authService.getEmitter().subscribe((customObject) => {
      this.initToken()
    });
  }

  initToken(): void {
    const decodeToken = this.jwtHelper.decodeToken() as Token;
    this.username = decodeToken?.sub;
    this.role = decodeToken?.role;
  }

  getAllPosts(): Observable<PostResponse[]> {
    return this.http.get<SuccessResponse<PostResponse[]>>(`${this.apiUrl}`).pipe(map(response => {
      return response.data;
    }));
  }

  deletePostById(postId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${postId}`);
  }

  editPost(postId: number, postData: PostRequest): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${postId}`, postData);
  }

  createPost(postData: PostRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}`, postData);
  }

  canEditPost(post: PostResponse): boolean {
    return post.authorUsername === this.username;
  }

  canDeletePost(post: PostResponse): boolean {
    return this.role === Role.ADMIN || post.authorUsername === this.username;
  }
}
