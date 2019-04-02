import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ApiAiClient } from 'api-ai-javascript/es6/ApiAiClient';
import { environment } from 'src/environments/environment';
import { BehaviorSubject } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { SessionId } from '../domain/sessionId';
import { SearchinfoService } from './searchinfo.service';
import { Router } from '@angular/router';

export class Message {
  constructor(public content: string, public sentBy: string) {}
}
@Injectable({
  providedIn: 'root'
})
export class ChatbotService {

  object : any;
  // readonly token = environment.dialogflow.angularBot;
  readonly token = '7598a589d5494f2ba0170e29fbb7392d';
  readonly client = new ApiAiClient({ accessToken: this.token });

  conversation = new BehaviorSubject<Message[]>([]);

  constructor(private dialog:MatDialog,private http:HttpClient,private SessionIdNew:SessionId,private searchService : SearchinfoService, private result : SessionId, private router : Router) {}

  // Sends and receives messages via DialogFlow
  converse(msg: string) {
    const userMessage = new Message(msg, 'user');
    this.update(userMessage);

    return this.client.textRequest(msg)
               .then(res => {
                  const speech = res.result.fulfillment.speech;
                  if (speech=='so you want to search about spring Close this bot and Hold on...')
                  {
                    console.log("inside if of speech");
                    const botMessage1 = new Message(speech, 'bot');
                    this.update(botMessage1);
                    const botMessage2=new Message(null,null)
                    this.update(botMessage2);
                    // this.navigate();
                  this.closeDialog(msg);
                  }
                  else{
                    const botMessage = new Message(speech, 'bot');
                    this.update(botMessage);
                  }
                 
               });}

        // Adds message to source
  update(msg: Message) {
    this.conversation.next([msg]);
  }

  closeDialog(text:string){
    var output = {
      sessionId : this.SessionIdNew.SessionId,
      searchString : text
    };
    setTimeout(()=>{    
      this.dialog.closeAll();
 }, 3000);   
  console.log(output);
 
   this.searchService.postResults(output).subscribe();
   this.router.navigate(['/result/resultcard']);

  }
}
