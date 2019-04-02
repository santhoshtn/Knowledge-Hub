import { Component, OnInit } from '@angular/core';
import { SessionId } from '../domain/sessionId';

@Component({
  selector: 'app-topsearch',
  templateUrl: './topsearch.component.html',
  styleUrls: ['./topsearch.component.scss']
})
export class TopsearchComponent implements OnInit {
  object4:any;
  constructor(private result : SessionId) { }

  ngOnInit() {
    this.object4 = this.result.nlpresult;

  }

}
