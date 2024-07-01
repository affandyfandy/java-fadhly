# Assignment 02

In Assignmnet 02 from Lecture 06, I should create a database with tables and create tho mock data for each tables

## Initialize Database

Created Database Name `store_table` in MySQL Database using XAMPP as a server

![Create Database Store](img/Create%20Database%20strore_table.PNG)

### Table Customer

Created Table Name `customer`, with 3 column `id`, `name`, `phone`

![Create Table Customer](img/Create%20Table%20Customer%201.PNG)

1. `id` type is INT with Auto_Increament, Not Null, and Primary Key of Table Customer
2. `name` type is VARCHAR with length 100 and Not Null
3. `phone` type is VARCHAR with length 100 and Not Null

![Create Table Customer](img/Create%20Table%20Customer%202.PNG)

![Create Table Customer](img/Create%20Table%20Customer%203.PNG)

![Create Table Customer](img/Create%20Table%20Customer%204.PNG)

### Table Cashier

Created Table Name `cashier`, with 2 column `id`, `name`

![Create Table Cashier](img/Create%20Table%20Cashier%201.PNG)

1. `id` type is INT with Auto_Increament, Not Null, and Primary Key of Table Cashier
2. `name` type is VARCHAR with length 100 and Not Null

![Create Table Cashier](img/Create%20Table%20Cashier%202.PNG)

![Create Table Cashier](img/Create%20Table%20Cashier%203.PNG)

![Create Table Cashier](img/Create%20Table%20Cashier%204.PNG)

### Table Invoice

Created Table Name `invoice`, with 5 column `id`, `customer_id`, `cashier_id`, `amount`, `created_date`

![Create Table Invoice](img/Create%20Table%20Invoice%201.PNG)

1. `id` type is INT with Auto_Increament, Not Null, and Primary Key of Table Invoice
2. `customer_id` type is INT Not Null, and Foreign Key from table `customer`
3. `cashier_id` type is INT Not Null, and Foreign Key from table `cashier`
4. `amount` type is INT Not Null
5. `created_date` type is date with format __yyyy-mm-dd__

![Create Table Invoice](img/Create%20Table%20Invoice%202.PNG)

![Create Table Invoice](img/Create%20Table%20Invoice%203.PNG)

![Create Table Invoice](img/Create%20Table%20Invoice%204.PNG)

### Table Product

Created Table Name `product`, with 3 column `id`, `name`, `price`

![Create Table Product](img/Create%20Table%20Product%201.PNG)

1. `id` type is INT with Auto_Increament, Not Null, and Primary Key of Table Cashier
2. `name` type is VARCHAR with length 100 and Not Null
3. `price` type is INT Not Null

![Create Table Product](img/Create%20Table%20Product%202.PNG)

![Create Table Product](img/Create%20Table%20Product%203.PNG)

![Create Table Product](img/Create%20Table%20Product%204.PNG)

### Table Invoice Detail

Created Table Name `invoice_detail`, with 6 column `id`, `quantity`, `product_id`, `product_price`, `invoice_id`, `amount`

![Create Table Product](img/Create%20Table%20Invoice%20Detail%201.PNG)

1. `id` type is INT with Auto_Increament, Not Null, and Primary Key of Table Invoice
2. `quantity` is INT Not Null
3. `product_id` type is INT Not Null, and Foreign Key from table `product`
4. `product_price` type is INT Not Null
5. `invoice_id` type is INT Not Null, and Foreign Key from table `invoice`
6. `amount` type is INT Not Null

![Create Table Product](img/Create%20Table%20Invoice%20Detail%202.PNG)

![Create Table Product](img/Create%20Table%20Invoice%20Detail%203.PNG)

![Create Table Product](img/Create%20Table%20Invoice%20Detail%204.PNG)

## Design Database

After initialize database, then insert data into tables

### Mock Data Table Customer

Insert into table `customer` with SQL query in the same database

![Mock Data Table Customer](img/Mock%20Data%20Customer%201.PNG)

![Mock Data Table Customer](img/Mock%20Data%20Customer%202.PNG)

![Mock Data Table Customer](img/Mock%20Data%20Customer%203.PNG)

![Mock Data Table Customer](img/Mock%20Data%20Customer%204.PNG)

### Mock Data Table Cashier

Insert into table `cashier` with SQL query in the same database

![Mock Data Table Cashier](img/Mock%20Data%20Cashier%201.PNG)

![Mock Data Table Cashier](img/Mock%20Data%20Cashier%202.PNG)

![Mock Data Table Cashier](img/Mock%20Data%20Cashier%203.PNG)

![Mock Data Table Cashier](img/Mock%20Data%20Cashier%204.PNG)

### Mock Data Table Invoice

Insert into table `invoice` with SQL query in the same database

![Mock Data Table Invoice](img/Mock%20Data%20Invoice%201.PNG)

![Mock Data Table Invoice](img/Mock%20Data%20Invoice%202.PNG)

![Mock Data Table Invoice](img/Mock%20Data%20Invoice%203.PNG)

![Mock Data Table Invoice](img/Mock%20Data%20Invoice%204.PNG)

Coloumn `amount` from `invoice` table is reference by sum of coloumn `amount` from table `invoice_detail`

### Mock Data Table Product

Insert into table `product` with SQL query in the same database

![Mock Data Table Product](img/Mock%20Data%20Product%201.PNG)

![Mock Data Table Product](img/Mock%20Data%20Product%202.PNG)

![Mock Data Table Product](img/Mock%20Data%20Product%203.PNG)

![Mock Data Table Product](img/Mock%20Data%20Product%204.PNG)

### Mock Data Table Invoice Detail

Insert into table `invoice_detail` with SQL query in the same database

![Mock Data Table Invoice Detail](img/Mock%20Data%20Invoice%20Detail%201.PNG)

![Mock Data Table Invoice Detail](img/Mock%20Data%20Invoice%20Detail%202.PNG)

![Mock Data Table Invoice Detail](img/Mock%20Data%20Invoice%20Detail%203.PNG)

![Mock Data Table Invoice Detail](img/Mock%20Data%20Invoice%20Detail%204.PNG)

Coloumn `amount` from `invoice_detail` table is reference by column `quantity` multiplied by column `product_price` form `product` table