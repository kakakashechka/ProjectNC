import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Observable, of, throwError} from "rxjs";
import {Quiz} from "../entities/quiz";
import {UserService} from "../user.service";

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private quizesUrl = 'api/quiz';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient, private userService: UserService) {
  }
  // getQuiz(quiz: Quiz) {
  //   return this.http.get<Quiz>(`api/quiz/get/${quiz.id}`).pipe(
  //     catchError(this.handleError<any>('getQuiz'))
  //   );
  // }
  // updateQuiz(quiz: Quiz) {
  //   return this.http.put<Quiz>('api/quiz/update', quiz).pipe(
  //     catchError(this.handleError<any>('editQuiz'))
  //   );
  // }
  // // createQuiz(quiz: Quiz) {
  // //   quiz.userId = this.userService.user.id;
  // //   return this.http.post<Quiz>('api/quiz/create', quiz).pipe(
  // //     catchError(this.handleError<any>('createQuiz'))
  // //   );
  // // }
  //
  // deleteQuiz(quiz: Quiz) {
  //   return this.http.delete<Quiz>(`api/quiz/delete/${quiz.id}`).pipe(
  //     catchError(this.handleError<any>('deleteQuiz'))
  //   );
  // }
///FOR TESTING
  getQuizes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.quizesUrl)
      .pipe(
        catchError(this.handleError<Quiz[]>('getQuizes', []))
      );
  }

  /** POST: add a new quiz to the server */
  createQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.post<Quiz>(this.quizesUrl, quiz, this.httpOptions).pipe(
      catchError(this.handleError<Quiz>('createQuiz'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
