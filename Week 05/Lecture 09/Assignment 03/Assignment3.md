# Assignment 03 - Spring MVC

## Task 01: Gen HTML Template and CSV File

### Select Dependencies

[Dependencies File](assignment3\pom.xml)

1. `Spring Boot Starter JPA`
2. `MySQL Database`
3. `Thymeleaf`
4. `Spring Boot Starter Web`
5. `Lombok`
6. `OpenCSV`
7. `OpenHTMLtoPDF`

### Create Database

[Query Create Table](table.sql) For create Table Employee

[Properties File in Project](assignment3\src\main\resources\application.properties) For connect database to project using MySQL

1. `Create Table Employee`

![Create Table Employee](img/Create%20Table%201.PNG)

![Create Table Employee](img/Create%20Table%202.PNG)

2. `Connect Database`

![Properties File in Project](img/Connect%20Database.PNG)

### Create Model

[Model Employee](assignment3\src\main\java\example\lecture9\assignment3\model\Employee.java) Create Model Employee using `Lombok` Dependency

### Create Repository

[Repository EmployeeRepository](assignment3\src\main\java\example\lecture9\assignment3\repository\EmployeeRepository.java) Create Repository Interface for Employee using `JPA` Dependencies

### Create Service

[Service EmployeeService](assignment3\src\main\java\example\lecture9\assignment3\service\EmployeeService.java) Create Service Interface for Service Implementation and Handling Open CSV File, Generate HTML File to PDF

[Service EmployeeServiceImpl](assignment3\src\main\java\example\lecture9\assignment3\service\impl\EmployeeServiceImpl.java) Create Service Implementation that Implement Method from EmployeeService and the Body Calling from Repository

### Create Controller

[Controller EmployeeController](assignment3\src\main\java\example\lecture9\assignment3\controller\EmployeeController.java) Create Controller for Employee that Contain End-Point and RESTApi for Simple CRUD, Read CSV File, and Generate HTML File to PDF

### Create View

[View Index](assignment3\src\main\resources\static\index.html) Create Index Static HTML for the Welcome Page of the Project

[View List Employee](assignment3\src\main\resources\templates\employees\list-employees.html) Create View for Page that Contain All of the List Employee that Store in Database

[View Form Employee](assignment3\src\main\resources\templates\employees\employee-form.html) Create View for Form Employee like Create New Employee or Edit Employee

[View Form Upload CSV](assignment3\src\main\resources\templates\employees\csv-form.html) Create View for Form Upload File CSV and Store it to Database

[View Report Employee](assignment3\src\main\resources\templates\employees\pdf-template.html) Create View for Generate HTML Report Employee to PDF File

### Running Application

![Add Button to Generate PDF](img/Generate%20PDF%201.PNG)

![Add Button to Generate PDF](img/Generate%20PDF%202.PNG)

## Task 02: Calculate and Bind Data from CSV File

### Additional Service Implementation

1. `Find Max Salary Employee`

![Find Max Salary Employee](img/Find%20Max%20Salary.PNG)

2. `Find Min Salary Employee`

![Find Min Salary Employee](img/Find%20Min%20Salary.PNG)

3. `Calculate Average Salary All Employee`

![Find Average Salary Employee](img/Find%20Avg%20Salary.PNG)

4. `Total Record Employee`

![Find Total Record Employee](img/Find%20Total%20Records%20Employee.PNG)

5. `Generate HTML to PDF`

![Generate HTML to PDF](img/Generate%20HTML%20to%20PDF.PNG)