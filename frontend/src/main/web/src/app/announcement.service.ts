import { Injectable } from '@angular/core';
import {User} from "./entities/user";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Announcement} from "./entities/announcement";
import {UserService} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {

  private announcementUrl = 'api/quiz';

  constructor(private http: HttpClient, private userService: UserService) { }
  header = {
    headers: new HttpHeaders()
      .set('Authorization',  `Bearer_${this.userService.getToken()}`)
  }
  private handleError<T>(operation= 'opeartion') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }

  getAnnouncement(): Observable<Announcement[]> {
    return this.http.get<Announcement[]>(this.announcementUrl)
      .pipe(
        catchError(this.handleError<Announcement[]>('getAnnouncement'))
      );
  }

  deleteAnnouncement(ann: Announcement) {
    return this.http.delete<Announcement>(`http://localhost:8080/api/announcement/delete/${ann.id}`,{
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('deleteAnnouncement'))
    );
  }
  createAnnouncement(ann: Announcement) {
    return this.http.post<Announcement>('http://localhost:8080/api/announcement/create', ann, {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('createAnnouncement'))
    );
  }

  updateAnnouncement(ann: Announcement) {
    return this.http.put<Announcement>(`http://localhost:8080/api/announcement/update`, ann, {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('updateAnnouncement'))
    );
  }

}
