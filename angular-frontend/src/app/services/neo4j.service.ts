import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Concepts } from '../concept';
import { Intents } from '../intent';

@Injectable({
  providedIn: 'root'
})
export class Neo4jService  {


  url;
  _url;
  constructor(private http:HttpClient) { }

  createNode(name,parent){

    this._url="https://13.234.94.132:8092/ontology-management-service/createConcept/"+parent+"/"+name+"/";
    console.log(this._url);
    return this.http.get(this._url,{responseType: 'text' as 'text' });
  }
  createIntent(name,intent,weight){
    console.log(name,intent,weight);
    this._url="https://13.234.94.132:8092/ontology-management-service/insertTerm/"+intent+"/"+name+"/"+weight+"/";
    console.log(this._url,);

    return this.http.get(this._url,{responseType: 'text' as 'text' });
  }

  getNodes():Observable<Concepts[]>{

    this.url="https://13.234.94.132:8092/ontology-management-service/getnodes";
    return this.http.get<Concepts[]>(this.url);
  }


  getKnowledgeTerms():Observable<Intents[]>{
    this.url="https://13.234.94.132:8092/ontology-management-service/getIntentTerms/knowledge";
    return this.http.get<Intents[]>(this.url);
  }

  getComprehensionTerms():Observable<Intents[]>{
    this.url="https://13.234.94.132:8092/ontology-management-service/getIntentTerms/comprehension";
    return this.http.get<Intents[]>(this.url);
  } 
  getAnalysisTerms():Observable<Intents[]>{
    this.url="https://13.234.94.132:8092/ontology-management-service/getIntentTerms/analysis";
    return this.http.get<Intents[]>(this.url);
  }
   getApplicationTerms():Observable<Intents[]>{
    this.url="https://13.234.94.132:8092/ontology-management-service/getIntentTerms/application";
    return this.http.get<Intents[]>(this.url);
  }
   getSynthesisTerms():Observable<Intents[]>{
    this.url="https://13.234.94.132:8092/ontology-management-service/getIntentTerms/synthesis";
    return this.http.get<Intents[]>(this.url);
  } 
  getEvaluationTerms():Observable<Intents[]>{
    this.url="https://13.234.94.132:8092/ontology-management-service/getIntentTerms/evaluation";
    return this.http.get<Intents[]>(this.url);
  }
  getSynonyms(terms:String){

    this.url="https://13.234.94.132:8092/ontology-management-service/"+terms;
    return this.http.get<[]>(this.url);
  }

  addSynonym(intent:String,term:String,synonym:String,score:String){
    console.log(intent,term,synonym,score);
    this._url="https://13.234.94.132:8092/ontology-management-service/insertTerm/"+intent+"/"+synonym+"/"+score+"/";
    console.log(this._url);
    return this.http.get(this._url, {responseType: 'text' as 'text' });  }

}
