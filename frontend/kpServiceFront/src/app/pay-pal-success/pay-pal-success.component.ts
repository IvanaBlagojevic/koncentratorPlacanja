import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentService } from '../services/payment.service';

@Component({
  selector: 'app-pay-pal-success',
  templateUrl: './pay-pal-success.component.html',
  styleUrls: ['./pay-pal-success.component.css']
})
export class PayPalSuccessComponent implements OnInit {

  constructor(private route : ActivatedRoute, private service : PaymentService) { }

  ngOnInit() {

    this.route.queryParams.subscribe(params => {
      if (params['paymentId'] !== undefined && params['PayerID'] !== undefined && params['username'] !== undefined) {
        this.service.payPalCompletePayment(params['paymentId'], params['PayerID'], params['username']).subscribe(
          (data: any) => {
            console.log("Podaci: " + data);
          });
      }else
      {
        console.log("Nemaaaa");
      }
    })
  }

}
