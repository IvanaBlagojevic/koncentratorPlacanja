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
      //alert("uspeh "+data);
    })
  }

  ngOnInit() {
  }
  

  
  sign(){
   // this.paymentService.createPayment(this.payment).subscribe(
   //     data =>{
   //      alert("Payment successfully registered!");
   //     },error =>{this.handleAuthError(error)});
   console.log(this.payment.merchantEmail);
   console.log(this.payment.amount);

   if(this.isBlank(this.payment.merchantEmail))
   {
     alert("Fill email field");
   }else if (this.isBlank(this.payment.amount))
   {
     alert("Fill amount field");
   }else
   {
    window.location.href = "https://localhost:1234/"+ this.payment.merchantEmail + "/" + this.payment.amount;
   }

  }    
  

  handleAuthError(err : HttpErrorResponse) {

    if(err.status === 400){
      alert("This data is not valid!");
    }else
    {
      alert("Problem");
    }
  }

  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

  
}
