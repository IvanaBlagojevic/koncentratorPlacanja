import { Component, OnInit } from '@angular/core';
import { TestService } from '../service/testService/test.service';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { PaymentData } from '../model/PaymentData';
import { PaymentService } from '../service/paymentService/payment.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  payment : PaymentData = new PaymentData();
  errorMessage = '';
  
  constructor(private router: ActivatedRoute, private testService: TestService,
    private paymentService: PaymentService) { 
    this.testService.getTest().subscribe(data=>{
      alert("uspeh "+data);
    })
  }

  ngOnInit() {
  }
  

  
  sign(){
    this.paymentService.createPayment(this.payment).subscribe(
        data =>{
         alert("Payment successfully registered!");
        },error =>{this.handleAuthError(error)});
  }    
  

  handleAuthError(err : HttpErrorResponse) {

    if(err.status === 400){
      alert("This data is not valid!");
    }else
    {
      alert("Problem");
    }
  }

  
}
