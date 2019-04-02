import { Component, OnInit } from '@angular/core';
import { Test } from '../test';
import { D3Service, Link, Node } from '../d3/d3';
import { D3Nodes } from '../domain/d3Nodes';

@Component({
  selector: 'app-intent-nodes',
  templateUrl: './intent-nodes.component.html',
  styleUrls: ['./intent-nodes.component.css']
})
export class IntentNodesComponent implements OnInit {

  nodes: Node[] = [];
  links: Link[] = [];
  constructor(private test: Test, private d3: D3Service, private node: D3Nodes) {

  }
  ngOnInit() {
    var arr = this.d3.getIntentNodes()
    
    // var arr = this.test.arr;
    console.log(arr)

    for (let d = 0; d < arr.length; d++) {

    this.nodes.push(new Node(arr[d]))
   }

}
}
