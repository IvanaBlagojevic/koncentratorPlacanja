import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BitcoinServiceService } from '../services/bitcoinService/bitcoin-service.service';

@Component({
  selector: 'app-bitcoin-success',
  templateUrl: './bitcoin-success.component.html',
  styleUrls: ['./bitcoin-success.component.css']
})
export class BitcoinSuccessComponent implements OnInit {

  constructor(private router: ActivatedRoute, private bs: BitcoinServiceService) { }

  ngOnInit() {
    const id = this.router.snapshot.paramMap.get('oid');
    if (id != undefined) {
      this.bs.bitcoinCompleatePayment(id).subscribe(data=>{
        console.log("bitcoin paymend success");
      });
    }
    
  }

}
