import { Component, OnInit } from '@angular/core';
import { TokenService } from '../services/token.service';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-display-admin-home',
  templateUrl: './display-admin-home.component.html',
  styleUrls: ['./display-admin-home.component.css']
})
export class DisplayAdminHomeComponent implements OnInit {
toke:string
  constructor(private token:TokenService) { 
    this.toke=token.getToken()
  }

  ngOnInit() {
  }

}
