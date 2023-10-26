import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, Subject } from 'rxjs';
import { Post } from '../model/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  subject = new Subject();
  toModify = new Subject();
  public posts: Array<Post> = [];

  postURL =  "http://localhost:8080/post/";;

  publicUrl = "http://localhost:8080/public/post";

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<any> {
    console.log(this.publicUrl);
    return this.httpClient.get<Array<Post>>(this.publicUrl, {});
  }

  public create(newPost: Post): Observable<any> {
    return this.httpClient.post<any>(this.postURL, newPost);
  }

  public delete(idPost: number): Observable<any> {
    return this.httpClient.delete<any>(this.postURL+ idPost);
  }

  public update(post: Post): Observable<any> {
    return this.httpClient.put<any>(this.postURL+ post.id, post);
  }

  public loadPostFromServices(){
    console.log("FULL")
    this.getAll().subscribe(
      result => {
        console.log("esy")
        this.subject.next(result);
        this.posts = result;
      },
      error => {
        console.log("false")
        
      }
    )
  }


  public getObservable(): Subject<any>{
    return this.subject;
  }

  public getObservableModify(): Subject<any>{
    return this.toModify;
  }

  public setEventModify(post: Post): void{
    
    this.toModify.next([post]);
  }

  
  
}
