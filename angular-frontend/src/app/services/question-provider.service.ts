import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { QueryQuestions } from '../question-classify/QueryQuestions';
import { Observable } from 'rxjs';
import {tap} from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class QuestionProviderService {
  private questions: QueryQuestions[];

  public questionProviderService = 'https://13.234.94.132:8092/question-provider/getAllQueryQuestions';
  public question_arr: QueryQuestions[];

  constructor(private httpclient: HttpClient) {
  }

  public getAllQuestions(): Observable<QueryQuestions[]> {
    console.log("get all questions");
    return this.httpclient.get<QueryQuestions[]>(this.questionProviderService).pipe(
      tap(data => console.log('All: ' + JSON.stringify(data))));
  }
}
