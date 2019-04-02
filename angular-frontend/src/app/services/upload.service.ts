import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const httpOptions = {
  headers: new HttpHeaders({ "Access-Control-Allow-Origin" : "*",'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
 })
 };
@Injectable({
  providedIn: 'root'
})
export class UploadService {

  _url;
  constructor(private http: HttpClient) { }

  pushFileToStorage(file: File) {
    const formdata: FormData = new FormData();
 
    formdata.append('file', file);

    this._url="https://13.234.94.132:8092/document-provider/files/"
    
 
    return this.http.post(this._url,formdata,{responseType: 'blob' as 'json'} )
  
  }
}

