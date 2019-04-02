import { Component, Input, OnInit } from '@angular/core';
import { Node, D3Service } from '../../../d3';

@Component({
  selector: '[nodeVisual]',
  template: `
    <svg:g [attr.transform]="'translate(' + node.x + ',' + node.y + ')'">
      <svg:circle
          class="node"
          [attr.fill]="node.color"
          cx="0"
          cy="0"
          [attr.r]="node.r">
      </svg:circle>
      <svg:text
          class="node-name"
          [attr.font-size]="node.fontSize">
         
        {{node.id}}
      </svg:text>
    </svg:g>
  `,
  styleUrls: ['./node-visual.component.css']


})
// export class NodeVisualComponent  implements OnInit{
//   ngOnInit(){
    
//   }
//   @Input('nodeVisual') node: Node;
// }

export class NodeVisualComponent  implements OnInit{
  @Input('nodeVisual') node: Node;
  nod:Node
  arr: any;
  constructor(private d3:D3Service,){}
  ngOnInit(){
   
   
      // this.nod.push(new Node("6"))
}
}