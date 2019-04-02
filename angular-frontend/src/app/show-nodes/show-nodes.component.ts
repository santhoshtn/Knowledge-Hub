import { Component, OnInit } from '@angular/core';
import {WOW} from 'wowjs/dist/wow.min'
import { D3Service } from '../d3/d3';
import { Router } from '@angular/router';


@Component({
  selector: 'app-show-nodes',
  templateUrl: './show-nodes.component.html',
  styleUrls: ['./show-nodes.component.css','../../assets/conceptsLib/font-awesome/css/font-awesome.min.css',
  '../../assets/conceptsLib/animate/animate.min.css','../../assets/conceptsLib/ionicons/css/ionicons.min.css',
  '../../assets/conceptsLib/owlcarousel/assets/owl.carousel.min.css','../../assets/conceptsLib/magnific-popup/magnific-popup.css',
'../../assets/conceptsLib/ionicons/css/ionicons.min.css','../../assets/conceptsLib/bootstrap/css/bootstrap.min.css']
})
export class ShowNodesComponent implements OnInit {

  constructor(private d3:D3Service,private router:Router) { }

  ngOnInit() {
    new WOW().init();

  }
  getConcept(){
    this.d3.getNeo4j();
    // this.router.navigate(['/navigConcept'])
    this.router.navigate(['/display'])
  }
  getIntent(){
    this.d3.getNeo4jIntent();
    // this.router.navigate(['/navigIntent'])
    this.router.navigate(['/displayIntent'])
  }
  getConceptNodes(){
    this.router.navigate(['/displayConceptNodes'])
  }
  getIntentNodes(){
    this.router.navigate(['/displayIntentNodes'])
  }

}
