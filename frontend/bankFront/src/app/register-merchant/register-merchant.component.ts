import { Component, OnInit } from '@angular/core';
import { Merchant } from '../model/Merchant';
import { ActivatedRoute } from '@angular/router';
import { MerchantService } from '../service/merchantService/merchant.service';

@Component({
  selector: 'app-register-merchant',
  templateUrl: './register-merchant.component.html',
  styleUrls: ['./register-merchant.component.css']
})
export class RegisterMerchantComponent implements OnInit {

  merchant : Merchant = new Merchant();
  errorMessage = '';

  constructor(private router: ActivatedRoute,private merchantService: MerchantService) { }

  ngOnInit() {
  }

  sign(){
    this.merchantService.createMerchant(this.merchant).subscribe(
      data =>{
        alert("Merchant "+this.merchant.merchantId+ " successfully registered!");
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
    
      }
    );
  }
}
