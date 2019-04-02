import { Component, OnInit } from '@angular/core';
import { QuestionProviderService } from '../services/question-provider.service';
import { QueryQuestions } from './QueryQuestions';
import { QuestionPublisherService } from '../services/question-publisher.service';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-question-classify',
  templateUrl: './question-classify.component.html',
  styleUrls: ['./question-classify.component.css'],
  providers: [QuestionProviderService,QuestionProviderService]
})
export class QuestionClassifyComponent implements OnInit {

   i: number = 0;
   intent: string;
  private finisher:boolean = true;
  private input1:string;
  private questions: QueryQuestions[] = [];
  private numQues: number;
  private currQuesNum: number;
  private currentQuestion: string;
  private currentQuestionObject: QueryQuestions;
  constructor(public token:TokenService,private questionProvider: QuestionProviderService,private questionPublisher:QuestionPublisherService) {

  }
  ngOnInit(): void {
    console.log("I am in onInit method");
    this.questionProvider.getAllQuestions().subscribe(
      QueryQuestions => {
        this.questions = QueryQuestions;
        if(this.questions == null ||this.questions.length==0){
          this.currentQuestion = "No questions to display";
          this.finisher=false;
          this.currQuesNum=0;
          this.numQues=0;
        }else{
          this.currentQuestion = this.questions[0].searchString;
          this.showQuestions(0, this.questions);
          this.numQues = this.questions.length;
          this.currQuesNum = 1;
        }
        console.log("In oonn method");
      });
  }

  showQuestions(index: number, queryQuestionsArray: QueryQuestions[]): void {
      this.currentQuestionObject = queryQuestionsArray[index];
      this.currentQuestion = this.currentQuestionObject.searchString;
  }

  getNext(): void {
    if (this.i == this.questions.length - 1) {
      this.questionPublisher.deleteQuestion(this.questions[this.i].uniqueId);
      this.currentQuestion = "Done with all the questions!";
      this.finisher=false;
    }
    else {
      this.i++;
      this.currQuesNum++;
      console.log("index: "+this.i);
      this.showQuestions(this.i, this.questions);
      console.log("delete: "+this.questions[this.i-1].uniqueId);
      this.questionPublisher.deleteQuestion(this.questions[this.i-1].uniqueId);
    }

  }

  sendKnowledgeData(): void {
    this.questionPublisher.publishQuestion("Knowledge",this.input1,this.currentQuestionObject.uniqueId);
  }

  sendComprehensionData(): void {
    this.questionPublisher.publishQuestion("Comprehension",this.input1,this.currentQuestionObject.uniqueId);

  }

  sendApplicationData(): void {
    this.questionPublisher.publishQuestion("Application",this.input1,this.currentQuestionObject.uniqueId);

  }

  sendAnalysisData(): void {
    this.questionPublisher.publishQuestion("Analysis",this.input1,this.currentQuestionObject.uniqueId);

  }

  sendSynthesisData(): void {
    this.questionPublisher.publishQuestion("Synthesis",this.input1,this.currentQuestionObject.uniqueId);

  }

  sendEvaluationData(): void {
    this.questionPublisher.publishQuestion("Evaluation",this.input1,this.currentQuestionObject.uniqueId);

  }
}
