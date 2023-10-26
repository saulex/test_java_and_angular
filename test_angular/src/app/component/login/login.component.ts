import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { TokenService } from '../../services/token.service';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  username: string = ''; 
  password: string = ''; 
  loginFailed = false;
  constructor(private authService: AuthService, private tokenService: TokenService, private postService: PostService ){

  }

 

  onLoginClick(){
    console.log('loging');
    console.log('username: ' + this.username);
    console.log('password: ' + this.password);

    this.authService.login(
      {
        "userName": this.username, 
        "password": this.password
      }
    ).subscribe( 
      result => {
        console.log(result);
        if (result){
          this.tokenService.setToken(result.token);
          this.tokenService.setAuthoritties(result.authorities);
          this.tokenService.setUserId(result.idUser);
          
          this.loginFailed = false;
          this.postService.loadPostFromServices();
        }
      },
      error => {
        this.loginFailed = true;
      }
      
    )
  }
  

}
