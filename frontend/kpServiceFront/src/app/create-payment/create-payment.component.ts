import { Component, OnInit, NgZone } from '@angular/core';
import { PaymentDTO } from '../model/PaymentDTO';
import { ActivatedRoute } from '@angular/router';
import { BitcoinServiceService } from '../services/bitcoinService/bitcoin-service.service';
import { PaymentService } from '../services/payment.service';
import { PaymentInfoService } from '../services/paymentInfoService/payment-info.service';
import { PaymentInfoDTO } from '../model/PaymentInfoDTO';

@Component({
  selector: 'app-create-payment',
  templateUrl: './create-payment.component.html',
  styleUrls: ['./create-payment.component.css']
})
export class CreatePaymentComponent implements OnInit {

  private issn = "";
  private amount : number;
  private supportedMethods = [];
  private merchant;
  payment : PaymentDTO =  new PaymentDTO();
  paymentInfo : PaymentInfoDTO =  new PaymentInfoDTO();
  private orderId="";
  constructor(private router : ActivatedRoute, private paymentService : PaymentService,
              private zone : NgZone, private bs : BitcoinServiceService, private paymentInfoService: PaymentInfoService) { 
    

    

    

  }

  ngOnInit() {
    this.orderId= this.router.snapshot.params.id;
    console.log("++++"+this.orderId)
    this.paymentInfoService.getPaymentInfo(this.orderId).subscribe(
      data=>{
        console.log("console "+data.merchantIssn);
        this.paymentInfo=data;
        this.issn = data.merchantIssn;
        this.amount = data.amount;
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
    )
    
  }

  pay(path : String)
  {
    let redirectUrl;
    console.log(this.paymentInfo.errorURL);
    console.log(this.paymentInfo.successURL);
    console.log(this.paymentInfo.failedURL);
    this.paymentService.payPalCreatePayment(this.paymentInfo, path, this.orderId).subscribe(data => {

      redirectUrl = data;

      this.zone.runOutsideAngular(() => {
          window.location.href = "" + redirectUrl;
      });

    });
  }

}
