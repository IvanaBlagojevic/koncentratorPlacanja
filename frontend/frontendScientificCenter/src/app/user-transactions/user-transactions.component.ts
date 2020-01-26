import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../service/transactionService/transaction.service';

@Component({
  selector: 'app-user-transactions',
  templateUrl: './user-transactions.component.html',
  styleUrls: ['./user-transactions.component.css']
})
export class UserTransactionsComponent implements OnInit {

  trans = [];
  displayedColumns: string[] = ['Journal', 'Amount', 'Status'];
  constructor(private ts : TransactionService) { 
    
    ts.get().subscribe(res => {
      this.trans = res;
      console.log("trans " + this.trans);
      console.log("res " + res);
    })
  }

  ngOnInit() {
  }

}
