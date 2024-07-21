DROP DATABASE if EXISTS `assignment1lecture12`;

CREATE DATABASE `assignment1lecture12`;

USE `assignment1lecture12`;

CREATE TABLE `employees` (
    `emp_no` int(11) NOT NULL AUTO_INCREMENT,
    `birth_date` date NOT NULL,
    `first_name` varchar(14) COLLATE utf8mb4_unicode_ci NOT NULL,
    `last_name` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
    `gender` enum('M', 'F') COLLATE utf8mb4_unicode_ci NOT NULL,
    `hire_date` date NOT NULL,
    PRIMARY KEY (`emp_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `departments` (
    `dept_no` char(4) COLLATE utf8mb4_unicode_ci NOT NULL,
    `dept_name` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL UNIQUE,
    PRIMARY KEY (`dept_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `dept_emp` (
    `emp_no` int(11) NOT NULL,
    `dept_no` char(4) COLLATE utf8mb4_unicode_ci NOT NULL,
    `from_date` date NOT NULL,
    `to_date` date NOT NULL,
    PRIMARY KEY (`emp_no`, `dept_no`),
    FOREIGN KEY (`emp_no`) references employees(`emp_no`) on update cascade on delete cascade,
    FOREIGN KEY (`dept_no`) references departments(`dept_no`) on update cascade on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `dept_manager` (
    `emp_no` int(11) NOT NULL,
    `dept_no` char(4) COLLATE utf8mb4_unicode_ci NOT NULL,
    `from_date` date NOT NULL,
    `to_date` date NOT NULL,
    PRIMARY KEY (`emp_no`, `dept_no`),
    FOREIGN KEY (`dept_no`) references departments(`dept_no`) on update cascade on delete cascade,
    FOREIGN KEY (`emp_no`) references employees(`emp_no`) on update cascade on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `salaries` (
    `emp_no` int(11) NOT NULL,
    `salary` int(11) NOT NULL,
    `from_date` date NOT NULL,
    `to_date` date NOT NULL,
    PRIMARY KEY (`emp_no`, `from_date`),
    FOREIGN KEY (`emp_no`) references employees(`emp_no`) on update cascade on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `titles` (
    `emp_no` int(11) NOT NULL,
    `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
    `from_date` date NOT NULL,
    `to_date` date NOT NULL,
    PRIMARY KEY (`emp_no`, `title`, `from_date`),
    FOREIGN KEY (`emp_no`) references employees(`emp_no`) on update cascade on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- Dummy Data
INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date)
VALUES
    (1, '1980-01-01', 'John', 'Doe', 'M', '2005-06-01'),
    (2, '1981-10-23', 'Stesha', 'Benyan', 'F', '2005-07-01'),
    (3, '1990-12-14', 'Alf', 'McTiernan', 'M', '2005-08-01'),
    (4, '1985-05-30', 'Olympe', 'Nevill', 'F', '2005-09-01'),
    (5, '1999-02-09', 'Noemi', 'Silwood', 'F', '2005-10-01');

INSERT INTO departments (dept_no, dept_name)
VALUES
    ('d001', 'SYSTEM'),
    ('d002', 'WEB'),
    ('d003', 'MOBILE');

INSERT INTO dept_emp (emp_no, dept_no, from_date, to_date)
VALUES
    (1, 'd003', '2005-06-01', '9999-01-01'),
    (2, 'd001', '2005-07-01', '9999-01-01'),
    (3, 'd002', '2005-08-01', '9999-01-01'),
    (4, 'd002', '2005-09-01', '9999-01-01'),
    (5, 'd003', '2005-10-01', '9999-01-01');

INSERT INTO dept_manager (emp_no, dept_no, from_date, to_date)
VALUES
    (1, 'd003', '2005-06-01', '9999-01-01'),
    (2, 'd001', '2005-07-01', '9999-01-01'),
    (3, 'd002', '2005-08-01', '9999-01-01'),
    (4, 'd002', '2005-09-01', '9999-01-01'),
    (5, 'd003', '2005-10-01', '9999-01-01');

INSERT INTO salaries (emp_no, salary, from_date, to_date)
VALUES
    (1, 1800, '2005-06-01', '9999-01-01'),
    (2, 2000, '2005-07-01', '9999-01-01'),
    (3, 1300, '2005-08-01', '9999-01-01'),
    (4, 1000, '2005-09-01', '9999-01-01'),
    (5, 1500, '2005-10-01', '9999-01-01');

INSERT INTO titles (emp_no, title, from_date, to_date)
VALUES
    (1, 'Sales Manager', '2005-06-01', '9999-01-01'),
    (2, 'Sales Manager', '2005-07-01', '9999-01-01'),
    (3, 'Sales Manager', '2005-08-01', '9999-01-01'),
    (4, 'Sales Manager', '2005-09-01', '9999-01-01'),
    (5, 'Sales Manager', '2005-10-01', '9999-01-01');