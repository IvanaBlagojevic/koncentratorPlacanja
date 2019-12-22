import { Component, OnInit } from '@angular/core';
import { BuyerInfoService } from '../service/buyerInfo/buyer-info.service';
import { ActivatedRoute } from '@angular/router';
import { BuyerInfo } from '../model/BuyerInfo';

@Component({
  selector: 'app-buyer-info',
  templateUrl: './buyer-info.component.html',
  styleUrls: ['./buyer-info.component.css']
})
export class BuyerInfoComponent implements OnInit {

  buyer : BuyerInfo = new BuyerInfo();
  errorMessage = '';
  id: String;

  constructor(private router: ActivatedRoute,private buyerService: BuyerInfoService) { 
    this.id = this.router.snapshot.params.id
    this.buyer.paymentId=this.id;

  }

  ngOnInit() {
  }

  sign(){
    this.buyerService.createBuyer(this.buyer).subscribe(
      data =>{
        alert("Merchant "+this.buyer.pan+ " successfully registered!");
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
    
      }
    );
  }

}
