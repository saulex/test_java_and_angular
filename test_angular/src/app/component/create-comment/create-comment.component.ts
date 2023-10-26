import { Component, Input } from '@angular/core';
import { Comment } from 'src/app/model/comment';
import { Post } from 'src/app/model/post';
import { CommentService } from 'src/app/services/comment.service';
import { PostComponent } from '../post/post.component';

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.css']
})
export class CreateCommentComponent {
  @Input() post: Post =  new Post(); 
  @Input() postComponent: PostComponent | undefined; 
  content: string = "";

  constructor(private commentService: CommentService){

  }

  postComment(){
    console.log('create coment');

    console.log('content: ' + this.content);
    let newComment= new Comment();

    newComment.comment = this.content;
    this.commentService.create(this.post.id, newComment).subscribe( 
      result => {
        console.log(result);
        this.postComponent?.ngOnInit();
      
       
      },
      error => {
        
      }
      
    )
  }

  
}
