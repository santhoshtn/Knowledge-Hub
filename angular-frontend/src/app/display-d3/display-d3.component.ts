import { Component, OnInit } from '@angular/core';
import { Test } from '../test';
import { D3Service, Link, Node } from '../d3/d3';
import { D3Nodes } from '../domain/d3Nodes';


@Component({
  selector: 'app-display-d3',
  templateUrl: './display-d3.component.html',
  styleUrls: ['./display-d3.component.css']
})
export class DisplayD3Component implements OnInit {


  ngOnInit() {

  }

  nodes: Node[] = [];
  links: Link[] = [];
  nan: any;
  ban: any;
  can:any;
  constructor(private test: Test, private d3: D3Service, private node: D3Nodes) {

    var arr = this.d3.getNeo4j()
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
  //  for(var i=0;i<N-1;i++){
  //     for(var j=i+1;j<N;j++){
  //  if (this.nodes[i].id ==this.nodes[j].id ) {
  //   console.log("sour "+this.nodes[i].id)
  //   console.log("tar "+this.nodes[j].id)
  //     delete this.nodes[j];
  // }
  // }}




// console.log(this.nodes)
//     for (var k = 0; k < N; k++) {
//       var s= APP_CONFIG.jas[k].source;
//        var t = APP_CONFIG.jas[k].target;
//     //      /** increasing connections toll on connecting nodes */
//         //  this.nodes[this.nodes.indexOf(this.nan.indexOf(s))].linkCount++;
//         //  this.nodes[this.nodes.indexOf(this.nan.indexOf(t))].linkCount++;
//     //     // this.nodes[0].linkCount++;
//     //     /** connecting the nodes before starting the simulation */
//        this.links.push(new Link(s,t));

//     // //   }
//     // }
//   }
//   for (var k = 0; k < M; k++) {

//      var u= APP_CONFIG.nas[k].source;
//      var v = APP_CONFIG.nas[k].target;
//   //      /** increasing connections toll on connecting nodes */
//   // this.nodes[this.nodes.indexOf(this.nan.indexOf(u))].linkCount++;
//   // this.nodes[this.nodes.indexOf(this.nan.indexOf(v))].linkCount++;
//   //     // this.nodes[0].linkCount++;
//   //     /** connecting the nodes before starting the simulation */

//      this.links.push(new Link(u,v));
//   // //   }
//   // }
// }

// }
// get(){
//   this.d3.getNeo4j();
// }
// }

