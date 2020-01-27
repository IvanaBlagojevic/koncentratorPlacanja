insert into user_pay_pal(username,client_id,client_secret) values ("1111-2222","AboVa2o5REedvjt-AO-30c84Mken1J-bQ5_YgoOWd1lJzFgDt8GzVo5OG9omfgae8idotWlLmXk1tHNQ","EFA16-8S9eYP1i7GTtkgEcZLrS30UWCCN90Hm4EHqx33KBCC-7ZHrIzFiNH-cLW9cUl6oLxzGaxLttpx");

--keirani planovi
insert into plan_for_billing(active,frequency,plan_id,type) values (true,"INFINITE","P-3EM74354A255918364RERRUY","DAY");
insert into plan_for_billing(active,frequency,plan_id,type) values (true,"INFINITE","P-25575031C0497704R47LC2ZI","MONTH");
insert into plan_for_billing(active,frequency,plan_id,type) values (true,"INFINITE","P-7RH55289T7507783C47LMSZQ","WEEK");
insert into plan_for_billing(active,frequency,plan_id,type) values (true,"INFINITE","P-03J67950LA685063D47L364I","YEAR");

--kreirani ugovori(preplata obavljena)
insert into agreement_for_billing(agreement_id,username) values ("I-Y0E1K3DEFJNR", "1111-2222");