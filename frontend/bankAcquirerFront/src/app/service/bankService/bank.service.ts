import { Injectable } from '@angular/core';
import { ResponseToKP } from 'src/app/model/ResponseToKP';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private http:HttpClient) { }
  zuulUrl :"https://localhost:8086/bankService";
  saveData(buyer: ResponseToKP){
    return this.http.post<ResponseToKP>(this.zuulUrl+'/payment/saveResponse',buyer);
  }

}
