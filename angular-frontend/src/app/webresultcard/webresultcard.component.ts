import { Component, OnInit } from '@angular/core';
import { SessionId } from '../domain/sessionId';

@Component({
  selector: 'app-webresultcard',
  templateUrl: './webresultcard.component.html',
  styleUrls: ['./webresultcard.component.css']
})
export class WebresultcardComponent implements OnInit {

  var1="";
  object2:any;

  flag:boolean = true;
  constructor(private result : SessionId) { }

  ngOnInit() {
    this.object2 = this.result.webresult;

  }
  assignVar(data){
    if(this.flag){
    this.var1=data;
    this.flag=false;
  }else{
    this.var1="";
    this.flag=true;
  }
  }

}
