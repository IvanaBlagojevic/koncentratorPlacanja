import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentService } from '../services/payment.service';
import { PaymentDTO } from '../model/PaymentDTO';
import { BitcoinServiceService } from '../services/bitcoinService/bitcoin-service.service';

@Component({
  selector: 'app-buy-journal',
  templateUrl: './buy-journal.component.html',
  styleUrls: ['./buy-journal.component.css']
})
export class BuyJournalComponent implements OnInit {

  private issn = "";
  private amount : number;
  private supportedMethods = [];
  private merchant;
  payment : PaymentDTO =  new PaymentDTO();

  constructor(private router : ActivatedRoute, private paymentService : PaymentService,
              private zone : NgZone, private bs : BitcoinServiceService) { 

    this.issn = this.router.snapshot.params.id1;
    this.amount = this.router.snapshot.params.id2;
    this.payment.merchantIssn = this.issn;
    this.payment.amount = this.amount;

    this.paymentService.getMerchantByUsername(this.issn).subscribe(res => {

      this.merchant = res;

      this.merchant.supportedMethods.forEach(element => {
        if (element.name == "Card")
        {
          element.img = "../assets/image/visa.png"
        }else if(element.name == "PayPal")
        {
          element.img = "../assets/image/paypal.png"
        }else //bitcoin
        {
          element.img = "../assets/image/atm.png"
        }
      });

    }, err => {
      alert("Merchant with given username doesn't exist");
    });

    

  }

  ngOnInit() {
  }

  pay(path : String)
  {
    let redirectUrl;
    this.paymentService.payPalCreatePayment(this.payment, path).subscribe(data => {

      redirectUrl = data;

      this.zone.runOutsideAngular(() => {
          window.location.href = "" + redirectUrl;
      });

    });
  }

}
