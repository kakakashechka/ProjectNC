import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Quiz} from "../entities/quiz";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {Category} from "../entities/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) {
  }

  getCategory(category: Category) {
    return this.http.get<Category>(`api/category/get/${category.id}`).pipe(
      catchError(this.handleError<any>('getCategory'))
    );
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`api/category/getCategories`).pipe(
      catchError(this.handleError<any>('getCategory'))
    );
  }
  updateCategory(category: Category) {
    return this.http.put<Quiz>('api/category/update', category).pipe(
      catchError(this.handleError<any>('editCategory'))
    );
  }
  createCategory(category: Category) {
    return this.http.post<Category>('api/category/create', category).pipe(
      catchError(this.handleError<any>('createCategory'))
    );
  }

  deleteCategory(category: Category) {
    return this.http.delete<Category>(`api/category/delete/${category.id}`).pipe(
      catchError(this.handleError<any>('deleteCategory'))
    );
  }

  private handleError<T>(operation= 'operation') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }
}
