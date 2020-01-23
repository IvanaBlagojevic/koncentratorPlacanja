export class UserBankDTO{
    id : number;
	
	merchantId: String;
	
	merchantPassword: String;
	
	merchantEmail: String;

	constructor(merchantEmail : String, merchantId : String, merchantPassword : String){
		this.merchantEmail = merchantEmail;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
	}
}

