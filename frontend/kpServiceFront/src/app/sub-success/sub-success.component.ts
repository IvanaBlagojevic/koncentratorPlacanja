import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentService } from '../services/payment.service';
import { MerchantDTO } from '../model/MerchantDTO';

@Component({
  selector: 'app-sub-success',
  templateUrl: './sub-success.component.html',
  styleUrls: ['./sub-success.component.css']
})
export class SubSuccessComponent implements OnInit {

  private username = "";
  private token = "";
  private callbackUrl = "";
  private merchant = new MerchantDTO();
  constructor(private route : ActivatedRoute, private paymentService : PaymentService) {

    this.username = this.route.snapshot.queryParamMap.get('username');
    this.callbackUrl = this.route.snapshot.queryParamMap.get('callbackUrl');
    this.token = this.route.snapshot.queryParamMap.get('token');

    console.log("Username: " + this.username);
    console.log("CallbackUrl: " + this.callbackUrl);
    console.log("Token: " + this.token);

    // this.paymentService.getMerchantByUsername(this.username).subscribe(res =>{

    //   this.merchant = res;
    //   console.log("M " + this.merchant);
    //   console.log("f " + this.merchant.SCsystem.systemName);
    //   console.log("MS: " + this.merchant.SCsystem.frontUrl);
      
    // });
  }

  ngOnInit() {

    
    
    if(this.username != undefined && this.callbackUrl != undefined && this.token != undefined)
    {
      this.paymentService.redirectAfterSubAgreement(this.username, this.callbackUrl,this.token).subscribe(res =>{
        window.location.href="https://localhost:4202";
      }, err => {
        alert("Error happend");
      });

    }
  }

}
