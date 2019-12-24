import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentDTO } from '../model/PaymentDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http : HttpClient) { }

  zuul_url = "https://localhost:8086/";

  //payPalService
  payPalCreatePayment(paymentInfo : PaymentDTO, paymentMethod : String) : Observable<any>{ 
    
    return this.http.post(this.zuul_url + "/" + paymentMethod + "/create", paymentInfo, { responseType: 'text'} );
  }

  payPalCompletePayment(paymentId : String, payerId : String, username : String, paymentMethod : String) : Observable<any>{

    console.log("pokusava da je pozoveeeee");
    return this.http.get(this.zuul_url + "/" + paymentMethod + "/complete/"+paymentId + "/"+payerId + "/" + username,  { responseType: 'text'} ); 
  }

}
