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
  constructor(private http: HttpClient){}

  login(){
    this.http.post('/login',this.credentials).subscribe(
      () =>{
        this.authenticated = true;
      },() =>{
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
}
