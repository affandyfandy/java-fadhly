# Assignment 02 - Spring MVC

## Task 01: Create CRUD from Given CSV(with MVC)

### Select Dependencies

[Dependencies File](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/pom.xml)

1. `Spring Boot Starter JPA`
2. `MySQL Database`
3. `Thymeleaf`
4. `Spring Boot Starter Web`
5. `Lombok`
6. `OpenCSV`
7. `OpenHTMLtoPDF`

### Create Database

[Query Table](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/table.sql) Creating Table Employee

[Database Setting](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/resources/application.properties) Connect Database to Project

1. `Create Table Employee`

![Create Table](img/Create%20Table%20Employee%201.PNG)

![Create Table](img/Create%20Table%20Employee%202.PNG)

2. `Connect to Project`

![Connect Database](img/Connect%20Database.PNG)

### Create Model

[Model Employee](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/java/example/lecture9/assignment2/model/Employee.java) Create Model Employee using `Lombok` Dependency

### Create Repository

[Repository EmployeeRepository](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/java/example/lecture9/assignment2/repository/EmployeeRepository.java) Create Repository Interface for Employee using `JPA` Dependencies

### Create Service

[Service EmployeeService](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/java/example/lecture9/assignment2/service/EmployeeService.java) Create Service Interface for Service Implementation

[Service EmployeeServiceImpl](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/java/example/lecture9/assignment2/service/Impl/EmployeeServiceImpl.java) Create Service Implementation that Implement Method from EmployeeService and the Body Calling from Repository

### Create Controller

[Controller EmployeeController](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/java/example/lecture9/assignment2/controller/EmployeeController.java) Create Controller for Employee that Contain End-Point and RESTApi

### Create View

[View Index](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/resources/static/index.html) Create Index Static HTML for the Welcome Page of the Project

[View List Employee](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/resources/templates/employees/list-employees.html) Create View for Page that Contain All of the List Employee that Store in Database

[View Form Employee](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/resources/templates/employees/employee-form.html) Create View for Form Employee like Create New Employee or Edit Employee

[View Form Upload CSV](https://github.com/affandyfandy/java-fadhly/blob/Week_05/Week%2005/Lecture%2009/Assignment%2002/assignment2/src/main/resources/templates/employees/csv-form.html) Create View for Form Upload File CSV and Store it to Database

### Running Application

1. `Read All Employee`

![(GET) All Employee](img/All%20Employee%201.PNG)

![(GET) All Employee](img/All%20Employee%202.PNG)

![(GET) All Employee](img/All%20Employee%203.PNG)

2. `Create New Employee`

![(POST) Create New Employee](img/Create%20New%20Employee%201.PNG)

![(POST) Create New Employee](img/Create%20New%20Employee%202.PNG)

3. `Update Employee`

![(PUT) Edit Employee](img/Edit%20Employee%201.PNG)

![(PUT) Edit Employee](img/Edit%20Employee%202.PNG)

4. `Delete Employee`

![(DELETE) Employee](img/Delete%20Employee%201.PNG)

![(DELETE) Employee](img/Delete%20Employee%202.PNG)

## Task 02: Add button to Upload CSV File and Store to Database

### Upload CSV File and Store to Database

![Add Button](img/Delete%20Employee%202.PNG)

![Upload CSV File and Store to Database](img/Upload%20CSV%20File%201.PNG)

![Upload CSV File and Store to Database](img/Upload%20CSV%20File%202.PNG)