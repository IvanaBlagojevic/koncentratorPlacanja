import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentService } from '../services/payment.service';
import { MerchantDTO } from '../model/MerchantDTO';
import { SubscriptionDTO } from '../model/SubscriptionDTO';

@Component({
  selector: 'app-journal-subscription',
  templateUrl: './journal-subscription.component.html',
  styleUrls: ['./journal-subscription.component.css']
})
export class JournalSubscriptionComponent implements OnInit {

  private issn = "";
  private merchant : MerchantDTO = new MerchantDTO();
  private email = "";

  constructor(private router : ActivatedRoute, private paymentService : PaymentService, private zone : NgZone) { 

    this.issn = this.router.snapshot.params.id1;
    this.email = this.router.snapshot.params.id2;

    this.paymentService.getMerchantByUsername(this.issn).subscribe(res => {

      this.merchant = res;
      console.log("Sub num " + this.merchant.subscriptions.length);
    }, err => {
     // alert("Error");
    });

  }

  ngOnInit() {
  }

  subscribe(s : SubscriptionDTO){
    console.log("TUUUUUU ");
    this.paymentService.subscribeToJournal(s.merchantUsername,s.planId,this.email).subscribe(res=>{
      console.log("Res: " + res);
      this.zone.runOutsideAngular(() => {
        window.location.href = "" + res;
      });
    }, err =>{
      alert("Error while subscribing to journal");
    });
  }
}
