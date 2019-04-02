import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Message, ChatbotService } from '../services/chatbot.service';
import 'rxjs/add/operator/scan';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css','../../assets/conceptsLib/font-awesome/css/font-awesome.min.css',
  '../../assets/conceptsLib/animate/animate.min.css','../../assets/conceptsLib/ionicons/css/ionicons.min.css',
  '../../assets/conceptsLib/owlcarousel/assets/owl.carousel.min.css','../../assets/conceptsLib/magnific-popup/magnific-popup.css',
'../../assets/conceptsLib/ionicons/css/ionicons.min.css','../../assets/conceptsLib/bootstrap/css/bootstrap.min.css']
})
export class ChatComponent implements OnInit {

  messages: Observable<Message[]>;
  formValue: string;
  constructor(public chat: ChatbotService,private route:Router) { }

  ngOnInit() {
    this.messages = this.chat.conversation.asObservable()
    .scan((acc, val) => acc.concat(val) );
  }
  sendMessage() {
    this.chat.converse(this.formValue);
    this.formValue = '';
    var elem = document.getElementById('text');
    if(elem){
    window.setInterval(function() {
      
      elem.scrollTop = elem.scrollHeight;
    }, 50);
  }
}

}
