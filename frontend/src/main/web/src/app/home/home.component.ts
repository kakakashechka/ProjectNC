import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Demo';
  greeting = {};
  constructor( private http: HttpClient, private app: UserService) {
    http.get('api/resource').subscribe(data => this.greeting = data);
  }

  authenticated() {

    return this.app.authenticated;

  }

  ngOnInit(): void {
  }

}
