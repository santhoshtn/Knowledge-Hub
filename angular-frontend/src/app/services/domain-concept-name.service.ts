import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DomainConceptNameService {
  private conceptsArray :string[];
  private url :string = "https://13.234.94.132:8092/web-search-service/domain";
  constructor(private http:HttpClient){

  } 
  postDomainConceptName(domain:string,concepts:string):string{
    if(domain!=null && concepts!=null && concepts.length!=0 && domain.length!=0 ){
      console.log("postDomainConceptName() am called");
      this.conceptsArray=concepts.split(",");
      console.log("before posting ")
      console.log(domain);
      console.log(concepts)
      this.http.post(this.url,{
        "domain":domain,
        "conceptName":this.conceptsArray
      }).subscribe((data)=>{

        console.log(data);
      })
      console.log("after posting ")
      return "Submitted Successfully!!";
    }
    else{
      return "Submission failed maybe due to null or empty values";
    }
    
  }
}
