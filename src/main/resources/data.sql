DROP TABLE IF EXISTS purchase_order;
DROP TABLE IF EXISTS order_product;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    full_name VARCHAR(250) NOT NULL,
    address VARCHAR(250) NOT NULL,
    phone_number VARCHAR(250) NOT NULL,
);

CREATE TABLE product(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    descritption VARCHAR(250) NOT NULL,
    category VARCHAR(250) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    weight DECIMAL(10, 2) NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE purchase_order(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    id_customer INT,
    id_product INT,
    cost DECIMAL(10, 2) NOT NULL,
    order_date TIMESTAMP NOT NULL
    foreign key (id_product) references product(id),
    foreign key (id_customer) references customer(id)
);

CREATE TABLE order_product(
    order_id INT,
    product_id INT,
    quantity INT,
    foreign key (order_id) references purchase_order(id),
    foreign key (product_id) references product(id)
);


