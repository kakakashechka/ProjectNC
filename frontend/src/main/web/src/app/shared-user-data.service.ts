import { Injectable } from '@angular/core';
import { User } from './entities/user';

@Injectable({
  providedIn: 'root'
})
export class SharedUserDataService {
  userData: User;
  empty = true;

  constructor() {
    this.userData = {} as User;
   }
   setUserData(val: User){
    this.empty = false;
    this.userData= val;
  }
  getUserData(){
    this.empty = true;
    return this.userData;
  }
  reload(){
    window.location.reload();
  }

}
