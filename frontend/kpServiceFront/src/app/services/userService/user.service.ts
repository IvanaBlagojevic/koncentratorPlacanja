import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http : HttpClient) { }
  url ="https://localhost:8086/authService/"
  logout(email : String){
    return this.http.get(this.url+'auth/logout/'+email+'/');
  }
  
  getUserByEmail(email : String){
    return this.http.get(this.url+'user/email/'+email+'/');
  }

  
}
