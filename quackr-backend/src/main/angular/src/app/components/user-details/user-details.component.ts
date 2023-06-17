import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { PostResponse } from 'src/app/models/post.model';
import { SharedService } from 'src/app/services/shared/shared.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit, OnDestroy {
  allPosts: PostResponse[] = [];
  subscription!: Subscription;

  constructor(
    private userService: UserService,
    private sharedService: SharedService<void>
  ) {}

  ngOnInit(): void {
    this.getAllCurrentUserPosts();
    this.subscription = this.sharedService.subscribeToEvent().subscribe((eventData) => {
      this.getAllCurrentUserPosts();
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  getAllCurrentUserPosts(): void {
    this.userService.getAllCurrentUserPosts().subscribe({
      next: response => {
        this.allPosts = response;
      },
      error: () => {}
    })
  }
}
