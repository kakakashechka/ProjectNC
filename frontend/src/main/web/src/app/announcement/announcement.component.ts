import { Component, OnInit } from '@angular/core';
import {Announcement} from "../entities/announcement";
import {AnnouncementService} from "../announcement.service";
import {UserService} from "../user.service";

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit {

  announcement: Announcement = {
    id: '1',
    title: 'New epic big super announcement',
    text: 'Some quick example text to build on the card title and make up the bulk of the card`s content.',
    date: '',
    userId: '1'
  };
  error = '';


  constructor(private announcementService: AnnouncementService, private userService: UserService) { }

  getRole() {
    return this.userService.user.role.name;
  }
  delete() {
    this.announcementService.deleteAnnouncement(this.announcement).subscribe(resp => {window.location.reload()},
        error => {this.error = error.message});
  }
  ngOnInit(): void {
  }

}
