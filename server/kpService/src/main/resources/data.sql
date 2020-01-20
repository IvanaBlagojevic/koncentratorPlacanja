insert into user(name,surname,email) values ('Marko','Markovic','marko@gmail.com');
insert into user(name,surname,email) values ('Nikola','Nikolic','nikola@gmail.com');

insert into method_of_payment(name, path) values ('Card','/bankService');
insert into method_of_payment(name, path) values ('PayPal','/payPalService');
insert into method_of_payment(name, path) values ('Bitcoin','/bitcoinService');

insert into payment_info(id,merchant_email,user_email,order_number_id,is_paid,payment_method) values (1,'pera@gmail.com','ivana',25,false,"Bitcoin");