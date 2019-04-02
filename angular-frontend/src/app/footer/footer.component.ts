import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { LoginComponent } from '../login/login.component';
import { DataService } from '../domain/data-service';
import { TokenService } from '../services/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css',
  '../../assets/footer/css/bootstrap.min.css',
  '../../assets/footer/css/font-awesome.min.css',
  '../../assets/footer/css/animate.min.css',
  '../../assets/footer/css/prettyPhoto.css',
  '../../assets/footer/css/style.css',
  '../../assets/footer/css/responsive.css'

// <link href="css/bootstrap.min.css" rel="stylesheet">
//   <link rel="stylesheet" href="css/font-awesome.min.css">
//   <link href="css/animate.min.css" rel="stylesheet">
//   <link href="css/prettyPhoto.css" rel="stylesheet">
//   <link href="css/style.css" rel="stylesheet">
//   <link href="css/responsive.css" rel="stylesheet">
]
})
export class FooterComponent implements OnInit {
adminLogin:string;
  constructor(private dialog:MatDialog,private data:DataService,private token:TokenService,private router:Router) { }

  ngOnInit() {
    this.adminLogin=this.data.login;
  }
login(){
  if(!this.token.getToken()){
  this.dialog.open(LoginComponent);
  }
else{
  
    this.token.signout();
    this.data.login="Admin Login";
    this.adminLogin = this.data.login;
    this.router.navigate(['/home'])
}
}
}
