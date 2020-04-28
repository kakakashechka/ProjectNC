import { Component, OnInit } from '@angular/core';
import { Quiz } from '../entities/quiz';
import { QuizService } from '../services/quiz.service';

@Component({
  selector: 'app-quiz-dashboard',
  templateUrl: './quiz-dashboard.component.html',
  styleUrls: ['./quiz-dashboard.component.css']
})
export class QuizDashboardComponent implements OnInit {
  quizes: Quiz[] = [];

  constructor(private quizService: QuizService) { }

  ngOnInit(): void {
    this.getQuizes();
  }

  getQuizes(): void {
    this.quizService.getQuizes()
      .subscribe(quizes => this.quizes = quizes);
  }


}
