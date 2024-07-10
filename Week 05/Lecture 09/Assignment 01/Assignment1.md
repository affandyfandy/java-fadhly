# Assignment 01 - Spring MVC

## Task 01: Recreate from Example

### Requirement Dependencies

[Dependencies File](assignment1\pom.xml)

1. `Spring Boot Starter JPA`
2. `MySQL Database`
3. `Thymeleaf`
4. `Spring Boot Starter Web`
5. `Lombok`

### Database Setting

[Query Table](table.sql) Creating Table Employee

[Database Setting](assignment1\src\main\resources\application.properties) Connect Database to Project

1. `Create Table Employee`

![Create Table](img/Create%20Table%20Employee%201.PNG)

![Create Table](img/Create%20Table%20Employee%202.PNG)

2. `Connect to Project`

![Connect Database](img/Connect%20Database.PNG)

### Create Model Employee

[Model Employee](assignment1\src\main\java\example\lecture9\assignment1\model\Employee.java) Create Model Employee using `Lombok` Dependency

### Create Repository JPA EmployeeRepository

[Repository EmployeeRepository](assignment1\src\main\java\example\lecture9\assignment1\repository\EmployeeRepository.java) Create Repository Interface for Employee using `JPA` Dependencies

### Create Service EmployeeService and Implementation

[Service EmployeeService](assignment1\src\main\java\example\lecture9\assignment1\service\EmployeeService.java) Create Service Interface for Service Implementation 

[Service EmployeeServiceImpl](assignment1\src\main\java\example\lecture9\assignment1\service\impl\EmployeeServiceImpl.java) Create Service Implementation that Implement Method from EmployeeService and the Body Calling from Repository

### Create Controller EmployeeController

[Controller EmployeeController](assignment1\src\main\java\example\lecture9\assignment1\controller\EmployeeController.java) Create Controller for Employee that Contain End-Point and RESTApi

### Create View using HTML

[View Index](assignment1\src\main\resources\static\index.html) Create Index Static HTML for the Welcome Page of the Project

[View List Employee](assignment1\src\main\resources\templates\employees\list-employees.html) Create View for Page that Contain All of the List Employee that Store in Database

[View Form Employee](assignment1\src\main\resources\templates\employees\employee-form.html) Create View for Form Employee like Create New Employee or Edit Employee

### Running Application

Running in Local Host: `localhost:8080`

1. `Welcome Page`

![Welcome Page](img/Welcome%20Page.PNG)

2. `Create New Employee`

![Create New Employee](img/Add%20New%20Employee%201.PNG)

![Create New Employee](img/Add%20New%20Employee%202.PNG)

3. `Edit Employee`

![Edit Employee](img/Update%20Employee%201.PNG)

![Edit Employee](img/Update%20Employee%202.PNG)

4. `Delete Employee`

![Delete Employee](img/Delete%20Employee%201.PNG)

![Delete Employee](img/Delete%20Employee%202.PNG)