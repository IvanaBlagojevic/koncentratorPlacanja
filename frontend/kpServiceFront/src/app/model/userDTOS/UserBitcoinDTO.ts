export class UserBitcoinDTO {
    id : number;
	username : String;
	token : String;

	constructor(username : String, token : String) {
		this.username = username;
		this.token = token;

	}
}