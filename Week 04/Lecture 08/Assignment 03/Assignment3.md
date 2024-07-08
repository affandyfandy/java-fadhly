# Assignment 03 - Lecture 08

## Task 01: Change Datasource to Bean

when using using multiple datasource, using bean is a useful approach.

### Setting Dependencies

1. `Spring Boot Starter Web`
2. `Spring Data JDBC`
3. `MySQL Database`
4. `Spring Boot DevTools`
5. `Lombok`

![Setting Dependencies](img/Other%20Dependencies%201.PNG)

![Setting Dependencies](img/Other%20Dependencies%202.PNG)

### Create Database

1. `Table for Database 1`

![Create Table Database 1](img/Table%20for%20Database1%201.PNG)

![Create Table Database 1](img/Table%20for%20Database1%202.PNG)

![Create Table Database 1](img/Table%20for%20Database1%203.PNG)

2. `Table for Database 2`

![Create Table Database 2](img/Table%20for%20Database2%201.PNG)

![Create Table Database 2](img/Table%20for%20Database2%202.PNG)

![Create Table Database 2](img/Table%20for%20Database2%203.PNG)

### Connect Database

Connect to database using Bean Annotation, without application properties. Also define `Transactional` and `JDBCTemplate` for each database.

![Connect Database](img/Connect%20Database%201.PNG)

![Connect Database](img/Connect%20Database%201.PNG)

### Create Model Employee

Model creted using `Lombok` Dependency.

![Create Model](img/Create%20Model%20Employee.PNG)

### Create RepositoryEmployee

1. `Repository Database 1`

![Create Repository Database 1](img/Create%20Repository%20Database1%201.PNG)

![Create Repository Database 1](img/Create%20Repository%20Database1%202.PNG)

2. `Repository Database 2`

![Create Repository Database 2](img/Create%20Repository%20Database2%201.PNG)

![Create Repository Database 2](img/Create%20Repository%20Database2%202.PNG)

### Create ServiceEmployee

ServiceEmployee will handle from each database, be different from the annotation using `Transactional`.

![Create Service Employee](img/Create%20Service%201.PNG)

![Create Service Employee](img/Create%20Service%202.PNG)

![Create Service Employee](img/Create%20Service%203.PNG)

![Create Service Employee](img/Create%20Service%204.PNG)

![Create Service Employee](img/Create%20Service%205.PNG)

### Create Controller

Controller will handle all API from each database, different from the endpoint.

![Create Controller Employee](img/Create%20Controller%201.PNG)

![Create Controller Employee](img/Create%20Controller%202.PNG)

![Create Controller Employee](img/Create%20Controller%203.PNG)

### Main Application and Running Application

Main appication additional `exclude = { DataSourceAutoConfiguration.class }` from giving access to read other database.

![Main Application](img/Main%20Application.PNG)

1. `GET All`

`Database 1`

![Test Get All](img/Test%20API%20GET%20All%20Employee%20Database1%201.PNG)

![Test Get All](img/Test%20API%20GET%20All%20Employee%20Database1%202.PNG)

`Database 2`

![Test Get All](img/Test%20API%20GET%20All%20Employee%20Database2%201.PNG)

![Test Get All](img/Test%20API%20GET%20All%20Employee%20Database2%202.PNG)

2. `GET by Param`

`Database 1`

![Test Get by Param](img/Test%20API%20GET%20Find%20Employee%20by%20ID%20Database1%201.PNG)

![Test Get by Param](img/Test%20API%20GET%20Find%20Employee%20by%20ID%20Database1%202.PNG)

`Database 2`

![Test Get by Param](img/Test%20API%20GET%20Find%20Employee%20by%20ID%20Database2%201.PNG)

![Test Get by Param](img/Test%20API%20GET%20Find%20Employee%20by%20ID%20Database2%202.PNG)

3. `POST`

`Database 1`

![Test Post](img/Test%20API%20Post%20Create%20Employee%20Database1%201.PNG)

![Test Post](img/Test%20API%20Post%20Create%20Employee%20Database1%202.PNG)

`Database 2`

![Test Post](img/Test%20API%20Post%20Create%20Employee%20Database2%201.PNG)

![Test Post](img/Test%20API%20Post%20Create%20Employee%20Database2%202.PNG)

4. `PUT`

`Database 1`

![Test PUT](img/Test%20API%20PUT%20Edit%20Employee%20Database1%201.PNG)

![Test PUT](img/Test%20API%20PUT%20Edit%20Employee%20Database1%202.PNG)

`Database 2`

![Test PUT](img/Test%20API%20PUT%20Edit%20Employee%20Database2%201.PNG)

![Test PUT](img/Test%20API%20PUT%20Edit%20Employee%20Database2%202.PNG)

5. `DELETE`

`Database 1`

![Test Delete](img/Test%20API%20DELETE%20Delete%20Employee%20Database1%201.PNG)

![Test Delete](img/Test%20API%20DELETE%20Delete%20Employee%20Database1%202.PNG)

`Database 2`

![Test Delete](img/Test%20API%20DELETE%20Delete%20Employee%20Database2%201.PNG)

![Test Delete](img/Test%20API%20DELETE%20Delete%20Employee%20Database2%202.PNG)

## Task 02: Handle Transaction (Insert/Update) Data

Handle when Insert and Upadate data using `Transactional` Annotation. `Transcational` created using transactionalManager that define using `Bean` and declare in config file.

1. Create Transactional Manager First for Two Different Database

![Create Transactional](img/Connect%20Database%202.PNG)

2. Declare `Transactional` Annotation in Service Employeee

![Transactional in Service](img/Create%20Service%202.PNG)

![Transactional in Service](img/Create%20Service%203.PNG)

![Transactional in Service](img/Create%20Service%204.PNG)

![Transactional in Service](img/Create%20Service%205.PNG)

It will handle when the API calling for POST/PUT/DELETE.

## Task 03: Research Lombok and Create Project using It

`Lombok` used for making code more concise and readable. Like it will generate automatic common methods like `getters`, `setters`, `toString`, `equals`, `hashCode`, and `constructors` during the compile time.

### Types of Lombok

1. `@Getter`, `@Setter` For Setter Getter
2. `@ToString`, `@EqualsAndHashCode` For toString, Equal, and Hascode Method
3. `@NoArgsConstructor`, `@AllArgsConstructo`r, `@RequiredArgsConstructor` for generate Constrctor with no argument
4. `@Data` bundle for Setter Getter, toString, Equal, Hascode Method, and Constructor

### Project Before using Lombok

1. `Declare Lombok`

![Declare Lombok in Model Employee](img/Create%20Model%20Employee.PNG)

2. `Calling Lombok Setter`

![Calling Lombok Setter](img/Create%20Repository%20Database1%201.PNG)

![Calling Lombok Setter](img/Create%20Repository%20Database2%201.PNG)

3. `Calling Lombok Getter`

![Calling Lombok Getter](img/Create%20Service%202.PNG)

![Calling Lombok Getter](img/Create%20Service%203.PNG)

![Calling Lombok Getter](img/Create%20Service%204.PNG)

![Calling Lombok Getter](img/Create%20Service%205.PNG)