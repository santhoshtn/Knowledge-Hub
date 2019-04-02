import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-zombotron',
  templateUrl: './zombotron.component.html',
  styleUrls: ['./zombotron.component.css']
})
export class ZombotronComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit() {
  }
  search(){
    this.router.navigate(['/search'])
  }

}
