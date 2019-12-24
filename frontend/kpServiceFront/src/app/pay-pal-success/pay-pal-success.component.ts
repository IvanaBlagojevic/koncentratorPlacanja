import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentService } from '../services/payment.service';

@Component({
  selector: 'app-pay-pal-success',
  templateUrl: './pay-pal-success.component.html',
  styleUrls: ['./pay-pal-success.component.css']
})
export class PayPalSuccessComponent implements OnInit {

  constructor(private route : ActivatedRoute, private service : PaymentService, private zone : NgZone) { }

  ngOnInit() {

    let redirectUrl;
    this.route.queryParams.subscribe(params => {
      if (params['paymentId'] !== undefined && params['PayerID'] !== undefined && params['username'] !== undefined) {
        this.service.payPalCompletePayment(params['paymentId'], params['PayerID'], params['username'],"payPalService").subscribe(
          (data: any) => {
            redirectUrl = data;

            this.zone.runOutsideAngular(() => {
              window.location.href = "" + redirectUrl;
            });
              });
      }else
      {
        console.log("Nemaaaa");
      }
    })
  }

}
