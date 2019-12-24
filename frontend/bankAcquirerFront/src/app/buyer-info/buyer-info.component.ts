import { Component, OnInit } from '@angular/core';
import { BuyerInfoService } from '../service/buyerInfo/buyer-info.service';
import { ActivatedRoute } from '@angular/router';
import { BuyerInfo } from '../model/BuyerInfo';
import { BankService } from '../service/bankService/bank.service';
import { ResponseToKP } from '../model/ResponseToKP';

@Component({
  selector: 'app-buyer-info',
  templateUrl: './buyer-info.component.html',
  styleUrls: ['./buyer-info.component.css']
})
export class BuyerInfoComponent implements OnInit {

  buyer : BuyerInfo = new BuyerInfo();
  errorMessage = '';
  id: String;
  res: ResponseToKP=new ResponseToKP();
  constructor(private router: ActivatedRoute,private buyerService: BuyerInfoService, private bankService: BankService) { 
    this.id = this.router.snapshot.params.id
    this.buyer.paymentId=this.id;

  }

  ngOnInit() {
  }

  sign(){
    this.buyerService.createBuyer(this.buyer).subscribe(
      data =>{
        alert("successfully registered -> "+data.url);
        
        /*this.res.url=data.url;
        this.res.acquirerOrderId=data.acquirerOrderId.toString();
        this.res.acquirerTimestamp=data.acquirerTimestamp;
        //this.res.cardHolderName=data.cardHolderName;
        this.res.merchantOrderId=data.merchantOrderId.toString();
        this.res.paymentId=data.paymentId.toString();
        *///this.res.status=data.status;
        //console.log("********* "+data.url);
        //this.bankService.saveData(this.res).subscribe(
          //respose=>{
            window.location.href =data.url;
        //});
        //window.location.href =data.url;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
    
      }
    );
  }

}
