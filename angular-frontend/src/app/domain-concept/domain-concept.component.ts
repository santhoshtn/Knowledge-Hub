import { Component, OnInit } from '@angular/core';
import { DomainConceptNameService } from '../services/domain-concept-name.service';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-domain-concept',
  templateUrl: './domain-concept.component.html',
  styleUrls: ['./domain-concept.component.css'],
  providers:[DomainConceptNameService]

})
export class DomainConceptComponent implements OnInit {
  private domain:string;
  private concepts:string;
  private resetButton:any;
  private output:string;
  constructor(private domainConceptNameService:DomainConceptNameService,public token:TokenService){

  }
  ngOnInit() {

  }
  publishDomainConceptName(){
    console.log("publish domain concept name 122");
    console.log("publish domain concept name 133");
    this.output=this.domainConceptNameService.postDomainConceptName(this.domain,this.concepts);
    console.log("publish domain concept name 144");
    this.domain='';
    this.concepts='';
  }
}
