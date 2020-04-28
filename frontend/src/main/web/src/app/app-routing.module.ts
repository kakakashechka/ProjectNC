import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthorizationComponent} from "./authorization/authorization.component";
import {HomeComponent} from "./home/home.component";
import {AppComponent} from "./app.component";
import {RegistrationComponent} from "./registration/registration.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {UserProfileComponent} from "./user-profile/user-profile.component"

import {AnnouncementEditComponent} from "./announcement-edit/announcement-edit.component";
import {AnnouncementDashboardComponent} from "./announcement-dashboard/announcement-dashboard.component";

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home'},
  { path: 'dashboard/:tab', component: DashboardComponent},
  { path: 'dashboard', pathMatch: 'full', redirectTo: '/dashboard/quizzes'},
  { path: 'home', component: HomeComponent},
  { path: 'login', component: AuthorizationComponent},
  { path: 'signup', component: RegistrationComponent },
  { path: 'profile', component: UserProfileComponent },
  { path: 'announcement-create', component: AnnouncementEditComponent },
  { path: 'announcement-edit/:id', component: AnnouncementEditComponent },
  { path: 'announcement-dashboard', component: AnnouncementDashboardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
