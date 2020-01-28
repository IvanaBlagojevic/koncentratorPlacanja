insert into roles(name) values('ROLE_ADMIN');

INSERT INTO `user` (`email`, `name`, `password`, `surname`) VALUES ('nikola@gmail.com', 'nikola', '$2b$10$Fteo8iSDjs2OWFQE/4Ruw.88AXPrVDfb2v4v0SGeAD8e6uwsCkLsG', 'grujcic');

insert into user_roles(user_id,role_id) values (1,1);