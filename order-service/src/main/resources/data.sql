CREATE TABLE ORDERS
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    create_date DATE NOT NULL,
    customer_id BIGINT NOT NULL,
    order_code VARCHAR(50),
    status VARCHAR(20),
    total_value DOUBLE,
    last_update DATE NOT NULL
);

INSERT INTO ORDERS (create_date, customer_id, order_code, status, total_value, last_update)
VALUES ('2021-10-12', 1, 'ORDER/2021-11-04/5308', 'CREATED', 120, '2021-10-15');
INSERT INTO ORDERS (create_date, customer_id, order_code, status, total_value, last_update)
VALUES ('2021-10-13', 2, 'ORDER/2021-11-04/2145', 'CREATED', 90, '2021-10-15');
INSERT INTO ORDERS (create_date, customer_id, order_code, status, total_value, last_update)
VALUES ('2021-10-13', 2, 'ORDER/2021-11-04/4117', 'CREATED', 100, '2021-10-15');

CREATE TABLE ITEMS
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    quantity INT,
    value BIGINT,
    order_id BIGINT,
    foreign key (order_id) references ORDERS(id)
);

INSERT INTO ITEMS (product_id, quantity, value, order_id) VALUES (1, 2, 20, 1);
INSERT INTO ITEMS (product_id, quantity, value, order_id) VALUES (2, 1, 15, 2);
INSERT INTO ITEMS (product_id, quantity, value, order_id) VALUES (3, 4, 100, 1);
INSERT INTO ITEMS (product_id, quantity, value, order_id) VALUES (3, 3, 75, 2);
INSERT INTO ITEMS (product_id, quantity, value, order_id) VALUES (3, 4, 100, 3);

