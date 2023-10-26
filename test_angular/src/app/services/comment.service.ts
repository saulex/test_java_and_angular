import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Comment } from '../model/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  postURL =  "http://localhost:8080/post/";
  publicUrl = "http://localhost:8080/public/post";

  constructor(private httpClient: HttpClient) { }

  public getAll(idPost: number): Observable<any> {
    console.log(this.publicUrl);
    return this.httpClient.get<Array<Comment>>(this.publicUrl + "/" + idPost+ "/comments" , {});
  }

  public create(idPost: number, newComment: Comment): Observable<any> {
    return this.httpClient.post<any>(this.postURL + idPost+ "/comments" , {"content": newComment.comment});
  }

}
