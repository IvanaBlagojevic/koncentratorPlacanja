<<<<<<< Updated upstream
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BitcoinDTO } from '../model/BitcoinDTO';
import { BitcoinServiceService } from '../services/bitcoinService/bitcoin-service.service';
=======
import { Component, OnInit, NgZone } from '@angular/core';
import { PaymentService } from '../services/payment.service';
import { PaymentDTO } from '../model/PaymentDTO';
>>>>>>> Stashed changes

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

<<<<<<< Updated upstream
  bitcoin : BitcoinDTO = new BitcoinDTO();

  constructor(private router: ActivatedRoute, private bs: BitcoinServiceService) { }
=======
  payPalChoosen : Boolean;
  payment : PaymentDTO =  new PaymentDTO();


  constructor(private service : PaymentService, private zone : NgZone) { }
>>>>>>> Stashed changes

  ngOnInit() {
      this.bitcoin.amount=1;
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
      })
  }

  createPayment() {

    if(this.payPalChoosen == true)
    {
      let redirectUrl;
      this.service.payPalCreatePayment(this.payment, "payPalService").subscribe(data => {
        
        console.log("Podaciii: " + data);
        redirectUrl = data;

        this.zone.runOutsideAngular(() => {
          window.location.href = "" + redirectUrl;
        });
        
      });
    }
  }

}

