import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Neo4jService } from '../services/neo4j.service';

@Component({
  selector: 'app-neo4j-term',
  templateUrl: './neo4j-term.component.html',
  styleUrls: ['./neo4j-term.component.css']
})
export class TermComponent {
  allTerms: [];
  word: string;
  value: [];
  synonym:String;
  bloom: any;
  msg: string;

 

  constructor(
    public dialogRef: MatDialogRef<TermComponent>,
    @Inject(MAT_DIALOG_DATA) data, public formService:Neo4jService) {
      console.log(data.term1);
      console.log(data.intent);
      this.bloom=data.intent;
      this.word=data.term1;
      this.getSynonym(data.term1);

    }

  onNoClick(): void {
    this.dialogRef.close();
  }


getSynonym(terms:String){

  this.formService.getSynonyms(terms).subscribe((data) => {
    this.allTerms=data;
    console.log(this.allTerms);
})

}

addSynonym(a,b,c,d){

   var e = JSON.stringify(d);
   var res = e.split(":");
   var res2 = res[1].replace(/["']/g, "")
   var res3 = res2.split("}");
   var score=res3[0];
 console.log(a);
 console.log(b);
 console.log(c);
 console.log(res3[0]);

 this.formService.addSynonym(a,b,c,score)
 .subscribe(
   data => {
     this.msg="Added succesfully"},
   error => {
    this.msg="Failed to add synonym";}
 );

}

}
