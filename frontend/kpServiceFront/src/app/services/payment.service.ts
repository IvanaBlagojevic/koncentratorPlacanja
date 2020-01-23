import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentDTO } from '../model/PaymentDTO';
import { Observable } from 'rxjs';
import { MethodOfPaymentDTO } from '../model/MethodOfPaymentDTO';
import { MerchantDTO } from '../model/MerchantDTO';
import { PaymentInfoDTO } from '../model/PaymentInfoDTO';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http : HttpClient) { }

  zuul_url = "https://localhost:8086";

  //dobavljanje svih metoda placanja
  getPaymentMethods() : Observable<any>{
    return this.http.get(this.zuul_url + "/kpService/methodOfPayment/getAll")
  }

  //dodavanje novog metoda placanja
  createMethodOfPayment(paymentInfo : MethodOfPaymentDTO) : Observable<any>{ 
    return this.http.post(this.zuul_url + "/kpService/methodOfPayment/create",paymentInfo);
  }

  //dobavljanje podrzanih metoda nekog casopisa
  getSupportedPaymentMethods(issn : String) : Observable<any>{

    return this.http.get(this.zuul_url + "/kpService/merchant/supportedMethods/"+issn);
    
  }

  //payPalService
  payPalCreatePayment(paymentInfo : PaymentInfoDTO, paymentMethod : String, orderId: String) : Observable<any>{ 
    //ovde sam samo service dodala - proveriti sa Danicom
    return this.http.post(this.zuul_url  + paymentMethod  + "/create/"+orderId, paymentInfo, { responseType: 'text'} );
  }

  payPalCompletePayment(paymentId : String, payerId : String, username : String, paymentMethod : String) : Observable<any>{

    return this.http.get(this.zuul_url + "/" + paymentMethod + "/complete/"+paymentId + "/"+payerId + "/" + username,  { responseType: 'text'} ); 
  }

  payPalCancelPayment(id : number)
  {
    return this.http.get(this.zuul_url + "/payPalService/cancel/"+id, {responseType: 'text'});
  }

  registerMerchant(merchant : MerchantDTO){

    return this.http.post(this.zuul_url+"/kpService/merchant/registerMerchant", merchant, { responseType: 'text'});
  }

  //gadjanje banke, paypal-a ili bitcoin-a radi dodavanja user-a sa podacima
  addUserInPaymentService(path : String, dto : any){

    return this.http.post(this.zuul_url  + path + "/addUser", dto, { responseType: 'text'}); //ako neko bude vracao json ovo ce se menjati
  }

  getMerchantByUsername(username : String) : Observable<any>{

    return this.http.get(this.zuul_url + "/kpService/merchant/getMerchant/"+username);
  }

}
