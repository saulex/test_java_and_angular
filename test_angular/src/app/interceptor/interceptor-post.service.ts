import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenService } from '../services/token.service';

@Injectable({
  providedIn: 'root'
})
export class InterceptorPostService implements HttpInterceptor {

  constructor(private tokenServices: TokenService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let initReq = req;
    const token =  this.tokenServices.getToken();
    if(token != null){
      initReq = req.clone({
        setHeaders: {
          "Authorization": `Bearer ${token}`,
          "Access-Control-Allow-Origin":  "*",
         "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
  
        }
      });
    }
    return next.handle(initReq);

  }

  
}

export const interceptorProvider = [{provide: HTTP_INTERCEPTORS, useClass: InterceptorPostService, multi: true}];