import { Component, OnInit } from '@angular/core';
import { Merchant } from '../model/Merchant';
import { ActivatedRoute } from '@angular/router';
import { MerchantService } from '../service/merchantService/merchant.service';
import { HttpErrorResponse } from '@angular/common/http';

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
    if(this.isBlank(this.merchant.merchantId))
    {
      alert("You have to fill merchant id!");
    }else if(this.isBlank(this.merchant.merchantPassword))
    {
      alert("You have to fill merchant password!");
    }else
    {
      this.merchantService.createMerchant(this.merchant).subscribe(
        data =>{
         alert("Merchant "+this.merchant.merchantId+ " successfully registered!");
        },error =>{this.handleAuthError(error)});
    }
    
  }

  handleAuthError(err : HttpErrorResponse) {

    if(err.status === 400){
      alert("This data is not valid!");
    }else
    {
      alert("Problem");
    }
  }

  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }
}
