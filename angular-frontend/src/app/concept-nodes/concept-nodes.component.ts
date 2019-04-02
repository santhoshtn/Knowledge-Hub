import { Component, OnInit } from '@angular/core';
import { Test } from '../test';
import { D3Service, Link, Node } from '../d3/d3';
import { D3Nodes } from '../domain/d3Nodes';
@Component({
  selector: 'app-concept-nodes',
  templateUrl: './concept-nodes.component.html',
  styleUrls: ['./concept-nodes.component.css']
})
export class ConceptNodesComponent implements OnInit {
  nodes: Node[] = [];
  links: Link[] = [];
  constructor(private test: Test, private d3: D3Service, private node: D3Nodes) {

  }
  ngOnInit() {
    var arr = this.d3.getConceptNodes()
    
    // var arr = this.test.arr;
    console.log(arr)

    for (let d = 0; d < arr.length; d++) {

    this.nodes.push(new Node(arr[d]))
   }
    
      //      /** increasing connections toll on connecting nodes */
      //  this.nodes[].linkCount++;
      // this.nodes[this.nodes.indexOf(this.nan.indexOf(v))].linkCount++;

      //     /** connecting t he nodes before starting the simulation */

      // //   }
      // }
    }
  
  


 
}
