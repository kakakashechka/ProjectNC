import { Component, OnInit } from '@angular/core';
import {QuizService} from "../services/quiz.service";
import {Category} from "../entities/category";
import {Quiz} from "../entities/quiz";
import {CategoryService} from "../services/category.service";
import {ActivatedRoute, Router} from "@angular/router";
import { Location } from '@angular/common';
import {UserService} from "../user.service";


@Component({
  selector: 'app-quiz-answer',
  templateUrl: './quiz-answer.component.html',
  styleUrls: ['./quiz-answer.component.css']
})
export class QuizAnswerComponent implements OnInit {
  quiz: Quiz = {
    id: null,
    title: '',
    description: '',
    user_id: this.userService.user.id
    //user_id: '5'
  };
  isIdGet = false;
  receivedQuiz: Quiz;
  categories: Category[] = [];

  constructor(
    private location: Location,
    private route: ActivatedRoute,
    private router: Router,
    private quizService: QuizService,
    private userService: UserService,
    private categoryService: CategoryService
  ) { }


  ngOnInit(): void {
    this.quiz.user_id=this.userService.user.id;
    // this.getData();
  }

  goBack(): void {
    this.location.back();
  }
  createQuiz() {
    console.log("quiz answered");
    this.quizService.answerQuiz({
      title: this.quiz.title,
      description: this.quiz.description,
      user_id: this.quiz.user_id
    } as Quiz)
      .subscribe(data  => {
        console.log(data.id);
        this.quiz.id = data.id;
        this.router.navigate(['/quiz-dashboard/' + this.quiz.id ]);
      });

  }


  // getCategories(): void {
  //   this.categoryService.getCategories()
  //     .subscribe((categories: Category) =>
  //       this.categories = categories);
  // }
}
