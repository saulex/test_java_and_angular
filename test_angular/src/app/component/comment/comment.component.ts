import { Component, OnInit, Input } from '@angular/core';
import { Comment } from 'src/app/model/comment';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  @Input() comment: Comment =  new Comment(); 
  creationDate: Date = new Date();
  isDisplayed = false;

  constructor(){

  }

  ngOnInit(): void {
    this.creationDate = new Date(
      this.comment.creationDate[0], 
      this.comment.creationDate[1] - 1, 
      this.comment.creationDate[2], 
      this.comment.creationDate[3], 
      this.comment.creationDate[4], 
      this.comment.creationDate[5]);

  }

  
}
