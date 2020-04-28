import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Question} from "../entities/question";
import {catchError} from "rxjs/operators";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private questionsUrl = 'api/question';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient) {
  }
  // getQuestion(question: Question) {
  //   return this.http.get<Question>(`api/question/get/${question.id}`).pipe(
  //     catchError(this.handleError<any>('getQuestion'))
  //   );
  // }
  //
  // updateQuestion(question: Question) {
  //   return this.http.put<Question>('api/question/update', question).pipe(
  //     catchError(this.handleError<any>('editQuestion'))
  //   );
  // }
  // createQuestion(question: Question, quiz_id) {
  //   question.quiz_id = quiz_id;
  //   return this.http.post<Question>('api/question/create', question).pipe(
  //     catchError(this.handleError<any>('createQuestion'))
  //   );
  // }
  //
  // deleteQuestion(question: Question) {
  //   return this.http.delete<Question>(`api/question/delete/${question.id}`).pipe(
  //     catchError(this.handleError<any>('deleteQuestion'))
  //   );
  // }
  //
  // private handleError<T>(operation= 'operation') {
  //   return (error: any): Observable<T> => {
  //     console.log(operation + ' ' + error);
  //     return throwError(error);
  //   };
  // }
  ///FOR TESTING
  getQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>(this.questionsUrl)
      .pipe(
        catchError(this.handleError<Question[]>('getQuestions', []))
      );
  }

  /** POST: add a new question to the server */
  createQuestion(question: Question): Observable<Question> {
    console.table(question);
    return this.http.post<Question>(this.questionsUrl+'/'+question.type.id, question, this.httpOptions).pipe(
      catchError(this.handleError<Question>('createQuestion'))
    );
  }



  deleteQuestion(question: Question | number): Observable<Question> {
    const id = typeof question === 'number' ? question : question.id;
    const url = `${this.questionsUrl}/${id}`;

    return this.http.delete<Question>(url, this.httpOptions).pipe(
      catchError(this.handleError<Question>('deleteQuestion'))
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
