import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from "./entities/user";
import {Observable, of, throwError} from "rxjs";
import {catchError, tap, finalize} from "rxjs/operators";
import { error } from 'protractor';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  authenticated = false;
  // user: User;
  user: User = {
    id: '123',
    username: 'lol',
    firstName: 'lol',
    lastName: 'kek',
    email: 'sdad@sdasd.com',
    role: {name: 'super admin'},
    password: 'lol'
  };


  constructor(private http: HttpClient) {
  }
  getToken() {
    return this.user.token;
  }
  getUser(user: User) {
    return this.http.get<User>(`http://localhost:8080/api/user/get/${user.id}`, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      catchError(this.handleError<any>('getUser'))
    );
  }
  updateUser(user: User) {
    return this.http.put<User>('http://localhost:8080/api/user/update', user,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      catchError(this.handleError<any>('signUp'))
    );
  }
  createUser(user: User) {
    return this.http.post<User>('http://localhost:8080/api/user/create', user, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      catchError(this.handleError<any>('signUp'))
    );
  }

  deleteUser(user: User) {
    return this.http.delete<User>(`http://localhost:8080/api/user/delete/${user.id}`,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(
      catchError(this.handleError<any>('signUp'))
    );
  }
  signUp(user: User): Observable<any> {
    return this.http.post<User>('http://localhost:8080/api/sign-up', user,{
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'json'
    }).pipe(
      catchError(this.handleError<any>('signUp'))
    );

  }

  login(user): Observable<User> {
    return this.http.post<any>('http://localhost:8080/api/login', user,{
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'json'
    }).pipe(
      tap(response => {
        this.user = response;
        this.authenticated = true}),
      catchError(this.handleError<any>('login'))
    );
  }
  logout() {
    return this.http.post<User>('http://localhost:8080/api/logout', this.user, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(finalize(() => {
      this.authenticated = false;
    }), catchError(this.handleError<any>('logout')))
  }

  private handleError<T>(operation= 'opeartion') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }
  searchUsers(term: string): Observable<User[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<User[]>(`http://localhost:8080/api/user/search/${term}`, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.getToken()}`)}).pipe(

      catchError(error => {return of([])})
    );
  }


}
