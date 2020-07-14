import { Injectable } from "@angular/core";
import { HttpEvent,HttpInterceptor,HttpHandler,
  HttpRequest,HttpResponse } from'@angular/common/http';
import { tap } from "rxjs/operators";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";


@Injectable()
export class RefreshInterceptor implements HttpInterceptor{
  constructor(private http: HttpClient) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
      tap(() =>{},
        error =>{
          console.log(error);
          if (error.status === 500 && error.error.message === 'refresh fail')
          {
            this.logout();
          }
        }
        ));
  }

  logout(){
    this.http.post('logout',{}).subscribe(
      () =>{
        window.location.href = 'http://auth.imooc.com:9090/logout?redirect_uri' +
          '=http://admin.imooc.com:8080';
      }
    )
  }
}
