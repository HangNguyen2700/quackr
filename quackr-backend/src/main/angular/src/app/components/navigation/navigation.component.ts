import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { PostRequest, PostResponse } from 'src/app/models/post.model';
import { UserResponse } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth/auth.service';
import { PostService } from 'src/app/services/post/post.service';
import { UserService } from 'src/app/services/user/user.service';
import { PostDialogComponent } from '../post-dialog/post-dialog.component';
import { SharedService } from 'src/app/services/shared/shared.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  currentUser?: UserResponse;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private postService: PostService,
    private sharedService: SharedService<void>,
    private dialog: MatDialog,
    private router: Router
  ) { }
 
  ngOnInit(): void {
    this.authService.getEmitter().subscribe((customObject) => {
      this.getCurrentUser();
    });
    this.isAuthenticated() && this.getCurrentUser();
  }

  openPostDialog(): void {
    const dialogRef = this.dialog.open(PostDialogComponent, {
      width: '40%',
      data: { dialogTitle: 'Create Post' }
    });

    dialogRef.afterClosed().subscribe((result: PostRequest) => {
      if (result) {
        this.createPost(result);
        this.emitEvent();
      }
    });
  }

  private createPost(createdPostData: PostRequest): void {
    this.postService.createPost(createdPostData).subscribe({
      next: response => {},
      error: () => {}
    })
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

  emitEvent() {
    this.sharedService.emitEvent();
  }
}
