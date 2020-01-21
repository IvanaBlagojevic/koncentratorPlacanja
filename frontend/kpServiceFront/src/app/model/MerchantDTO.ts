import { MethodOfPaymentDTO } from './MethodOfPaymentDTO';

export class MerchantDTO {

    id : number;
    companyName : string;
    merchantName : string;
    username : string;
    supportedMethods : Array<MethodOfPaymentDTO>;

}