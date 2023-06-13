import { Component } from '@angular/core';
import { PostResponse } from 'src/app/models/post.model';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent {
  allPosts: PostResponse[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAllCurrentUserPosts();
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
