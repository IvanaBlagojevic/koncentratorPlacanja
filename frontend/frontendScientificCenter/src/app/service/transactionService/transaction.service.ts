import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { create } from 'domain';
import { Transaction } from 'src/app/model/Transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

    constructor(private http : HttpClient) { }
    
    url ="https://localhost:8088/"
    
    create(transaction : Transaction){
      return this.http.post<Transaction>(this.url+'transaction/create',transaction);
    }
    
}
