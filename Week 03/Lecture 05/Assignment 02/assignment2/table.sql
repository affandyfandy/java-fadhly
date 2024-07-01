CREATE TABLE `employee` (
           `id` varchar(50) NOT NULL,
           `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `dateofbirth` date NOT NULL,
           `address` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
           `department` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `employee`
    ADD PRIMARY KEY (`id`);

INSERT INTO `employee` (`id`, `name`, `dateofbirth`, `address`, `department`) VALUES
('ABC_1', 'Stesha Benyan', '1981-10-23', '6 Ronald Regan Court', 'SYSTEM'),
('ABC_2', 'Alf McTiernan', '1990-12-14', '9390 Utah Way', 'WEB');