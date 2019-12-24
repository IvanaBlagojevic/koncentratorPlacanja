import { Injectable } from '@angular/core';
import { ResponseToKP } from 'src/app/model/ResponseToKP';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private http:HttpClient) { }

  saveData(buyer: ResponseToKP){
    return this.http.post<ResponseToKP>('https://localhost:8089/payment/saveResponse',buyer);
  }

}
