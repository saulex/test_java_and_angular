import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/model/post';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent  implements OnInit {
  posts: Array<Post> =  this.postService.posts;

  constructor(private postService: PostService){
    
  }
  ngOnInit(): void {
    this.postService.getObservable()
      .subscribe({
        next: (post) => {
          this.posts = post
          
        },
      });
      this.postService.loadPostFromServices();
  }


  
  
}





