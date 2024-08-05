# Assignment 02 - Simple Interceptor

## Overall Project

This project like Assignment 02 - Lecture 15, but the difference is using `Interceptor` instead `Filter` to do some security phase in project.

### Dependencies

1. `Spring Web`: To build web, including RESTful using Apache Tomcat as default
2. `Spring Data JPA`: To persist data in SQL stores
3. `Spring Devtools`: To live reload or fast restart application
4. `MySQL Connector`: To connect MySQL database

### Aplication Properties

[Application Properties](assignment3\src\main\resources\application.properties) Use to handle connection with database and also can used to auto ddl table `update, create/delete` using annotation `@Entity` in target class

[DML](table.sql) Use to store `api_key`, `username`, `last_used` in the database named `testApiKey`, `fadhly`, and `Date now`

![DML Output](img/DML%20Output.PNG)

### Entity

1. `Product` 

[Product Entity](assignment2\src\main\java\com\example\lecture15\assignment2\entity\Product.java) Use to create entity name `Product` and defined for store table `product` in database

2. `ApiKey`

[ApiKey Entity](assignment2\src\main\java\com\example\lecture15\assignment2\entity\ApiKey.java) Use to create entity name `ApiKey` and defined for store table `api_key` in database

### Repository

1. `ProductRepository`

[Product Repository](assignment3\src\main\java\com\example\lecture15\assignment3\repository\ProductRepository.java) Use to handle persist entity data `Product` in project to table `product` in database, like `select`, `insert`, `update`, and `delete`

2. `ApiKeyRepository`

[ApiKey Repository](assignment3\src\main\java\com\example\lecture15\assignment3\repository\ApiKeyRepository.java) Use to handle persist entity data `ApiKey` in project to table `api_key` in database, like `select`, `insert`, `update`, and `delete`

### Service

1. `ProductService`

[Product Service](assignment3\src\main\java\com\example\lecture15\assignment3\service\ProductService.java) Create Service Interface for `Product Implementation`

2. `ProductServiceImplementation`

[Product Service Implemetation](assignment3\src\main\java\com\example\lecture15\assignment3\service\Impl\ProductServiceImpl.java) Create Product Implementation that Implement Method from `Product Service` and the Body Calling from Repository

3. `ApiKeyService`

[ApiKey Service](assignment3\src\main\java\com\example\lecture15\assignment3\service\ApiKeyService.java) Create Service Interface for `ApiKey Implementation`

4. `ApiKeyServiceImplementation`

[ApiKey Service Implemetation](assignment3\src\main\java\com\example\lecture15\assignment3\service\Impl\ApiKeyServiceImpl.java) Create ApiKey Implementation that Implement Method from `ApiKey Service` and the Body Calling from Repository

### Interceptor Configuration

1. `WebConfig`

[Web Configuration](assignment3\src\main\java\com\example\lecture15\assignment3\config\WebConfig.java) Use to setting `Interceptor` for `ApiKey` in this project

2. `ApiKeyInterceptor`

[Api Key Interceptor](assignment3\src\main\java\com\example\lecture15\assignment3\config\ApiKeyInterceptor.java) Use to implementation of using `Interceptor` for checking `ApiKey` that include in the request also check if the `ApiKey` exist in database or not before execute the request. This code also can add the header for the response

### Controller

1. `ProductController`

[Product Controller](assignment3\src\main\java\com\example\lecture15\assignment3\controller\ProductController.java) Use to define all the mapping for the web process for the `Product`

### End-Point

1. **GET** `All Data Product`

2. **GET** `Product By Id`

3. **POST** `Create New Product`

4. **PUT** `Edit Existing Product by Id`

5. **DELETE** `Delete Existing Product by Id`

## Task 01: Store username for Each API Key, add username (use-name) to Header and Print it in Function Controller

1. **GET** `All Data Product`

![Get All With Username](img/Get%20All%20Product%20With%20Username%201.PNG) Get All Data With the Right Username

![Get All Without Username](img/Get%20All%20Product%20With%20Username%201.PNG) Get All Data Without the Username

![Get All Wrong Username](img/Get%20All%20Product%20Wrong%20Username%201.PNG) Get All Data Wrong the Username

![Print Out Username](img/Get%20All%20Product%20With%20Username%203.PNG) Print out Username in terminal

