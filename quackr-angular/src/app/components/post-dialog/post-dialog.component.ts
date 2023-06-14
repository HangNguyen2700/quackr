import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { PostRequest } from 'src/app/models/post.model';
import { Dialog } from 'src/types/dialog.type';

@Component({
  selector: 'app-post-dialog',
  templateUrl: './post-dialog.component.html',
  styleUrls: ['./post-dialog.component.css']
})
export class PostDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<PostDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public dialogData: Dialog<PostRequest>
  ) {}

  onSavePost(postData: PostRequest): void {
    this.dialogRef.close(postData);
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
