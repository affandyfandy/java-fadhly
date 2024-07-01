CREATE VIEW list_product_consumer AS
SELECT
    c.id AS customer_id,
    c.name AS customer_name,
    p.id AS product_id,
    p.name AS product_name,
    idt.quantity,
    idt.amount,
    i.created_date
FROM customer c
JOIN invoice i ON c.id = i.customer_id
JOIN invoice_detail idt ON i.id = idt.invoice_id
JOIN product p ON idt.product_id = p.id;

DELIMITER //

CREATE FUNCTION calculate_revenue_cashier(cashID INT) 
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total_revenue INT;
    SELECT SUM(amount) INTO total_revenue
    FROM invoice
    WHERE cashier_id = cashID;
    RETURN IFNULL(total_revenue, 0);
END //

DELIMITER ;

SELECT calculate_revenue_cashier(2) AS revenue;

CREATE TABLE revenue_report (
    `id` INT NOT NULL AUTO_INCREMENT,
    `year` INT NOT NULL,
    `month` INT NOT NULL,
    `day` INT NOT NULL,
    `amount` INT NOT NULL,
PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DELIMITER //
CREATE PROCEDURE calculate_daily_revenue(IN input_date DATE)
BEGIN
    DECLARE total_revenue INT;
    SELECT SUM(amount) INTO total_revenue
    FROM invoice
    WHERE created_date = input_date;
    IF total_revenue IS NULL THEN
        SET total_revenue = 0;
    END IF;
    INSERT INTO revenue_report (`year`, `month`, `day`, `amount`)
    VALUES (YEAR(input_date), MONTH(input_date), DAY(input_date), total_revenue);
END //
DELIMITER ;

CALL calculate_daily_revenue('2024-06-02');

DELIMITER //
CREATE PROCEDURE calculate_monthly_revenue(IN input_date DATE)
BEGIN
    DECLARE total_revenue INT;
    SELECT SUM(amount) INTO total_revenue
    FROM invoice
    WHERE YEAR(created_date) = YEAR(input_date) AND MONTH(created_date) = MONTH(input_date);
    IF total_revenue IS NULL THEN
        SET total_revenue = 0;
    END IF;
    INSERT INTO revenue_report (`year`, `month`, `day`, `amount`)
    VALUES (YEAR(input_date), MONTH(input_date), 0, total_revenue);
END //
DELIMITER ;

CALL calculate_monthly_revenue('2024-06-02');

DELIMITER //
CREATE PROCEDURE calculate_yearly_revenue(IN input_date DATE)
BEGIN
    DECLARE total_revenue INT;
    SELECT SUM(amount) INTO total_revenue
    FROM invoice
    WHERE YEAR(created_date) = YEAR(input_date);
    IF total_revenue IS NULL THEN
        SET total_revenue = 0;
    END IF;
    INSERT INTO revenue_report (`year`, `month`, `day`, `amount`)
    VALUES (YEAR(input_date), 0, 0, total_revenue);
END //
DELIMITER ;

CALL calculate_yearly_revenue('2024-06-02');