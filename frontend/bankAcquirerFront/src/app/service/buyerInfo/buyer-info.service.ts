import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BuyerInfo } from 'src/app/model/BuyerInfo';
import { ResponseToKP } from 'src/app/model/ResponseToKP';

@Injectable({
  providedIn: 'root'
})
export class BuyerInfoService {
  constructor(private http:HttpClient) { }

  createBuyer(buyer: BuyerInfo){
    return this.http.post<ResponseToKP>('https://localhost:8090/payment/addBuyerInfo/',buyer);
  }

}
