insert into customer(full_name,address,phone_number) values('Customer 1','Av abc num:11','12334455');
insert into customer(full_name,address,phone_number) values('Customer 2','Av abc num:12','21212121');
insert into customer(full_name,address,phone_number) values('Customer 3','Av abc num:13','44444444');
insert into customer(full_name,address,phone_number) values('Customer 4','Av abc num:14','54545454');
insert into customer(full_name,address,phone_number) values('Customer 5','Av abc num:15','87565453');

insert into product(descritption,category,price,weight,creation_date) values('Book A','Book',10.90,0.350,parsedatetime('01-01-2019', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book B','Book',10.90,0.200,parsedatetime('01-02-2019', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book C','Book',2.90,0.80,parsedatetime('01-01-2020', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book D','Book',43.90,0.100,parsedatetime('01-02-2020', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book E','Book',99.90,0.300,parsedatetime('01-05-2020', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book F','Book',5.90,0.450,parsedatetime('01-01-2019', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book AAA','E-Book',10.90,0,parsedatetime('01-01-2020', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book BBB','E-Book',30.90,0,parsedatetime('01-07-2019', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book CCC','E-Book',10.90,0,parsedatetime('01-06-2018', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book DDD','E-Book',7.90,0,parsedatetime('01-01-2020', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Book FFF','E-Book',1.90,0,parsedatetime('01-08-2019', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Paper White','Kindle',199.99,0.850,parsedatetime('01-02-2020', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Kindle 10a','Kindle',89.99,0.800,parsedatetime('01-04-2018', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Kindle  9a','Kindle',00.00,0.700,parsedatetime('01-09-2019', 'dd-MM-yyyy'));
insert into product(descritption,category,price,weight,creation_date) values('Kindle  8a','Kindle',00.00,0.700,parsedatetime('01-06-2020', 'dd-MM-yyyy'));

insert into purchase_order(id_customer,cost,shipping,order_date) values(1,10.90,2,parsedatetime('05-01-2020', 'dd-MM-yyyy'));
insert into purchase_order(id_customer,cost,shipping,order_date) values(1,274.79,0,parsedatetime('22-02-2020', 'dd-MM-yyyy'));
insert into purchase_order(id_customer,cost,shipping,order_date) values(2,0,0,parsedatetime('30-06-2020', 'dd-MM-yyyy'));
insert into purchase_order(id_customer,cost,shipping,order_date) values(3,0,0,parsedatetime('01-08-2019', 'dd-MM-yyyy'));

insert into order_product(order_id,product_id,quantity) values(1,1,1);
insert into order_product(order_id,product_id,quantity) values(2,4,1);
insert into order_product(order_id,product_id,quantity) values(2,8,1);
insert into order_product(order_id,product_id,quantity) values(3,9,1);
insert into order_product(order_id,product_id,quantity) values(3,10,1);
insert into order_product(order_id,product_id,quantity) values(4,2,1);
insert into order_product(order_id,product_id,quantity) values(4,5,1);



