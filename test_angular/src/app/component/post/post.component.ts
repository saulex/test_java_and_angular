import { Component, Input,OnInit } from '@angular/core';
import { Post } from 'src/app/model/post';
import { CommentService } from 'src/app/services/comment.service';
import { Comment } from 'src/app/model/comment';
import { TokenService } from 'src/app/services/token.service';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit{
  @Input() post: Post = new Post(); 

  
  comments: Array<Comment> = [];
  creationDate: Date = new Date();
  isDisplayed:boolean = true;



  constructor(private commentService: CommentService, private postService: PostService, private tokenService: TokenService){

  }
  ngOnInit(): void {
    console.log("FULL")

    this.creationDate = new Date(
    this.post.creationDate[0], 
    this.post.creationDate[1] - 1, 
    this.post.creationDate[2], 
    this.post.creationDate[3], 
    this.post.creationDate[4], 
    this.post.creationDate[5]);

    this.commentService.getAll(this.post.id).subscribe(
      result => {
        console.log("esy")
        this.comments = result;
      },
      error => {
        console.log("false")
        
      }
    )
    console.log("isDisplayed", this.tokenService.getUserId() == this.post.idUser);
    console.log("isDisplayed", "this.tokenService.getUserId:" + this.tokenService.getUserId() + " this.post.idUser:" + this.post.idUser);
    this.isDisplayed = this.tokenService.getUserId() === this.post.idUser;
  }

  
  updatePost():void{
    this.postService.setEventModify(this.post);
  }

  deletePost(){
    this.postService.delete(this.post.id).subscribe( 
      result => {
        console.log(result);
        this.postService.loadPostFromServices();
       
      },
      error => {
        
      }
      
    )
  }
  
}
