Execute Spring boot it will be running in port 8080
To see in-memory database H2 in the web browser
http://localhost:8080/h2-console
    user: rsilva
    pwd:123456

Tables
SELECT * FROM CUSTOMER;
SELECT * FROM PURCHASE_ORDER;
SELECT * FROM ORDER_PRODUCT;
SELECT * FROM PRODUCT;

To call all purchases by Customer
http://localhost:8080/purchase/OrderByCustomerName?name=Customer 1

To see all Customers
http://localhost:8080/purchase/AllCustomer
