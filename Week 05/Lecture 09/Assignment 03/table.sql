CREATE TABLE `employee` (
           `id` varchar(50) NOT NULL,
           `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `dateofbirth` date NOT NULL,
           `address` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
           `department` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `salary` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `employee`
    ADD PRIMARY KEY (`id`);