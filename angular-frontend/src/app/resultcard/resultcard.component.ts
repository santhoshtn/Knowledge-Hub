import { Component, OnInit } from '@angular/core';
import { SessionId } from '../domain/sessionId';
import { DataService } from '../domain/data-service';

@Component({
  selector: 'app-resultcard',
  templateUrl: './resultcard.component.html',
  styleUrls: ['./resultcard.component.css']
})
export class ResultcardComponent implements OnInit {

  searchTerm: string;
  object1:any;
  flag:boolean = true;
  var1="";
  constructor( private dataService: DataService,
    private result : SessionId) { }

  ngOnInit() {
    this.searchTerm = this.dataService.dataService;
    this.object1 = this.result.pdfresult;
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
