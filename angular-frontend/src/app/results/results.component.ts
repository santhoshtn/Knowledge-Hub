import { Component, OnInit, Inject, PLATFORM_ID, ViewChild, Injector } from '@angular/core';
import { DataService } from '../domain/data-service';
import { SessionId } from '../domain/sessionId';
import { Router } from '@angular/router';
import { SocketService } from '../services/socket.service';
import { SearchinfoService } from '../services/searchinfo.service';
import $ from 'jquery'
import { MatDialogRef, MatDialog } from '@angular/material';
import { ChatComponent } from '../chat/chat.component';
import { makeStateKey, TransferState } from '@angular/platform-browser';
import { isPlatformServer } from '@angular/common';

const configKey = makeStateKey('CONFIG');
declare var webkitSpeechRecognition: any;


@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {
  searchTerm: string;
  object1:any;
  object2:any;
  object3:any;
  object4:any;
  object5:any;
  flag : number;
  public title : string;
  @ViewChild('gSearch') formSearch;
  @ViewChild('searchKey') hiddenSearchHandler;

  chatComponent: MatDialogRef<ChatComponent>;


  constructor(    private dataService: DataService,
    private result : SessionId,    private searchService : SearchinfoService,
    private router : Router,
    private injector: Injector,
    private state : TransferState,
    private session : SocketService,
    private dialog:MatDialog,
    @Inject(PLATFORM_ID) private platformid: Object) { 
      this.title = 'Voice Search POC';
      if(isPlatformServer(this.platformid)){
        const envJson = this.injector.get('CONFIG')? this.injector.get('CONFIG'): {};
        this.state.set(configKey, envJson as any);}
    }

  ngOnInit() {
    this.router.navigate(['/result/resultcard']);
    this.searchTerm = this.dataService.dataService
    $(document).ready(function () {
      $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
          $('.scroll-top').fadeIn();
        } else {
          $('.scroll-top').fadeOut();
        }
      });
    
      $('.scroll-top').click(function () {
        $("html, body").animate({
          scrollTop: 0
        }, 100);
          return false;
      });
    
    });

  }
  search(){
    var output = {
      sessionId : this.result.SessionId,
      searchString : this.searchTerm
    };
    this.result.nlpresult=[];
    this.result.pdfresult=[];
    this.result.recommendation=[];
    this.result.searchfreq=[];
    this.result.webresult=[];
    this.dataService.dataService=this.searchTerm
    this.searchService.postResults(output).subscribe(data=>{ 
    this.object1 = this.result.pdfresult;
    this.object2 = this.result.webresult;
    this.object3 = this.result.recommendation;
    this.object4 = this.result.nlpresult;
    this.object5 = this.result.searchfreq;

    });
    this.router.navigateByUrl('/search', {skipLocationChange: true}).then(()=>
    this.router.navigate(["/result/resultcard"]));
 
  }
  openDialog() {
    this.chatComponent = this.dialog.open(ChatComponent,{ disableClose : true})
  }

  /****voice search************/
  voicesearch(){
    if('webkitSpeechRecognition' in window){
      const vSearch = new webkitSpeechRecognition();
      this.flag=1;
      vSearch.continuous = false;
      vSearch.interimresults = false;
      vSearch.lang = 'en-US';
      vSearch.start();
      const voiceSearchForm = this.formSearch.nativeElement;
      const voiceHandler = this.hiddenSearchHandler.nativeElement;
      vSearch.onresult = function(event){
        //storing the result in value
        //returns a string containing the transcript of the recognised word(s).
        voiceHandler.value = event.results[0][0].transcript;
        window['searchTerm'] = voiceHandler.value;
        
        }

        vSearch.onend = (e) => {
          console.log("Ended", e);
          this.search();
        }
 
      //if error is encountered, show error in console and stop the speech recognition
      vSearch.onerror = function(event){
          console.log(event);
          vSearch.stop();
      }
 
 
  }
  //if browser does not have webkitspeechrecognition
  else {
    console.log("Your browser does not support voice recognition feature.");
    }
  }


}
