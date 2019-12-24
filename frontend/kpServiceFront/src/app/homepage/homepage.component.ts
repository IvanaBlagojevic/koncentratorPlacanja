import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BitcoinDTO } from '../model/BitcoinDTO';
import { BitcoinServiceService } from '../services/bitcoinService/bitcoin-service.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  bitcoin : BitcoinDTO = new BitcoinDTO();

  constructor(private router: ActivatedRoute, private bs: BitcoinServiceService) { }

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

}
