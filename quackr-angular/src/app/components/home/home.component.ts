import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { PostResponse } from 'src/app/models/post.model';
import { PostService } from 'src/app/services/post/post.service';
import { SharedService } from 'src/app/services/shared/shared.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  allPosts: PostResponse[] = [];
  subscription!: Subscription;

  constructor(
    private postService: PostService,
    private sharedService: SharedService<void>
  ) {}

  ngOnInit(): void {
    this.getAllPosts();
    this.subscription = this.sharedService.subscribeToEvent().subscribe((eventData) => {
      this.getAllPosts();
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  getAllPosts(): void {
    this.postService.getAllPosts().subscribe({
      next: response => {
        this.allPosts = response;
      },
      error: () => {}
    })
  }
}
