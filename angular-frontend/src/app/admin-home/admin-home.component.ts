import { Component, OnInit } from '@angular/core';
import {WOW} from 'wowjs/dist/wow.min'
import { Token } from '@angular/compiler/src/ml_parser/lexer';
import { TokenService } from '../services/token.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { UploadComponent } from '../upload/upload.component';
@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css','../../assets/conceptsLib/font-awesome/css/font-awesome.min.css',
  '../../assets/conceptsLib/animate/animate.min.css','../../assets/conceptsLib/ionicons/css/ionicons.min.css',
  '../../assets/conceptsLib/owlcarousel/assets/owl.carousel.min.css','../../assets/conceptsLib/magnific-popup/magnific-popup.css',
'../../assets/conceptsLib/ionicons/css/ionicons.min.css','../../assets/conceptsLib/bootstrap/css/bootstrap.min.css']
})
export class AdminHomeComponent implements OnInit {
  tok:string;
  constructor(private token:TokenService,private router:Router,private dialog:MatDialog) { 
    this.tok=this.token.getToken();
  }

  ngOnInit() {
      new WOW().init();
    }
    showNlpQuestions(){
        this.router.navigate(['/showNlpQuestions']);
    }
    uploadFile(){
      this.dialog.open(UploadComponent)
    }
    addDomainConceptNames(){
      this.router.navigate(['/addDomainConceptNames']);
    }

    modifyNeo4j(){
      this.router.navigate(['/modifyNeo4j']);
    }
  }