2. **GET** `Product By Id`

![Get Product By Id With Username](img/Get%20Product%20By%20Id%20With%20Username%201.PNG) Get Product By Id With the Right Username

![Get Product By Id Without Username](img/Get%20Product%20By%20Id%20Without%20Username%201.PNG) Get Product By Id Without Username

![Get Product By Id Wrong Username](img/Get%20Product%20By%20Id%20Wrong%20Username%201.PNG) Get Product By Id Wrong Username

![Print Out Username](img/Get%20Product%20By%20Id%20With%20Username%203.PNG) Print Out Username in Terminal

3. **POST** `Create New Product`

![Create New Product With Username](img/Create%20Product%20With%20Username%201.PNG)

![Create New Product With Username](img/Create%20Product%20With%20Username%202.PNG)

![Create New Product Without Username](img/Create%20Product%20Without%20Username%201.PNG)

![Create New Product Without Username](img/Create%20Product%20Without%20Username%202.PNG)

![Create New Product Wrong Username](img/Create%20Product%20Wrong%20Username%202.PNG)

![Create New Product Wrong Username](img/Create%20Product%20Wrong%20Username%201.PNG)

![Print Out Username](img/Create%20Product%20With%20Username%203.PNG)

4. **PUT** `Edit Existing Product by Id`

![Update Existing Product With Username](img/Update%20Product%20With%20Username%201.PNG)

![Update Existing Product With Username](img/Update%20Product%20With%20Username%202.PNG)

![Update Existing Product Without Username](img/Update%20Product%20Without%20Username%201.PNG)

![Update Existing Product Without Username](img/Update%20Product%20Without%20Username%202.PNG)

![Update Existing Product Wrong Username](img/Update%20Product%20Wrong%20Username%201.PNG)

![Update Existing Product Wrong Username](img/Update%20Product%20Wrong%20Username%202.PNG)

![Print Out Username](img/Update%20Product%20With%20Username%203.PNG)

5. **DELETE** `Delete Existing Product by Id`

![Delete Existing Product With Username](img/Delete%20Product%20With%20Username%201.PNG)

![Delete Existing Product Without Username](img/Delete%20Product%20Without%20Username%201.PNG)

![Delete Existing Product Wrong Username](img/Delete%20Product%20Wrong%20username%201.PNG)

![Print Out Username](img/Delete%20Product%20With%20username%203.PNG)

## Task 02: Return All Response to Client Include Header "timestamp" : "current-time"

1. **GET** `All Data Product`

![Get All With Username](img/Get%20All%20Product%20With%20Username%202.PNG)

![Get All Without Username](img/Get%20All%20Product%20Without%20Username%202.PNG)

![Get All Wrong Username](img/Get%20All%20Product%20Wrong%20Username%202.PNG)

2. **GET** `Product By Id`

![Get Product By Id With Username](img/Get%20Product%20By%20Id%20With%20Username%202.PNG)

![Get Product By Id Without Username](img/Get%20Product%20By%20Id%20Without%20Username%202.PNG)

![Get Product By Id Wrong Username](img/Get%20Product%20By%20Id%20Wrong%20Username%202.PNG)

3. **POST** `Create New Product`

![Create New Product With Username](img/Create%20Product%20With%20Username%202.PNG)

![Create New Product Without Username](img/Create%20Product%20Without%20Username%202.PNG)

![Create New Product Wrong Username](img/Create%20Product%20Wrong%20Username%201.PNG)

4. **PUT** `Edit Existing Product by Id`

![Edit Existing Product With Username](img/Update%20Product%20With%20Username%202.PNG)

![Edit Existing Product Without Username](img/Update%20Product%20Without%20Username%202.PNG)

![Edit Existing Product Wrong Username](img/Update%20Product%20Wrong%20Username%202.PNG)

5. **DELETE** `Delete Existing Product by Id`

![Delete Existing Product With Username](img/Delete%20Product%20With%20username%202.PNG)

![Delete Existing Product Without Username](img/Delete%20Product%20Without%20Username%202.PNG)

![Delete Existing Product Wrong Username](img/Delete%20Product%20Wrong%20username%202.PNG)

## Task 03: Store Last Time API Key used

**Before**
![Success Called](img/After%20Success%20Request.PNG)

**After**
![Success Called](img/Before%20Success%20Request.PNG)