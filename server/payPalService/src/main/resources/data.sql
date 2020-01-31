insert into user_pay_pal(username,client_id,client_secret) values ("1111-2222","AboVa2o5REedvjt-AO-30c84Mken1J-bQ5_YgoOWd1lJzFgDt8GzVo5OG9omfgae8idotWlLmXk1tHNQ","EFA16-8S9eYP1i7GTtkgEcZLrS30UWCCN90Hm4EHqx33KBCC-7ZHrIzFiNH-cLW9cUl6oLxzGaxLttpx");
insert into user_pay_pal(username,client_id,client_secret) values ("2222-2222","AeGC7A1Z3Hg5THe74Y696gpAVlrzY1w6nm2r-Gy81tHAn761UtMpsMYPBpLtAg0ldb5p8iBea7ojTi1-","EPvL7pRy3iJKE5sd6HNFi7jRgKldH6sdTcfAZ1kfh6yXJ13ct-kow98db8upO9nUAayaGFXWReKWJ88t");

--keirani planovi
insert into plan_for_billing(active,frequency,plan_id,type) values (true,"INFINITE","P-7AJ82571DF828421A7C2QQHI","DAY");
insert into plan_for_billing(active,frequency,plan_id,type) values (true,"INFINITE","P-9HT753127D157811R7C2WK3A","DAY");

--kreirani ugovori(preplata obavljena)
insert into agreement_for_billing(agreement_id,username,token) values ("I-1L0NU5JWX8K5", "1111-2222","EC-77F96609X62847633");
insert into agreement_for_billing(agreement_id,username,token) values ("I-N61AN65R3BGN", "2222-2222","EC-2BT17450JP788143W");