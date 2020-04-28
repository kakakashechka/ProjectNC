import { Component, OnInit } from '@angular/core';
import {AnnouncementService} from "../announcement.service";

@Component({
  selector: 'app-announcement-dashboard',
  templateUrl: './announcement-dashboard.component.html',
  styleUrls: ['./announcement-dashboard.component.css']
})
export class AnnouncementDashboardComponent implements OnInit {

  announcement: Announcements[] = [];

  constructor(private quizService: AnnouncementService) { }

  ngOnInit(): void {
    this.getAnnouncements();
  }

  getAnnouncements(): void {
    this.AnnouncementService.getAnnouncement()
      .subscribe(announcements => this.announcement = announcements);
  }

}
