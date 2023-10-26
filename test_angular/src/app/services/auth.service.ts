import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authURL = "http://localhost:8080/auth/";
  constructor(private httpClient: HttpClient) { }

  public login(loginUsuario: {}): Observable<any> {
    return this.httpClient.post<any>(this.authURL+ 'login', loginUsuario);
  }


}
