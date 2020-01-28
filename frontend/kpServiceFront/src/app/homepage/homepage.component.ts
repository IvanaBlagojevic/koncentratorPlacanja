import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BitcoinDTO } from '../model/BitcoinDTO';
import { PaymentService } from '../services/payment.service';
import { PaymentDTO } from '../model/PaymentDTO';
import { MethodOfPaymentDTO } from '../model/MethodOfPaymentDTO';
import { BitcoinServiceService } from '../services/bitcoinService/bitcoin-service.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { UserService } from '../services/userService/user.service';
import { User } from '../model/User';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  
  messages: any;
  clicked: boolean=false;
  myArray: any;
  someoneLogged : boolean = false;
  email : string = "";
  adminLogged : boolean = false;
  editorLogged : boolean = false;
  roles: string[];
  loggedUser : User;
  recenzentLogged: boolean = false;
 


  constructor(private router: ActivatedRoute, private tokenStorage : TokenStorageService,
    private userServ : UserService ) {
   }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.someoneLogged = true;
      
      let jwt = this.tokenStorage.getToken();
      console.log("Tokeen: " + jwt);
      let jwtData = jwt.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
      this.email = decodedJwtData.sub;


      this.userServ.getUserByEmail(this.email).subscribe(data =>{
        this.loggedUser = data as User;

        this.loggedUser.roles.forEach(element =>{
          console.log("Uloga: " + element.name);
          if(element.name === "ROLE_ADMIN")
          {
            this.adminLogged = true;
          }else if(element.name=== "ROLE_EDITOR"){
            this.editorLogged = true;
          }else if(element.name === "ROLE_RECENZENT"){
            this.recenzentLogged = true;
          }
        });
      });
    }
    
  }

  logout(){
    this.tokenStorage.signOut();
    this.someoneLogged = false;
    this.userServ.logout(this.email).subscribe(data =>{
      window.location.href="https://localhost:1234";
    });
    
  }
  
}

