CREATE TABLE `employee` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `name` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `department` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `age` INT NOT NULL,
PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;