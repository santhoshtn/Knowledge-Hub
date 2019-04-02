import { Component, OnInit } from '@angular/core';
import { SessionId } from '../domain/sessionId';
import $ from 'jquery'
@Component({
  selector: 'app-toptrending',
  templateUrl: './toptrending.component.html',
  styleUrls: ['./toptrending.component.scss']
})
export class ToptrendingComponent implements OnInit {
  object5:any;
  constructor(private result : SessionId) { }

  ngOnInit() {
    $('button').click(function(){
      $('button').toggleClass('active');
      $('.title').toggleClass('active');
      $('nav').toggleClass('active');
    });
    this.object5 = this.result.searchfreq;
  }

}
