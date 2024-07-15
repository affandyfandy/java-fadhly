CREATE TABLE `employee` (
           `id` binary(16) NOT NULL,
           `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `dateofbirth` date NOT NULL,
           `address` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
           `department` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           `email` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
           `phone` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;