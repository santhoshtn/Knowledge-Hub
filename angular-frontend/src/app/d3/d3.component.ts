import { Component, OnInit } from '@angular/core';
import APP_CONFIG from 'src/app/app.config';
import { Node, Link, D3Service } from './d3';
import { Test } from '../test';




@Component({
  selector: 'app-d3',
  templateUrl: './d3.component.html',
  styleUrls: ['./d3.component.css']
})
export class D3Component implements OnInit {
  ngOnInit() {

    console.log("d3")
  }
//   nodes: Node[] = [];
//   links: Link[] = [];
//   nan:any;
//   ban:any;
 
  constructor(private test:Test,private d3:D3Service) {}
//     const N = APP_CONFIG.jas.length;
//     const M = APP_CONFIG.nas.length
         

//     /** constructing the nodes array */
//     for (let d = 0; d < N; d++){
//       var s= APP_CONFIG.jas[d].source;
//       var t = APP_CONFIG.jas[d].target;
//       // this.test.dat.push(s);
//       // this.test.dat.push(t);
//     }
//     for (let d = 0; d < M; d++){
     
//       var u= APP_CONFIG.nas[d].source;
//       var v = APP_CONFIG.nas[d].target;
  
//       // this.test.dat.push(u);
//       // this.test.dat.push(v);
//     }
//     // //   
//     // }
   
//     // console.log(this.test.dat)
//   //  for(var i=0;i<N-1;i++){
//   //     for(var j=i+1;j<N;j++){
//   //  if (this.nodes[i].id ==this.nodes[j].id ) {
//   //   console.log("sour "+this.nodes[i].id)
//   //   console.log("tar "+this.nodes[j].id)
//   //     delete this.nodes[j];
//   // }
//   // }}

// function removeDuplicateUsingSet(arr){
//   let unique_array = Array.from(new Set(arr))
//   return unique_array
// }
//  this.nan=removeDuplicateUsingSet(this.test.dat)

// for (let d = 0; d < this.nan.length; d++){
//     this.nodes.push(new Node(this.nan[d]))
// }

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
get(){
  this.d3.getNeo4j();
}}
// }


