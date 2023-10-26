import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './component/index/index.component';
import { LoginComponent } from './component/login/login.component';
import { BlogComponent } from './component/blog/blog.component';
import { PostComponent } from './component/post/post.component';
import { CommentComponent } from './component/comment/comment.component';
import { CreatePostComponent } from './component/create-post/create-post.component';
import { CreateCommentComponent } from './component/create-comment/create-comment.component';
import { interceptorProvider } from './interceptor/interceptor-post.service';



@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    LoginComponent,
    BlogComponent,
    PostComponent,
    CommentComponent,
    CreatePostComponent,
    CreateCommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
