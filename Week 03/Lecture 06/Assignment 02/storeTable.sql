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
(2, 'yugo');

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

CREATE TABLE `product` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `name` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `price` INT NOT NULL,
PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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