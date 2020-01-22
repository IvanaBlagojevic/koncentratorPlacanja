insert into user(name,surname,email) values ('Marko','Markovic','marko@gmail.com');
insert into user(name,surname,email) values ('Nikola','Nikolic','nikola@gmail.com');

insert into method_of_payment(name, path) values ('Card','/bankService');
insert into method_of_payment(name, path) values ('PayPal','/payPalService');
insert into method_of_payment(name, path) values ('Bitcoin','/bitcoinService');

insert into payment_info(id,merchant_issn,user_email,order_number_id,is_paid,payment_method) values (1,'2222-2222','ivana',25,'PAID',"bitcoinService");

insert into method_of_payment_fields(code, name, type) values ('MERCHANT_ID', 'Merchant id' ,'text');
insert into method_of_payment_fields(code, name, type) values ('MERCHANT_PASSWORD', 'Merchant password' ,'password');
insert into method_of_payment_fields(code, name, type) values ('TOKEN', 'Token' ,'text');
insert into method_of_payment_fields(code, name, type) values ('CLIENT_ID', 'Client id' ,'text');
insert into method_of_payment_fields(code, name, type) values ('CLIENT_PASSWORD', 'Client password' ,'text');

--Banka--
insert into payment_fields(method_id,field_id) values (1,1); --Merchant id
insert into payment_fields(method_id,field_id) values (1,2); --Merhcant password

--PayPal--
insert into payment_fields(method_id,field_id) values (2,4); --Client id
insert into payment_fields(method_id,field_id) values (2,5); --Client password

--Bitcoin--
insert into payment_fields(method_id,field_id) values (3,3); --Token

--naucne centrale--
insert into merchant_system(system_name) values ('Naucna centrala 1');

--registrovani prodavci--
insert into merchant(name, username, system_id) values ('Time', '1111-2222', 1);
insert into merchant_payment_methods(merchant_id, payment_methods_id) values (1,2); --Podrzava PayPal placanje
insert into merchant_payment_methods(merchant_id, payment_methods_id) values (1,1);
insert into merchant_payment_methods(merchant_id, payment_methods_id) values (1,3);
