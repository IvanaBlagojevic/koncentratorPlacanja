import { Injectable } from '@angular/core';
import { Merchant } from 'src/app/model/Merchant';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MerchantService {

  constructor(private http:HttpClient) { }

  createMerchant(merchant: Merchant){
    return this.http.post('https://localhost:8089/merchant/add/',merchant);
  }

}
