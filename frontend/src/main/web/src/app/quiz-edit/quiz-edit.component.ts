import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {Component, OnInit, Input, OnDestroy} from '@angular/core';
import {Quiz} from "../entities/quiz";
import {Question} from "../entities/question";
import {QuestionService} from "../services/question.service";
import {Option} from "../entities/option";
import {DefaultOption} from "../entities/default-option"
import {Subscription} from "rxjs";
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";
import {SequenceOption} from "../entities/sequence-option";

@Component({
  selector: 'app-quiz-edit',
  templateUrl: './quiz-edit.component.html',
  styleUrls: ['./quiz-edit.component.css']
})
export class QuizEditComponent implements OnInit, OnDestroy {
  questions: Question[] = [];
  titleEditor: any;
  question: Question = {
    id: null,
    time: 15,
    options: 1,
    type: {id: '1', name: ''},
    text: '',
    quiz_id: null,
    image: ''
  };
  questionToAdd: Question = {
    id: null,
    time: 15,
    options: 1,
    type: {id: '1', name: ''},
    text: '',
    quiz_id: null,
    image: ''
  };
  numberOfOptions = 4;
  options: Option[] = Array.from({length: this.numberOfOptions},()=>
    ({
      is_correct: false,
      text: ''
    }))
  answerTrueFalse = 'False';
  answerTypeAnswer = '';
  optionsSequence: SequenceOption[] = Array.from({length: this.numberOfOptions},()=>
    ({
      serial_num: null,
      text: ''
    }))



  isAddQuestion = false;
  selectedLevel: any;
  private routeSub: Subscription;
  defaultOption: DefaultOption = {answer: ''};


  constructor(
    private route: ActivatedRoute,
    private questionService: QuestionService,
    private location: Location
  ) { }

  ngOnInit(): void {

      // console.log(params); //log the entire params object
      //console.log(params['id']); //log the value of id

      //console.log(this.question.quiz_id);
       this.getQuestions();

  }
  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }
  showAdd() {
    this.titleEditor = 'Add a question';
    this.question = this.questionToAdd;
    this.isAddQuestion = !this.isAddQuestion;
  }
  showEdit(action: string, question: Question) {
    this.titleEditor = 'Edit a question';
    this.question = question;
    this.isAddQuestion = !this.isAddQuestion;
  }




  //CRUD
  getQuestions(): void {
    this.questionService.getQuestions()
      .subscribe(questions => {
        for (let question of questions) {
          if (question.quiz_id === this.question.quiz_id) {
            this.questions.push(question);
          }
        }
    }
    );
  }
  createQuestion() {
    let someOption=[];
    if (this.question.type.id === '1') {
      this.defaultOption.answer = this.answerTrueFalse;
      someOption.push(this.defaultOption);
    } else if (this.question.type.id === '2') {
      this.defaultOption.answer = this.answerTypeAnswer;
      someOption.push(this.defaultOption);
    } else if (this.question.type.id === '3') {
      someOption = this.options;
    } else if (this.question.type.id === '4') {
      for (let i = 0; i < this.optionsSequence.length; i++) {
        this.optionsSequence[i].serial_num = i + 1;
      }
      someOption = this.optionsSequence;
    }
    console.log("question created");
    this.routeSub = this.route.params.subscribe(params => {
      this.question.quiz_id = params['id'];
    });
    this.questionService.createQuestion({
        options: someOption,
        text: this.question.text,
        type: {id: this.question.type.id},
        time: this.question.time,
        max_points:null,
        quiz_id: this.question.quiz_id
    } as Question)
      .subscribe(data  => {
        this.question.id = data.id;
        console.log(data.id);
        this.questions.push(this.question);
      });
  }


  deleteQuestion(question: Question): void {
    this.questions = this.questions.filter(q => q !== question);
    this.questionService.deleteQuestion(question.id).subscribe();
  }

  isCorrectOption(option: Option) {
    option.is_correct = !option.is_correct;
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.optionsSequence, event.previousIndex, event.currentIndex);
  }


}
