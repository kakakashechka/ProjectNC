import { Component, OnInit } from '@angular/core';
import {Announcement} from "../entities/announcement";
import {UserService} from "../user.service";
import {AnnouncementService} from "../announcement.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-announcement-edit',
  templateUrl: './announcement-edit.component.html',
  styleUrls: ['./announcement-edit.component.css']
})
export class AnnouncementEditComponent implements OnInit {


  announcement: Announcement = {
    id: '8',
    title: 'New epic big super announcement',
    text: 'Some quick example text to build on the card title and make up the bulk of the card`s content.',
    userId: '1',
    date: '2019-01-21T05:47:08.644',
    statusId: '1',
    categoryId: '1'


  };
  error = ''
  message = '';

  announcementForm: FormGroup;

  constructor(private fb: FormBuilder, private announcementService: AnnouncementService, private userService: UserService) { }

  ngOnInit(): void {
    this.announcementForm = this.fb.group({
      title: [this.announcement.title, [Validators.required, Validators.minLength(3), Validators.maxLength(40)]],
      text: [this.announcement.text, [Validators.required, Validators.minLength(3), Validators.maxLength(499)]],
      image: [this.announcement.image],

    });
  }

  save() {
    this.announcement.title = this.announcementForm.get('title').value;
    this.announcement.text = this.announcementForm.get('text').value;
    this.announcementService.updateAnnouncement(this.announcement).subscribe(resp => {this.message = resp.message},
      error => {this.error = error.message});
  }

  add() {
    this.announcement.title = this.announcementForm.get('title').value;
    this.announcement.text = this.announcementForm.get('text').value;
    this.announcement.userId = this.userService.user.id;
    this.announcementService.createAnnouncement(this.announcement).subscribe(resp => {this.message = resp.message},
      error => {this.error = error.message});
  }

}
