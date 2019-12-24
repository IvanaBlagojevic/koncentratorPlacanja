import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentData } from 'src/app/model/PaymentData';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http:HttpClient) { }

  url= 'https://localhost:8088/payment';

  createPayment(paymentData: PaymentData){
    return this.http.post(this.url+'/sendPaymentData',paymentData);
  }

}
