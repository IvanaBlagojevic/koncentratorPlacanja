import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentService } from '../services/payment.service';

@Component({
  selector: 'app-pay-pal-cancel',
  templateUrl: './pay-pal-cancel.component.html',
  styleUrls: ['./pay-pal-cancel.component.css']
})
export class PayPalCancelComponent implements OnInit {

  constructor(private route : ActivatedRoute, private zone : NgZone, private service : PaymentService) 
  { 

  }

  ngOnInit() {

    let redirect;
    this.route.queryParams.subscribe(params => {
      if (params['oid'] !== undefined) {
        this.service.payPalCancelPayment(params['oid']).subscribe(data => {
            redirect = data;
            console.log("ddddddddd " + data);
              window.location.href = "" + redirect;
        });
      }else
      {
        console.log("Nemaaaa");
      }
    });
  }

}
