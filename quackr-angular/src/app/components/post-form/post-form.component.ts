import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostRequest } from 'src/app/models/post.model';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css']
})
export class PostFormComponent implements OnInit {
  @Input() postData?: PostRequest;
  @Output() savePost = new EventEmitter<PostRequest>();

  postForm: FormGroup = new FormGroup({});

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(): void {
    this.postForm = this.fb.group({
      content: [this.postData ? this.postData.content : '', Validators.required]
    });
  }

  onSave(): void {
    if (this.postForm.valid) {
      const postData = {
        content: this.postForm.value.content
      };
      this.savePost.emit(postData);
    }
  }
}
