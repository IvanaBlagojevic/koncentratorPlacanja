import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BitcoinDTO } from '../model/BitcoinDTO';
import { PaymentService } from '../services/payment.service';
import { PaymentDTO } from '../model/PaymentDTO';
import { MethodOfPaymentDTO } from '../model/MethodOfPaymentDTO';
import { BitcoinServiceService } from '../services/bitcoinService/bitcoin-service.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  
  constructor( ) {
   }

  ngOnInit() {
    
  }
  
}

