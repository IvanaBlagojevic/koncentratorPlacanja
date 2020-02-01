
insert into method_of_payment(name, path) values ('Card','/bankService');
insert into method_of_payment(name, path) values ('PayPal','/payPalService');
insert into method_of_payment(name, path) values ('Bitcoin','/bitcoinService');

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
insert into merchant_system(system_name,front_url, back_url) values ('Naucna centrala 1','https://localhost:4202','https://localhost:8088');

--registrovani prodavci--
insert into merchant(name, username, system_id) values ('Time', '1111-2222', 1);
insert into merchant_payment_methods(merchant_id, payment_methods_id) values (1,2); --Podrzava PayPal placanje
insert into merchant_payment_methods(merchant_id, payment_methods_id) values (1,1);
insert into merchant_payment_methods(merchant_id, payment_methods_id) values (1,3);

insert into merchant(name, username, system_id) values ('Architectural digest', '2222-2222', 1);
insert into merchant_payment_methods(merchant_id, payment_methods_id) values (2,2); --Podrzava PayPal placanje

--vec napravljeni billing planovi
insert into subscription_plan(frequency,period,plan_id,price,merchant_id) values (1,"DAY","P-7AJ82571DF828421A7C2QQHI",1,1);
insert into subscription_plan(frequency,period,plan_id,price,merchant_id) values (1,"DAY","P-9HT753127D157811R7C2WK3A",6,2);



--INSERT INTO `payment_info`(`amount`,`created`,`errorurl`,`failedurl`,`is_paid`,`merchant_issn`,`order_number_id`,`order_numbernc`,`payment_method`,`successurl`,`updated`,`user_email`) VALUES (2.5,NULL,'https://localhost:4202/error','https://localhost:4202/failed','CREATED',
--'1111-2222',NULL,'00c6f74a-30e7-4579-868c-4067d788ce47',NULL,'https://localhost:4202/success',NULL,'ljuba@gmail.com');

--vec preplaceni korisnici
insert into subscription(active,agreement_id,merchant_username,plan_id,price_amount,sub_email, type) values (true, "I-1L0NU5JWX8K5", "1111-2222", "P-7AJ82571DF828421A7C2QQHI", 1, "dana@gmail.com", "DAY");
insert into subscription(active,agreement_id,merchant_username,plan_id,price_amount,sub_email, type) values (true, "I-N61AN65R3BGN", "2222-2222", "P-9HT753127D157811R7C2WK3A", 6, "dana@gmail.com", "DAY");



