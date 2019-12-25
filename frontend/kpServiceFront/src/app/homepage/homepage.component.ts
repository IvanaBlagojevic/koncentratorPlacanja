import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BitcoinDTO } from '../model/BitcoinDTO';
import { BitcoinServiceService } from '../services/bitcoinService/bitcoin-service.service';
import { PaymentService } from '../services/payment.service';
import { PaymentDTO } from '../model/PaymentDTO';
import { MethodOfPaymentDTO } from '../model/MethodOfPaymentDTO';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  bitcoin : BitcoinDTO = new BitcoinDTO();
  payPalChoosen : Boolean;
  payment : PaymentDTO =  new PaymentDTO();
  amount : number;
  email : string;

  paymentMethods: Array<MethodOfPaymentDTO>;
  
  constructor(private service : PaymentService, private zone : NgZone, private router: ActivatedRoute, private bs: BitcoinServiceService) {

    this.email = this.router.snapshot.params.id1;
    this.amount = this.router.snapshot.params.id2;
    this.payment.merchantEmail = this.email;
    this.payment.amount = this.amount;

   }

  ngOnInit() {
    console.log("email " + this.email);
    console.log("amount " + this.amount);

      /*this.bitcoin.amount=1;
      this.bitcoin.currency="USD";
      this.bitcoin.merchantEmail="ivana";
      this.bitcoin.merchantOrderId="2";
     
      console.log(this.bitcoin.amount);
      console.log(this.bitcoin.currency);
      console.log(this.bitcoin.merchantEmail);
      console.log(this.bitcoin.merchantOrderId);
      this.bs.bitcoinCreatePayment(this.bitcoin).subscribe(data=>{
        console.log("bitcoin payment");
        console.log("data"+data);
        window.location.href = data;
      });*/

      this.service.getPaymentMethods().subscribe(data => {
          this.paymentMethods = data;

          this.paymentMethods.forEach(element => {
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
      });
  }
  
  pay(path : String)
  {
    let redirectUrl;
    this.service.payPalCreatePayment(this.payment, path).subscribe(data => {

      redirectUrl = data;

      this.zone.runOutsideAngular(() => {
          window.location.href = "" + redirectUrl;
      });

    });
  }
}

