import { Component, OnInit } from '@angular/core';
import { BuyerInfoService } from '../service/buyerInfo/buyer-info.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-buyer-info',
  templateUrl: './buyer-info.component.html',
  styleUrls: ['./buyer-info.component.css']
})
export class BuyerInfoComponent implements OnInit {

  constructor(private router: ActivatedRoute,private buyerInfoService: BuyerInfoService) {
    this.buyerInfoService.getTest().subscribe(data =>{
      alert("Connection is "+data.info);
    })
   }

  ngOnInit() {
    
  }

}
