export class Subscription{
        id : number;
		type : SubscriptionType;
		frequency : number;
		price : number;
		email : string;
}

enum SubscriptionType{
    WEEK,
    DAY,
    MONTH,
    YEAR
}