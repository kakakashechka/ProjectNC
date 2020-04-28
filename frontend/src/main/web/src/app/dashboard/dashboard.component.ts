import {Component, OnInit, Input, ViewChild, AfterViewChecked, AfterViewInit} from '@angular/core';
import {UserService} from "../user.service";
import { User } from '../entities/user';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';
import { SharedUserDataService } from '../shared-user-data.service';
import {UserCardComponent} from "../user-card/user-card.component";
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, AfterViewChecked, AfterViewInit {

  tab: string = '';
  param = '';
  user: User;
  users$: Observable<User[]>;
  private searchTerms = new Subject<string>();
  @ViewChild(UserCardComponent) viewChild: UserCardComponent;

  constructor(private userService: UserService,private location: Location, private route: ActivatedRoute,private router: Router,private sharedData: SharedUserDataService) { }

  ngOnInit(): void {
    this.tab = this.route.snapshot.paramMap.get('tab');
    this.user = this.userService.user;
    this.users$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((term: string) => this.userService.searchUsers(term)),
    );
  }
  ngAfterViewInit() {


  }

  ngAfterViewChecked() {
    // viewChild is updated after the view has been checked
    if (true === this.viewChild.edit) {
      this.tab = 'Profile';
      // this.sharedData

    }
  }


  search(term: string): void {
    this.searchTerms.next(term);
  }
profile()
{
  this.tab = 'Profile';
  this.sharedData.setUserData(this.userService.user);

}
emptyProfile() {
  this.tab = 'Profile';
  this.param = 'true'
  this.sharedData.setUserData({role: {name: 'moderator'}} as User);
}
  changeTab(tab: string, param?: string) {
    this.tab = tab;
    this.router.navigateByUrl(`dashboard/${tab}`);
    this.param = param;
  }


  getUser(empty?: string) {

    return this.sharedData.getUserData();
  }
}
