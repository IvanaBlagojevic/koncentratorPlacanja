import { Injectable } from '@angular/core';
import { Merchant } from 'src/app/model/Merchant';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MerchantService {

  constructor(private http:HttpClient) { }
  zuulUrl :"https://localhost:8086/bankService";
  createMerchant(merchant: Merchant){
    return this.http.post(this.zuulUrl+'/merchant/add/',merchant);
  }

}
