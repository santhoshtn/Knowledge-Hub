import { Injectable, EventEmitter } from '@angular/core';
import { Node, Link, ForceDirectedGraph } from './models';
import * as d3 from 'd3';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { json } from 'd3';
import { Test } from 'src/app/test';
import { Intent } from 'src/app/testIntent';
import { Testconcept } from 'src/app/conceptArray';
import { TestIntent } from 'src/app/intentArray';


const httpOptions = {
  headers: new HttpHeaders({ "Access-Control-Allow-Origin" : "*","Authorization":"Basic bmVvNGo6bmVvNGpAMTIz"
})
};
const url="http://13.234.94.132:7474/db/data/cypher";
@Injectable()
export class D3Service {
  // url: any;
  form1: any;
  
  /** This service will provide methods to enable user interaction with elements
    * while maintaining the d3 simulations physics
    */
  constructor(private http:HttpClient,private test:Test,private intent:Intent,private conceptArray:Testconcept,private intentArray:TestIntent) { 
  }

  /** A method to bind a pan and zoom behaviour to an svg element */
  applyZoomableBehaviour(svgElement, containerElement) {
    let svg, container, zoomed, zoom;

    svg = d3.select(svgElement);
    container = d3.select(containerElement);

    zoomed = () => {
      const transform = d3.event.transform;
      container.attr('transform', 'translate(' + transform.x + ',' + transform.y + ') scale(' + transform.k + ')');
    }

    zoom = d3.zoom().on('zoom', zoomed);
    svg.call(zoom);
  }

  /** A method to bind a draggable behaviour to an svg element */
  applyDraggableBehaviour(element, node: Node, graph: ForceDirectedGraph) {
    const d3element = d3.select(element);

    function started() {
      /** Preventing propagation of dragstart to parent elements */
      d3.event.sourceEvent.stopPropagation();

      if (!d3.event.active) {
        graph.simulation.alphaTarget(0.3).restart();
      }

      d3.event.on('drag', dragged).on('end', ended);

      function dragged() {
        node.fx = d3.event.x;
        node.fy = d3.event.y;
      }

      function ended() {
        if (!d3.event.active) {
          graph.simulation.alphaTarget(0);
        }

        node.fx = null;
        node.fy = null;
      }
    }

    d3element.call(d3.drag()
      .on('start', started));
  }

  /** The interactable graph we will simulate in this article
  * This method does not interact with the document, purely physical calculations with d3
  */
  getForceDirectedGraph(nodes: Node[], links: Link[], options: { width, height }) {
    const sg = new ForceDirectedGraph(nodes, links, options);
    return sg;
  }


  getNeo4j(){
    // this.url="http://13.234.94.132:7474/db/data/cypher";
    // this.form1={
    //   "query" : "MATCH (a:Level)-[r:levelOf]->(b:Level) WITH collect(source:a.name,target: b.name,value: type(r)}) AS links RETURN links",
    //   "params" : {}
    // }

    this.form1={};

    this.form1.query="MATCH (a:Concept)-[r:subconceptOf]->(b:Concept) WITH collect({ source:a.name,target: b.name, value: type(r)}) AS links RETURN links"

    this.form1.params={}




    this.http.post(url,this.form1,httpOptions)
         .subscribe(data=>{
           this.test=null;
           this.test=new Test();
         this.test.dat = (data['data'][0][0])
          for (let d = 0; d < this.test.dat.length; d++){
            var s= this.test.dat[d].source;
            var t = this.test.dat[d].target;
            this.test.arr.push(s);
            this.test.arr.push(t);
          }
          
         })
         return this.test;
         
  }


  getNeo4jIntent(){
    // this.url="http://13.234.94.132:7474/db/data/cypher";
    // this.form1={
    //   "query" : "MATCH (a:Level)-[r:levelOf]->(b:Level) WITH collect(source:a.name,target: b.name,value: type(r)}) AS links RETURN links",
    //   "params" : {}
    // }

    this.form1={};

    this.form1.query="MATCH (a:Terms)-[r:termsOf]->(b:Level) WITH collect({ source:a.name,target: b.name, value: type(r)}) AS links RETURN links"

    this.form1.params={}




    this.http.post(url,this.form1,httpOptions)
         .subscribe(data=>{
          this.intent=null;
          this.intent=new Intent();
         this.intent.dat = (data['data'][0][0])
          for (let d = 0; d < this.intent.dat.length; d++){
            var s= this.intent.dat[d].source;
            var t = this.intent.dat[d].target;
            this.intent.arr.push(s);
            this.intent.arr.push(t);
          }
          
         })
         return this.intent;
         
  }
  getConceptNodes(){
    // this.url="http://13.234.94.132:7474/db/data/cypher";
    // this.form1={
    //   "query" : "MATCH (a:Level)-[r:levelOf]->(b:Level) WITH collect(source:a.name,target: b.name,value: type(r)}) AS links RETURN links",
    //   "params" : {}
    // }

    this.form1={};

    this.form1.query="MATCH (n:Concept) RETURN n.name "

    this.form1.params={}




    this.http.post(url,this.form1,httpOptions)
         .subscribe(data=>{
          this.conceptArray.arr.length=0;
          for (let d = 0; d < data['data'].length; d++){
          
            this.conceptArray.arr.push(data['data'][d]);  
          }
          
         }) 
         return this.conceptArray.arr
  }
  getIntentNodes(){
    // this.url="http://13.234.94.132:7474/db/data/cypher";
    // this.form1={
    //   "query" : "MATCH (a:Level)-[r:levelOf]->(b:Level) WITH collect(source:a.name,target: b.name,value: type(r)}) AS links RETURN links",
    //   "params" : {}
    // }

    this.form1={};

    this.form1.query="MATCH (n:Terms) RETURN n.name "

    this.form1.params={}




    this.http.post(url,this.form1,httpOptions)
         .subscribe(data=>{
          // this.test.dat.length=0;
          // this.test.arr.length=0;
          this.intentArray.arr.length=0;
          for (let d = 0; d < data['data'].length; d++){
          
            this.intentArray.arr.push(data['data'][d]);  
          }
          
         }) 
         return this.intentArray.arr 
  }
  

}
