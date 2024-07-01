CREATE TABLE `customer` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `name` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `phone` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL,
PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `customer` (`id`, `name`, `phone`) VALUES
(1, 'fad' , '089572345832'),
(2, 'dhly', '089575225832'),
(3, 'al'  , '083434412867'),
(4, 'fari', '081577832381'),
(5, 'rizi', '082946634512');

CREATE TABLE `cashier` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `name` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `cashier` (`id`, `name`) VALUES
(1, 'jeane'),
(2, 'yugo'),
(3, 'sebas'),
(4, 'kuro'),
(5, 'nanse');

CREATE TABLE `invoice` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `customer_id` INT NOT NULL,
           `cashier_id` INT NOT NULL,
           `amount` INT NOT NULL,
           `created_date` date NOT NULL,
PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `invoice`
    ADD FOREIGN KEY (`customer_id`) REFERENCES customer(id);

ALTER TABLE `invoice`
    ADD FOREIGN KEY (`cashier_id`) REFERENCES cashier(id);

INSERT INTO `invoice` (`id`, `customer_id`, `cashier_id`, `amount`, `created_date`) VALUES
(1, 1, 1, 10000, '2024-05-15'),
(2, 1, 1, 20000, '2024-05-16'),
(3, 2, 5, 30000, '2024-05-16'),
(4, 5, 2, 20000, '2024-06-01'),
(5, 3, 4, 15000, '2024-06-02'),
(6, 4, 4, 25000, '2024-06-02');

CREATE TABLE `product` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `name` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `price` INT NOT NULL,
PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `product` (`id`, `name`, `price`) VALUES
(1, 'Shirt', 10000),
(2, 'Jacket', 15000),
(3, 'Dress', 20000);

CREATE TABLE `invoice_detail` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `quantity` INT NOT NULL,
           `product_id` INT NOT NULL,
           `product_price` INT NOT NULL,
           `invoice_id` INT NOT NULL,
           `amount` INT NOT NULL,
PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `invoice_detail`
    ADD FOREIGN KEY (`product_id`) REFERENCES product(id);

ALTER TABLE `invoice_detail`
    ADD FOREIGN KEY (`invoice_id`) REFERENCES invoice(id);

INSERT INTO `invoice_detail` (`id`, `quantity`, `product_id`, `product_price`, `invoice_id`, `amount`) VALUES
(1, 1, 1, 10000, 1, 10000),
(2, 2, 1, 10000, 2, 20000),
(3, 1, 1, 10000, 3, 10000),
(4, 1, 3, 20000, 3, 20000),
(5, 1, 3, 20000, 4, 20000),
(6, 1, 2, 15000, 5, 15000),
(7, 1, 1, 10000, 6, 10000),
(8, 1, 2, 15000, 6, 15000);