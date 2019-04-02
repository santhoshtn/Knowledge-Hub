import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Observable } from 'rxjs';
import {WOW} from 'wowjs/dist/wow.min'
import { Intents } from '../intent';
import { TermComponent } from '../neo4j-term/neo4j-term.component';
import { Neo4jService } from '../services/neo4j.service';
import { Router } from '@angular/router';
import { D3Service, Link,Node } from '../d3/d3';

@Component({
  selector: 'app-neo4j-node',
  templateUrl: './neo4j-node.component.html',
  styleUrls: ['./neo4j-node.component.css','../../assets/conceptsLib/font-awesome/css/font-awesome.min.css',
  '../../assets/conceptsLib/animate/animate.min.css','../../assets/conceptsLib/ionicons/css/ionicons.min.css',
  '../../assets/conceptsLib/owlcarousel/assets/owl.carousel.min.css','../../assets/conceptsLib/magnific-popup/magnific-popup.css',
'../../assets/conceptsLib/ionicons/css/ionicons.min.css','../../assets/conceptsLib/bootstrap/css/bootstrap.min.css']
})
export class NodeComponent implements OnInit {
  openform: boolean;
  openConcept: boolean;
  openProperty: boolean;
  value: any;
  openIntent: boolean;
  selectedLevel:string;
  data:string;
  field:any;
  terms:String;
  intents: Observable<Intents[]>;
  allTerms=[];
  bloomstack:string;
  msg:any;
  msg1: string;
  hide:string;
  getTerms1: boolean;
  nodes: Node[] = [];
  links: Link[] = [];
  child:string;
  parent:string;
  showNodes:boolean;

  constructor(private formService:Neo4jService,public dialog:MatDialog,private router:Router,private d3:D3Service) { }

  ngOnInit() {
    this.hide='hide';
    this.showNodes=false;
    new WOW().init();
  }

  onClickOpenForm(){
    this.openform=true;  
    }

  onClickOpenConceptForm(){
    this.openConcept=true;
    this.openIntent=false;
    this.getTerms1=false;
    this.openProperty=false;
    this.fieldArray.length=0;
    this.hide='hide';
    this.intents=null;
    this.bloomstack=null;
    this.showNodes=false;
  } 

  onClickOpenIntentForm(){
    this.openIntent=true;
    this.openProperty=false;
    this.hide='hide';
    this.intents=null;
    this.bloomstack=null;
    this.openConcept=false;
    this.getTerms1=false;
    this.fieldArray.length=0;
    this.showNodes=false;
  }

  onClickOpenTerms(){
    this.getTerms1=true;
    this.openIntent=false;
    this.openConcept=false;
    this.fieldArray.length=0;
  }

  selected(){
    this.data=this.selectedLevel;
    this.fieldArray.length=0;
    this.openProperty=false;
    // this.field.property='';
  }
  
  showProperties(){
    this.openProperty=true;
  }


  fieldArray: Array<any> = [];
  newAttribute: any = {};

  addFieldValue() {
      this.fieldArray.push(this.newAttribute)
      this.newAttribute = {};
  }

  deleteFieldValue(index) {
    this.fieldArray.splice(index, 1);
}


addLastProperty(){
  this.fieldArray.push(this.newAttribute);
}


createNode(form) {
  delete form.newAttributeProperty;
  delete form.newAttributeValue;
  this.value = form; 
  this.child=form.name;
  this.parent=form.parent;
  this.showNodes=true;
  console.log(this.value);
  console.log(form.name,form.parent);
     
     
  this.formService.createNode(form.name,form.parent)
  .subscribe(
    data => {
      this.msg="Added succesfully"},
    error => {
     this.msg="Failed to add node";}
  );
}
createIntent(form) {
  delete form.newAttributeProperty;
  delete form.newAttributeValue;
  this.value = form; 
  this.child=form.name;
  this.parent=form.parent_node_type;
  this.showNodes=true;
  console.log(form.name,form.parent_node_type,form.weight)
  console.log(this.value);
  this.formService.createIntent(form.name,form.parent_node_type,form.weight)
  .subscribe(
    data => {
      
      this.msg1="Added succesfully"},
    error => {
     this.msg1="Failed to add intent node";}
  );
}




onClickShowKnowledge(){
  if(this.hide=='hide'){
  this.bloomstack="Knowledge";
  this.hide='open'
  this.intents = this.formService.getKnowledgeTerms();
  console.log(this.intents);}
  else{this.intents=null
     this.hide='hide'
  }
}
onClickShowSynthesis(){
  if(this.hide=='hide'){
  this.bloomstack="Synthesis";
  this.hide='open'
  this.intents = this.formService.getSynthesisTerms();
  console.log(this.intents);}
  else{this.intents=null
    this.hide='hide'}
}

onClickShowComprehension(){
  if(this.hide=='hide'){
  this.bloomstack="Comprehension";
  this.hide='open'
  this.intents = this.formService.getComprehensionTerms();
  console.log(this.intents);}
  else{this.intents=null
    this.hide='hide'}
}
onClickShowAnaylsis(){
  if(this.hide=='hide'){
    this.bloomstack="Analysis";
    this.hide='open'
    this.intents = this.formService.getAnalysisTerms();
  console.log(this.intents);}
  else{this.intents=null
    this.hide='hide'}
  }
onClickShowApplication(){
  if(this.hide=='hide'){
    this.bloomstack="Application";
    this.hide='open'
    this.intents = this.formService.getApplicationTerms();
  console.log(this.intents);}
  else{this.intents=null
    this.hide='hide'}
  }
onClickShowEvaluation(){
  if(this.hide=='hide'){
    this.bloomstack="Evaluation";
    this.hide='open'
    this.intents = this.formService.getEvaluationTerms();
    console.log(this.intents);}
    else{this.intents=null
    this.hide='hide'}
  }

// getSynonym(terms:String){

//   this.formService.getSynonyms(terms).subscribe((data) => {
//     this.allTerms=data;
//     console.log(this.allTerms);
// })
// }
onD3(){
  this.router.navigate(['/showD3'])
}

openDialog(terms,bloomstack): void {
  console.log(terms);
  console.log(bloomstack);
  // const dialogRef = this.dialog.open(TermComponent, {
  //   width: '400px',
  //   data: {terms: this.terms}
  // });

  const dialogRef=this.dialog.open(TermComponent,{data:{term1:terms,intent:bloomstack}});

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
  });
}




}
