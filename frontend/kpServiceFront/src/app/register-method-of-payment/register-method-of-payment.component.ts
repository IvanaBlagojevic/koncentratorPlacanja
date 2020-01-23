import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../services/payment.service';
import { MethodOfPaymentDTO } from '../model/MethodOfPaymentDTO';
import { MethodPaymentFieldsDTO } from '../model/MethodPaymentFieldsDTO';

@Component({
  selector: 'app-register-method-of-payment',
  templateUrl: './register-method-of-payment.component.html',
  styleUrls: ['./register-method-of-payment.component.css']
})
export class RegisterMethodOfPaymentComponent implements OnInit {

  private method = new MethodOfPaymentDTO();
  private methodsFields = Array<MethodPaymentFieldsDTO>();
  private types= ["text","checkbox","number","email","radio","date","time","password","file"]
  constructor(private paymentService : PaymentService) { }

  ngOnInit() {
  }

  addField(){
    let newM = new MethodPaymentFieldsDTO();
   
    this.methodsFields.push(newM);
  }
  onSubmit(){

    this.method.fields=this.methodsFields;
    this.method.fields.forEach(element=>{
      console.log(element.name+" "+element.code+" "+element.type);
    })
    this.paymentService.createMethodOfPayment(this.method).subscribe(
      data=>{
        alert("Merchant " + this.method.name + " registrated!");
          window.location.href = "https://localhost:1234";
        },err => {
          alert("Error while adding method of payment!");
        }
      );
  }
    
    
  
  

}

