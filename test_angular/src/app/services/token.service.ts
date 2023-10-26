import { Injectable } from '@angular/core';

const TOKEN_KEY = 'authToken';
const USERNAME_KEY = 'userName';
const AUTHORITTIES_KEY = 'authoritties';
const USERID_KEY = 'authoritties';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  roles: any[] = [];
  constructor() { }

  public setToken(token: string):void{
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return window.sessionStorage.getItem(TOKEN_KEY) || '';
  }

  public setUserName(userName: string):void{
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, userName);
  }

  public getUserName(): string{
    return window.sessionStorage.getItem(USERNAME_KEY) || '';
  }

  public setAuthoritties(authoritties: string[]):void{
    this.roles = authoritties;
    window.sessionStorage.removeItem(AUTHORITTIES_KEY);
    window.sessionStorage.setItem(AUTHORITTIES_KEY,JSON.stringify(authoritties));
  }

  public setUserId(userId: number):void{
    window.sessionStorage.removeItem(USERID_KEY);
    window.sessionStorage.setItem(USERID_KEY, userId.toString());
  }

  public getUserId(): number{
    return  Number(window.sessionStorage.getItem(USERID_KEY));
  }


  public logOut(): void{
    window.sessionStorage.clear();
  }
}
