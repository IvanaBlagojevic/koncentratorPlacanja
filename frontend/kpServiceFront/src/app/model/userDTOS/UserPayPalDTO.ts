export class UserPayPalDTO{
    id : number;
	username : String;
	clientId : String;
	clientSecret : String;

	constructor(username : String, clientId : String, clientSecret : String){
		this.username = username;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
}