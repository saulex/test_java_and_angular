import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/model/post';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit{
  loginCreation: boolean = false;
  title: string = "";
  content: string = "";
  isModifyPost = false;
  post: Post = new Post();

  constructor(private postService: PostService){

  }
  ngOnInit(): void {
    this.postService.getObservableModify().subscribe(
      result=>{

        let postResponse = result[0];
        this.isModifyPost = true;
        this.post = postResponse;
        this.title = postResponse.title;
        this.content = postResponse.content;
      }
    )
  }


  postPost(){
    console.log('create post');
    console.log('title: ' + this.title);
    console.log('content: ' + this.content);
    let newPost = new Post();
    newPost.title = this.title;
    newPost.content = this.content;
    this.postService.create(newPost).subscribe( 
      result => {
        console.log(result);
        this.postService.loadPostFromServices();
       
      },
      error => {
        this.loginCreation = true;
      }
      
    )

    
  }

  updatePost(){
    console.log('create post');
    console.log('title: ' + this.title);
    console.log('content: ' + this.content);
    let newPost = new Post();
    newPost.title = this.title;
    newPost.content = this.content;
    newPost.id = this.post.id;
    this.postService.update(newPost).subscribe( 
      result => {
        console.log(result);
        this.postService.loadPostFromServices();
       
      },
      error => {
        this.loginCreation = true;
      }
      
    )

    
  }

  

}
