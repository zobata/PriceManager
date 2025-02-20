CREATE TABLE brand (
    brand_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE product (
    product_id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE currency (
    currency_code CHAR(3) PRIMARY KEY,
    currency_name VARCHAR(255) NOT NULL
);

CREATE TABLE price_list (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id INT,
    product_id BIGINT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    priority INT,
    price DECIMAL(10, 2),
    currency_code CHAR(3),
    FOREIGN KEY (brand_id) REFERENCES brand(brand_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (currency_code) REFERENCES currency(currency_code)
);

INSERT INTO brand (brand_id, name) VALUES (1, 'ZARA');

INSERT INTO product (product_id, name, description) VALUES (35455, 'Producto 35455', 'Descripción del producto 35455');

INSERT INTO currency (currency_code, currency_name) VALUES ('EUR', 'Euro');

INSERT INTO price_list (brand_id, product_id, start_date, end_date, priority, price, currency_code) VALUES
(1, 35455, '2020-06-14T00:00:00', '2020-12-31T23:59:59', 0, 35.50, 'EUR'),
(1, 35455, '2020-06-14T15:00:00', '2020-06-14T18:30:00', 1, 25.45, 'EUR'),
(1, 35455, '2020-06-15T00:00:00', '2020-06-15T11:00:00', 1, 30.50, 'EUR'),
(1, 35455, '2020-06-15T16:00:00', '2020-12-31T23:59:59', 1, 38.95, 'EUR');