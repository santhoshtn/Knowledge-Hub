import { Component, OnInit } from '@angular/core';
import { SocketService } from '../services/socket.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css','../../assets/aboutUslib/font-awesome/css/font-awesome.min.css',
  '../../assets/aboutUslib/animate/animate.min.css','../../assets/aboutUslib/ionicons/css/ionicons.min.css',
  '../../assets/aboutUslib/owlcarousel/assets/owl.carousel.min.css','../../assets/aboutUslib/magnific-popup/magnific-popup.css',
'../../assets/aboutUslib/ionicons/css/ionicons.min.css','../../assets/aboutUslib/bootstrap/css/bootstrap.min.css']
})
export class AboutUsComponent implements OnInit {

  constructor(private socket:SocketService) { }

  ngOnInit() {
  }

}
