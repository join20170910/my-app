import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  title = 'angular microservice security demo';
  authenticated = false;
  credentials = {username:'xixi',password: '123456'};
  order = {id:'0',productId:'100'}
  constructor(private http: HttpClient){
    this.http.get('me').subscribe(
      (data: any) => {
        if(data){
          this.authenticated = true;
        }
        if (!this.authenticated){
          window.location.href = 'http://auth.imooc.com:9090/oauth/authorize?'
            + 'client_id=admin&'
            + 'redirect_uri=http://admin.imooc.com:8080/oauth/callback&'
            + 'response_type=code&'
            + 'state=abc'
        }

      },() =>{
        alert('login fail ')
      }
    )

  }

  login(){
    this.http.post('/login',this.credentials).subscribe(
      () =>{
        this.authenticated = true;
      },
      (data: any) => {
        this.order = data;
        console.log(data);
      },

      () =>{
        alert('login fail ')
      }
    );
  }

  getOrder(){

    this.http.get('api/order/orders/1').subscribe(
      (data: any) => {
        this.order = data;
        console.log(data);
      }
      ,
      () =>{
        alert('get order fail')
      }
    )
  }
  logout(){
    this.http.post('/logout',this.credentials).subscribe(
      () =>{
        this.authenticated = false;
      },() =>{
        alert('logout fail ')
      }
    );
  }
}
