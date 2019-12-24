import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BitcoinServiceService } from '../services/bitcoinService/bitcoin-service.service';

@Component({
  selector: 'app-bitcoin-cancel',
  templateUrl: './bitcoin-cancel.component.html',
  styleUrls: ['./bitcoin-cancel.component.css']
})
export class BitcoinCancelComponent implements OnInit {

  constructor(private router: ActivatedRoute, private bs: BitcoinServiceService) { }

  ngOnInit() {
    const id = this.router.snapshot.paramMap.get('oid');
    if (id != undefined) {
      this.bs.bitcoinCancelPayment(id).subscribe(data=>{
        console.log("bitcoin paymend canceld");
      });
    }
  }

}
