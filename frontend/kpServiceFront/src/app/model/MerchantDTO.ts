import { MethodOfPaymentDTO } from './MethodOfPaymentDTO';
import { SubscriptionDTO } from './SubscriptionDTO';
import { MerchantSystemDTO } from './MerchantSystemDTO';

export class MerchantDTO {

    id : number;
    companyName : string;
    merchantName : string;
    username : string;
    SCsystem : MerchantSystemDTO;
    supportedMethods : Array<MethodOfPaymentDTO>;
    subscriptions : Array<SubscriptionDTO>;
}