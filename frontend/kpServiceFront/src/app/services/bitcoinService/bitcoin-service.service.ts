import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BitcoinDTO } from 'src/app/model/BitcoinDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BitcoinServiceService {

  constructor(private http : HttpClient) { }

  zuulUrl = "https://localhost:8086/bitcoinService/bitcoin";
  url = "https://localhost:8088/bitcoin";

  bitcoinCreatePayment(paymentInfo : BitcoinDTO): Observable<any>{
    console.log("usao u create bitcoin payment");
    console.log(paymentInfo.amount);
      console.log(paymentInfo.currency);
      console.log(paymentInfo.merchantEmail);
      console.log(paymentInfo.merchantOrderId);
    return this.http.post(this.zuulUrl + "/createPayment", paymentInfo, {responseType: 'text'});
  }

  bitcoinCompleatePayment(paymentInfo : String){
    return this.http.get(this.zuulUrl + "/completePayment/"+ paymentInfo);
  }

  bitcoinCancelPayment(paymentInfo : String){
    return this.http.get(this.zuulUrl + "/cancelPayment/" + paymentInfo);
  }
}
