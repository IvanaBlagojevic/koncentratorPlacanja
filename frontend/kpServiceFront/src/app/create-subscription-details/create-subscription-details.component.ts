import { Component, OnInit } from '@angular/core';
import { SubscriptionPlanDTO } from '../model/SubscriptionPlanDTO';
import { PaymentService } from '../services/payment.service';

@Component({
  selector: 'app-create-subscription-details',
  templateUrl: './create-subscription-details.component.html',
  styleUrls: ['./create-subscription-details.component.css']
})
export class CreateSubscriptionDetailsComponent implements OnInit {

  private subscriptionDTO = new SubscriptionPlanDTO();

  constructor(private paymentService : PaymentService) { }

  ngOnInit() {
  }

  onSubmit(){
    // alert("Klik");
    // console.log("Issn: " + this.subscriptionDTO.merchantUsername);
    // console.log("Period: " + this.subscriptionDTO.subPeriod);
    // console.log("Frequency: " + this.subscriptionDTO.frequency);
    // console.log("Price: " + this.subscriptionDTO.subPrice);
    this.paymentService.createSubscription(this.subscriptionDTO).subscribe(res =>{
      alert("Subscription created!");
    }, err => {
      alert("Error while creating subscription");
    });
  }
}
