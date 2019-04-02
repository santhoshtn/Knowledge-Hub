import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navig-intent',
  templateUrl: './navig-intent.component.html',
  styleUrls: ['./navig-intent.component.css']
})
export class NavigIntentComponent{

  constructor(private router:Router) { }


  ngOn() {
    this.router.navigate(['/displayIntent'])
  }

}
