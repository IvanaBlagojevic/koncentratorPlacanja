import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentService } from '../services/payment.service';
import { ActiveSubscription } from '../model/ActiveSubscription';

@Component({
  selector: 'app-cancel-subscription',
  templateUrl: './cancel-subscription.component.html',
  styleUrls: ['./cancel-subscription.component.css']
})
export class CancelSubscriptionComponent implements OnInit {

  private issn = "";
  private email = "";
  private subscription;
  constructor(private router : ActivatedRoute, private paymentService : PaymentService) { 

    this.issn = this.router.snapshot.params.id1;
    this.email = this.router.snapshot.params.id2;

    this.paymentService.getSubscription(this.issn).subscribe(res =>{
      this.subscription = res;
    }, err =>{
      alert("Some error");
    });

  }

  ngOnInit() {
  }

  unsubscribe(sub : ActiveSubscription){
    
    if(this.issn != undefined && this.email != undefined){

      this.paymentService.cancelSubscription(sub.merchantUsername,sub.agreementId,this.email).subscribe(res =>{
        window.location.href = "" + res;
      }, err => {
        alert("Error!");
      });
    }
   // alert("Unsub " + sub.agreementId);
  }
}
