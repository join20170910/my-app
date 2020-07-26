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

            // 1、 token 过期 且 刷新 token 也失败 直接 调到 登陆 界面
            // 2 、 通过 认证服务器 认证 token 是否失效

            this.logout();
            // this.refershToken();
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

  // 认证服务器操作
  refershToken(){

    window.location.href = 'http://auth.imooc.com:9090/oauth/authorize?'
      + 'client_id=admin&'
      + 'redirect_uri=http://admin.imooc.com:8080/oauth/callback&'
      + 'response_type=code&'
      + 'state=abc'
  }

}
