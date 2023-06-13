import { Component, Input } from '@angular/core';
import { PostResponse } from 'src/app/models/post.model';
import { PostService } from 'src/app/services/post/post.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent {
  @Input() posts: PostResponse[] = [];

  constructor(private postService: PostService) { }

  editPost(post: PostResponse): void {
    // Implement edit functionality
    // You can navigate to an edit page or show a modal for editing the post
  }

  deletePost(post: PostResponse): void {
    this.postService.deletePostById(post.id).subscribe({
      next: response => {
        this.posts = this.posts.filter((item) => item.id !== post.id);
      },
      error: () => {}
    })
  }

  canDelete(post: PostResponse): boolean {
    return this.postService.canDeletePost(post);
  }
}
