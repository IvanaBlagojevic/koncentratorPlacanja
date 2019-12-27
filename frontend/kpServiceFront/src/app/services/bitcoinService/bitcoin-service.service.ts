import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BitcoinDTO } from 'src/app/model/BitcoinDTO';
import { Observable } from 'rxjs';
import { PaymentDTO } from 'src/app/model/PaymentDTO';

@Injectable({
  providedIn: 'root'
})
export class BitcoinServiceService {

  constructor(private http : HttpClient) { }

  zuulUrl = "https://localhost:8086/bitcoinService";
  url = "https://localhost:8090";

  bitcoinCreatePayment(paymentInfo : PaymentDTO): Observable<any>{
    console.log("usao u create bitcoin payment");
    return this.http.post(this.url + "/create", paymentInfo, {responseType: 'text'});
  }

  bitcoinCompleatePayment(paymentInfo : String){
    return this.http.get(this.zuulUrl + "/completePayment/"+ paymentInfo);
  }

  bitcoinCancelPayment(paymentInfo : String){
    return this.http.get(this.zuulUrl + "/cancelPayment/" + paymentInfo);
  }
}
