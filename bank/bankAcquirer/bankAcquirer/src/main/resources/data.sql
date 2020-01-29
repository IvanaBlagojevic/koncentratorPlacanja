INSERT INTO `merchant` (`id`,`merchant_id`,`merchant_password`, `account_id`) VALUES (1,'id','pass','4');

INSERT INTO `account` (`id`,`account_number`,`amount`) VALUES (1,'1111111111111111',50000);
INSERT INTO `account` (`id`,`account_number`,`amount`) VALUES (2,'1111112222222222',70000);
INSERT INTO `account` (`id`,`account_number`,`amount`) VALUES (3,'1111113333333333',65000);
INSERT INTO `account` (`id`,`account_number`,`amount`) VALUES (4,'1111114444444444',45000);

INSERT INTO `payment` (`id`,`amount`,`error_url`,`failed_url`,`merchant_id`,`merchant_order_id`,`merchant_password`,`merchant_timestamp`,`payment_id`,`payment_url`,`status`,`success_url`) VALUES (1,2000,'https://localhost:4200/error','https://localhost:4200/failed','id',1,'pass','2019-11-12 23:28:57',1,'http://localhost:4201/payment/1',NULL,'https://localhost:4200/success');
INSERT INTO `payment` (`id`,`amount`,`error_url`,`failed_url`,`merchant_id`,`merchant_order_id`,`merchant_password`,`merchant_timestamp`,`payment_id`,`payment_url`,`status`,`success_url`) VALUES (2,5000,'https://localhost:4200/error','https://localhost:4200/failed','id',2,'pass','2019-11-12 23:28:57',2,'http://localhost:4201/payment/2',NULL,'https://localhost:4200/success');


INSERT INTO `card` (`id`, `expiry_date`, `secure_code`, `account_id`,`pan`) VALUES ('1', '2020-01-01 00:00:00', '1234', '1','1111111111111111');
INSERT INTO `card` (`id`, `expiry_date`,`secure_code`, `account_id`,`pan`) VALUES ('2', '2020-01-01 00:00:00', '5555', '2','1111112222222222');

