export class MethodPaymentFieldsDTO {
    
    id : number;
	
	code : MethodOfPaymentFieldName;
	
	name : string;
	
	type : string;
	
    value : string;
    
}

enum MethodOfPaymentFieldName{
    MERCHANT_ID,
    MERCHANT_PASSWORD,
    TOKEN,
    CLIENT_ID,
    CLIENT_PASSWORD
}