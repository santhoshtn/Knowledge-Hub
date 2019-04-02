import { Component, OnInit } from '@angular/core';
import { Test } from '../test';
import { D3Service, Link, Node } from '../d3/d3';
import { D3Nodes } from '../domain/d3Nodes';

@Component({
  selector: 'app-display-intent',
  templateUrl: './display-intent.component.html',
  styleUrls: ['./display-intent.component.css']
})
export class DisplayIntentComponent implements OnInit {

  ngOnInit() {

  }

  nodes: Node[] = [];
  links: Link[] = [];
  nan: any;
  ban: any;
  can:any;
  constructor(private test: Test, private d3: D3Service, private node: D3Nodes) {

    var arr = this.d3.getNeo4jIntent()
    function removeDuplicateUsingSet(arr) {
      let unique_array = Array.from(new Set(arr))
      return unique_array
    }
this.can=arr.arr

    var counts = {};

for (var i = 0; i < arr.arr.length; i++) {
  var num = arr.arr[i];
  counts[num] = counts[num] ? counts[num] + 1 : 1;
}

    this.nan = removeDuplicateUsingSet(arr.arr)
    console.log(this.nan)


    for (let d = 0; d < this.nan.length; d++) {

      this.nodes.push(new Node(this.nan[d]))
      console.log(counts[this.can[d]])
       this.nodes[d].linkCount=counts[this.can[d]];
      
    }
    this.ban=arr.dat
    console.log(this.ban)
    for (var k = 0; k < this.ban.length; k++) {

      var u = this.ban[k].source;
      var v = this.ban[k].target;
      //      /** increasing connections toll on connecting nodes */
      //  this.nodes[].linkCount++;
      // this.nodes[this.nodes.indexOf(this.nan.indexOf(v))].linkCount++;

      //     /** connecting t he nodes before starting the simulation */

      this.links.push(new Link(u, v));
      // //   }
      // }
    }
  }
}


