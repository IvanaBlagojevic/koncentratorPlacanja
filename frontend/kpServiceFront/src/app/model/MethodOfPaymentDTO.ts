import { MethodPaymentFieldsDTO } from './MethodPaymentFieldsDTO';

export class MethodOfPaymentDTO {

    id : number;
    name : String;
    path : String;
    //img : String;
    fields : Array<MethodPaymentFieldsDTO>;

}