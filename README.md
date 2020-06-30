<p>Execute Spring boot it will be running in port 8080</p>
<p>To see in-memory database H2 in the web browser</p>
<p>http://localhost:8080/h2-console</p>
    <p>user: rsilva</p>
    <p>pwd:123456</p>

<p>Tables</p>
<p>SELECT * FROM CUSTOMER;</p>
<p>SELECT * FROM PURCHASE_ORDER;</p>
<p>SELECT * FROM ORDER_PRODUCT;</p>
<p>SELECT * FROM PRODUCT;</p>

<p>To call all purchases by Customer</p>
<p>http://localhost:8080/purchase/OrderByCustomerName?name=Customer%201</p>

<p>To see all Customers</p>
<p>http://localhost:8080/purchase/AllCustomer</p>
