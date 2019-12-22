import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BuyerInfo } from 'src/app/model/BuyerInfo';

@Injectable({
  providedIn: 'root'
})
export class BuyerInfoService {
  constructor(private http:HttpClient) { }

  createBuyer(buyer: BuyerInfo){
    return this.http.post('http://localhost:8090/payment/addBuyerInfo/',buyer);
  }

}
