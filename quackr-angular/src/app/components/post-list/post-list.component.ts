import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PostRequest, PostResponse } from 'src/app/models/post.model';
import { PostService } from 'src/app/services/post/post.service';
import { PostDialogComponent } from '../post-dialog/post-dialog.component';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent {
  @Input() posts: PostResponse[] = [];

  constructor(
    private postService: PostService,
    private dialog: MatDialog
  ) {}

  openPostDialog(post: PostResponse): void {
    const dialogRef = this.dialog.open(PostDialogComponent, {
      width: '40%',
      data: { dialogTitle: 'Edit Post', data: post }
    });

    dialogRef.afterClosed().subscribe((result: PostRequest) => {
      if (result) {
        this.editPost(post.id, result)
      }
    });
  }

  private editPost(postId: number, editedPostData: PostRequest): void {
    this.postService.editPost(postId, editedPostData).subscribe({
      next: response => {
        this.posts = this.posts.map((item) => ({
          ...item,
          content: item.id === postId ? editedPostData.content : item.content,
        }));
      },
      error: () => {}
    })
  }

  deletePost(post: PostResponse): void {
    this.postService.deletePostById(post.id).subscribe({
      next: response => {
        this.posts = this.posts.filter((item) => item.id !== post.id);
      },
      error: () => {}
    })
  }

  canEdit(post: PostResponse): boolean {
    return this.postService.canEditPost(post);
  }

  canDelete(post: PostResponse): boolean {
    return this.postService.canDeletePost(post);
  }
}
