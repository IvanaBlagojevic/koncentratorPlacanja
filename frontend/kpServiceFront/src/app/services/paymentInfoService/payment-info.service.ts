import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentInfoDTO } from 'src/app/model/PaymentInfoDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentInfoService {

  constructor(private http : HttpClient) { }

  zuul_url = "https://localhost:8086";

  createPaymentInfo(paymentInfo : PaymentInfoDTO) : Observable<any>{ 
    //ovde sam samo service dodala - proveriti sa Danicom
    return this.http.post(this.zuul_url  +"kpService/paymentinfo/create", paymentInfo);
  }

  getPaymentInfo(id : string) : Observable<any>{ 
    return this.http.get<PaymentInfoDTO>(this.zuul_url  +"/kpService/methodOfPayment/getPaymentInfo/".concat(id));
  }
}
