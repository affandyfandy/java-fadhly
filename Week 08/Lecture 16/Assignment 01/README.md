# Assignment 01 - Microservice

In this project, there were 2 projects that will implements how microservice works.
Project 1 that will request from project 2, project 2 will response the needs from project 1.

## Project 1

### Dependencies

1. `Spring Web`
2. `Spring Cloud OpenFeign`
3. `Spring JPA`
4. `Spring Devtools`
5. `MySQL Connector`
6. `Lombok`
7. `Map & Struct`

### Application Properties

[Application Properties](assignment1_1\src\main\resources\application.properties) Use to handle connection with database and also can used to auto ddl table `update, create-delete` using annotation `@Entity` in target class

### Entity

1. `Post`

[Post Entity](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\entity\Post.java) Use to create entity name `Post` and defined for store table `post` in database

### Repository

1. `PostRepository`

[Post Repository](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\repository\PostRepository.java) Use to handle persist entity data `Post` in project to table `post` in database, like `select`, `insert`, `update`, and `delete`

### Client

1. `CommentClient`

[Comment Client](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\client\CommentClient.java) Use to handle retrieve data `Comment` by Id from project 2 using `FeignClient`

2. `JsonPlaceholderClient`

[Json Placeholder Client](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\client\JsonPlaceholderClient.java) Use to handle retrieve data from other resources using `FeignClient`

### Config

1. `AppConfig`

[App Config](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\config\AppConfig.java) Use to handle configuration about `Rest-Template` and `WebClient`

### DTO

1. `CommentShowDTO`

[Comment Show DTO](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\dto\CommentShowDTO.java) Use to change the response of `Comment` entity

2. `PostShowDTO`

[Post Show DTO](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\dto\PostShowDTO.java) Use to change the response of `Post` entity

### Mapper

1. `PostMapper`

[Post Mapper](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\mapper\PostMapper.java)

### Service

1. `PostService`

[Post Service](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\PostService.java) Create Service Interface for `Post Implementation`

2. `PostServiceImplementation`

[Post Service Implemetation](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\Impl\PostServiceImpl.java) Create Post Implementation that Implement Method from `Post Service` and the Body Calling from Repository

3. `CommentService`

[Comment Service](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\CommentService.java) Create Service Interface for `Comment Implementation`

4. `CommentServiceImplementation`

[Comment Service Implemetation](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\Impl\CommentServiceImpl.java) Create Comment Implementation that Implement Method from `Comment Service` and the Body Calling from Repository

### Controller

1. `PostController`

[Post Controller](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\controller\PostController.java) Use to define all the mapping for the web process for the `Post`

### End-Point

1. **GET** `All Data Post with Comment using FeignClient`

2. **GET** `All Data Post with Comment using Rest-Template`

3. **GET** `All Data Post with Comment using WebClient`

4. **GET** `Comment by Id`

5. **POST** `Create New Post`

## Project 2

### Dependencies

1. `Spring Web`
2. `Spring Cloud OpenFeign`
3. `Spring JPA`
4. `Spring Devtools`
5. `MySQL Connector`
6. `Lombok`
7. `Map & Struct`

### Application Properties

[Application Properties](assignment1_3\src\main\resources\application.properties) Use to handle connection with database and also can used to auto ddl table `update, create-delete` using annotation `@Entity` in target class. Using different default PORT `8081`

### Entity

1. `Comment`

[Comment Entity](assignment1_3\src\main\java\com\lecture16\assignment1_3\entity\Comment.java) Use to create entity name `Comment` and defined for store table `comment` in database

### Repository

1. `CommentRepository`

[Comment Repository](assignment1_3\src\main\java\com\lecture16\assignment1_3\repository\CommentRepository.java) Use to handle persist entity data `Comment` in project to table `comment` in database, like `select`, `insert`, `update`, and `delete`

### Service

1. `CommentService`

[Comment Service](assignment1_3\src\main\java\com\lecture16\assignment1_3\service\CommentService.java) Create Service Interface for `Comment Implementation`

2. `CommentServiceImplementation`

[Comment Service Implemetation](assignment1_3\src\main\java\com\lecture16\assignment1_3\service\Impl\CommentServiceImpl.java) Create Comment Implementation that Implement Method from `Comment Service` and the Body Calling from Repository

### Controller

1. `CommentController`

[Comment Controller](assignment1_3\src\main\java\com\lecture16\assignment1_3\controller\CommentController.java) Use to define all the mapping for the web process for the `Comment`

### End-Point

1. **GET** `Comment by Id`

## Task 01: Implement All Examples

### Feign-Client Demo

**Build by**

1. [Main Class Annotation](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\Assignment11Application.java)

2. [Comment Client](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\client\CommentClient.java)

3. [Post Mapper](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\mapper\PostMapper.java)

4. [Post Service](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\PostService.java)

5. [Post Service Implementation](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\Impl\PostServiceImpl.java)

![Get All Data using FeignClient](img/Get%20All%20Posts%20With%20Comment%20using%20FeignClient.PNG)

### Rest-Template Demo

**Build by**

1. [App Config](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\config\AppConfig.java)

2. [Comment Service](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\CommentService.java)

3. [Comment Service Implementation](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\Impl\CommentServiceImpl.java)

4. [Post Mapper](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\mapper\PostMapper.java)

5. [Post Service](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\PostService.java)

6. [Post Service Implementation](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\Impl\PostServiceImpl.java)

![Get All Data using Rest-Template](img/Get%20All%20Posts%20With%20Comment%20using%20Rest-Template.PNG)

### Web-Client Demo

**Build by**

1. [App Config](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\config\AppConfig.java)

2. [Comment Service](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\CommentService.java)

3. [Comment Service Implementation](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\Impl\CommentServiceImpl.java)

4. [Post Mapper](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\mapper\PostMapper.java)

5. [Post Service](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\PostService.java)

6. [Post Service Implementation](assignment1_1\src\main\java\com\example\lecture16\assignment1_1\service\Impl\PostServiceImpl.java)

![Get All Data using WebClient](img/Get%20All%20Posts%20With%20Comment%20using%20WebClient.PNG)