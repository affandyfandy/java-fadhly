# Assignment 01 - Lecture 10

## Task 01: Similar to lecture5 - assignment2

This task based on implementation Assignmetn 02 - Lecture 05 [Assignment 02-Lecture 05](https://github.com/affandyfandy/java-fadhly/tree/Week_03/Week%2003/Lecture%2005/Assignment%2002/assignment2)

With additional Dependencies

[Dependencies](assignment1\pom.xml)

1. `Starter Web Spring Boot`
2. `JPA Repository`
3. `Spring Boot DevTools`
4. `MySQL`
5. `Lombok`
6. `OpenCSV`
7. `Map Struct`
8. `Validation`

## Task 02: Add Field: email, phone

Field Updated
1. `id` as binary(16)
2. `email` as varchar(256)
3. `phone` as varchar(100)

[Query Table](table.sql)

## Task 03: Project Structure Follow Standard

### Config Folder

This folder inlcude all configuration for this project

[Mapper Configuration](assignment1\src\main\java\example\lecture10\assignment1\config\MapConfig.java) This configuration used to declare Mapper in this project, so the mapper interface can be read by Spring Boot

### Controller Folder

This folder will handle all of the RESTApi

[Employee Controller](assignment1\src\main\java\example\lecture10\assignment1\controller\EmployeeController.java) This controller will handle RESTApi for the Employee

### DTO Folder

This folder used for all transfer data that have different data structure

[Employee DTO](assignment1\src\main\java\example\lecture10\assignment1\dto\EmployeeDTO.java) This DTO for Employee Model

### Mapper Folder

This folder include all file that convert data from DTO before

[Employee Mapper](assignment1\src\main\java\example\lecture10\assignment1\mapper\EmployeeMapper.java) This Mapper used for convert Employee Model to Employee DTO and Employee DTO to Employee Model

### Model Folder

This folder include all file that store table from database

[Employee Model](assignment1\src\main\java\example\lecture10\assignment1\model\Employee.java) This Model will store data from table name `employee`

### Repository Folder

This folder include all resource to reach database like select, insert or alter the table

[Employee Repository](assignment1\src\main\java\example\lecture10\assignment1\repository\EmployeeRepository.java) This repository will access database table name `employee`

### Service Folder

This folder will include all the file that connect controller and repository

[Employee Service](assignment1\src\main\java\example\lecture10\assignment1\service\EmployeeService.java) This file will create Service Interface for Service Implementation

[Employee Service Implementation](assignment1\src\main\java\example\lecture10\assignment1\service\Impl\EmployeeServiceImpl.java) This fle will create Service Implementation that Implement Method from EmployeeService and the Body Calling from Repository

### Util Folder

This folder will contain all the helper that provides common functionalities

[File Util](assignment1\src\main\java\example\lecture10\assignment1\util\FileUtil.java) This file will handle of CSV File upload and store to database

[UUID Deserializer](assignment1\src\main\java\example\lecture10\assignment1\util\UUIDDeserializer.java) This file will handle of input `id` if the input is not in UUID format

## Task 04: Use id by UUID

In Employee Model and Employee DTO, `id` defined by UUID

[Employee Model](assignment1\src\main\java\example\lecture10\assignment1\model\Employee.java) and [Employee DTO](assignment1\src\main\java\example\lecture10\assignment1\dto\EmployeeDTO.java)

To handle the input of UUID, the column of the table also changed to binary(16)

![Query Table Employee](img/Create%20Table%201.PNG)

![Table Employee](img/Create%20Table%202.PNG)

## Task 05: Use DTO and MapStruct

Use DTO for Employee model and convert it with Map Struct

[Employee Mapper](assignment1\src\main\java\example\lecture10\assignment1\mapper\EmployeeMapper.java) and [Employee DTO](assignment1\src\main\java\example\lecture10\assignment1\dto\EmployeeDTO.java)

## Task 06: Handle Exception

![Handle for Get All Employee](img/Handle%20Exception%201.PNG)

![Handle for Get Employee by ID](img/Handle%20Exception%202.PNG)

![Handle for Get Employee by Department](img/Handle%20Exception%203.PNG)

![Handle for Update Employee](img/Handle%20Exception%204.PNG)

![Handle for Create Employee](img/Handle%20Exception%205.PNG)

![Handle for Create Employee by CSV File](img/Handle%20Exception%206.PNG)

![Handle for Delete Employee](img/Handle%20Exception%207.PNG)

## Task 07: Validate email, name (notnull, only text), phone as your country format phone

All the validation declare in DTO Employee Class using annotation from `Validation` Dependency

[Employee DTO](assignment1\src\main\java\example\lecture10\assignment1\dto\EmployeeDTO.java)

## Task 08: Running Application

1. `Create Employee`

![Create Employee](img/Create%20Employee.PNG)

with Format [`address`, `dateOfBirth`, `department`, `email`, `name`, `phone`, `id`]

2. `Get Employee by Id`

![Get Employee by Id](img/Get%20Employee%20by%20Id.PNG)

3. `Create Emmployee by CSV File`

Sample Data can be access by [sample-data](sampledata.csv)

![Create Emmployee by CSV File](img/Create%20Employee%20by%20CSV%20File.PNG)

4. `Get Employee by Department`

![Get Employee by Department](img/Get%20Employee%20by%20Department.PNG)

5. `Update Employee`

![Update Employee](img/Upadate%20Employee.PNG)

6. `Delete Employee`

![Delete Employee](img/Delete%20Employee%201.PNG)

![Delete Employee](img/Delete%20Employee%202.PNG)

7. `Get All Employee`

![Get All Employee](img/Get%20All%20Employee%201.PNG)

![Get All Employee](img/Get%20All%20Employee%202.PNG)