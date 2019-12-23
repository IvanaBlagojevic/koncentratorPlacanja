import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentDTO } from '../model/PaymentDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http : HttpClient) { }

  payPal_url = "https://localhost:8087/paypal";

  payPalCreatePayment(paymentInfo : PaymentDTO){

    return this.http.post(this.payPal_url + "/complete", paymentInfo);
  }

  payPalCompletePayment(paymentId : String, payerId : String, username : String) : Observable<any>{

    console.log("pokusava da je pozoveeeee");
    return this.http.get(this.payPal_url + "/complete/"+paymentId + "/"+payerId + "/" + username); 
  }

}
