CREATE TABLE CATEGORY
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO category (`id`, `name`)
VALUES (1, 'Category 1'),
       (2, 'Category 2'),
       (3, 'Category 3');


CREATE TABLE PRODUCTS
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    model       VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    category_id BIGINT      NOT NULL,
    price       DOUBLE      NOT NULL,
    foreign key (category_id) references CATEGORY (id)
);

INSERT INTO PRODUCTS (`name`, `model`, `description`, `price`, `category_id`)
VALUES ('Name 1', 'Model 1', 'Description 1', 10, 1),
       ('Name 2', 'Model 2', 'Description 2', 15, 1),
       ('Name 3', 'Model 3', 'Description 3', 25, 1),
       ('Name 4', 'Model 4', 'Description 4', 20, 2),
       ('Name 5', 'Model 5', 'Description 5', 45, 3),
       ('Name 6', 'Model 6', 'Description 6', 80, 3);