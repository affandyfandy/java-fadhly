# Assignment 02 - Simple Filter

## Dependencies

1. `Spring Web`: To build web, including RESTful using Apache Tomcat as default
2. `Spring Data JPA`: To persist data in SQL stores
3. `Spring Devtools`: To live reload or fast restart application
4. `MySQL Connector`: To connect MySQL database

## Task 01: Create Simple CRUD

### Application Properties

[Application Properties](assignment2\src\main\resources\application.properties) Use to handle connection with database and also can used to auto ddl table `update, create/delete` using annotation `@Entity` in target class

### Entity

1. `Product` 

[Product Entity](assignment2\src\main\java\com\example\lecture15\assignment2\entity\Product.java) Use to create entity name `Product` and defined for store table `product` in database

2. `ApiKey`

[ApiKey Entity](assignment2\src\main\java\com\example\lecture15\assignment2\entity\ApiKey.java) Use to create entity name `ApiKey` and defined for store table `api_key` in database

### Repository

1. `ProductRepository`

[Product Repository](assignment2\src\main\java\com\example\lecture15\assignment2\repository\ProductRepository.java) Use to handle persist entity data `Product` in project to table `product` in database, like `select`, `insert`, `update`, and `delete`

2. `ApiKeyRepository`

[ApiKey Repository](assignment2\src\main\java\com\example\lecture15\assignment2\repository\ApiKeyRepository.java) Use to handle persist entity data `ApiKey` in project to table `api_key` in database, like `select`, `insert`, `update`, and `delete`

### Service and Implementation

1. `ProductService`

[Product Service](assignment2\src\main\java\com\example\lecture15\assignment2\service\ProductService.java) Create Service Interface for `Product Implementation`

2. `ProductServiceImplementation`

[Product Service Implemetation](assignment2\src\main\java\com\example\lecture15\assignment2\service\Impl\ProductServiceImpl.java) Create Product Implementation that Implement Method from `Product Service` and the Body Calling from Repository

### Config

1. `ApiKeyFilter`

[Api Key Filter](assignment2\src\main\java\com\example\lecture15\assignment2\config\ApiKeyFilter.java) Use to create `Filter` for checking `ApiKey` that include in the request also check if the `ApiKey` exist in database or not before execute the request. This code also can add the header for the response

### Controller

1. `ProductController`

[Product Controller](assignment2\src\main\java\com\example\lecture15\assignment2\controller\ProductController.java) Use to define all the mapping for the web process for the `Product`

### End-Point

1. **GET** `All Data Product`

2. **GET** `Product By Id`

3. **POST** `Create New Product`

4. **PUT** `Edit Existing Product by Id`

5. **DELETE** `Delete Existing Product by Id`

## Task 02: Store API Key in Database

[DML](table.sql) Use to store `api_key` in the database named `testApiKey`, using DML

![DML API Key](img/Store%20API%20Key%20in%20Database.PNG)

## Task 03: Verify All Requests having Header "api-key" that Stored in Database

1. **GET** `All Data Product`

![Get All With Api Key](img/Get%20All%20Product%20With%20API%20Key%201.PNG) Get All Data With the Right API Key

![Get All Without Api Key](img/Get%20All%20Product%20Without%20API%20Key%201.PNG) Get All Data Without the API Key

![Get All Wrong Api Key](img/Get%20All%20Product%20Wrong%20API%20Key%201.PNG) Get All Data Wrong the API Key

2. **GET** `Product By Id`

![Get By With Api Key](img/Get%20Product%20By%20Id%20With%20API%20Key%201.PNG) Get Data By Id With the Right API Key

![Get By Without Api Key](img/Get%20Product%20By%20Id%20Without%20API%20Key%201.PNG) Get Data By Id Without the API Key

![Get By Wrong Api Key](img/Get%20Product%20By%20Id%20Wrong%20API%20Key%201.PNG) Get Data By Id Wrong the API Key

3. **POST** `Create New Product`

![Post With Api Key](img/Create%20Product%20With%20API%20Key%201.PNG)

![Post With Api Key](img/Create%20Product%20With%20API%20Key%202.PNG) Create Data With the Right API Key

![Post Without Api Key](img/Create%20Product%20Without%20API%20Key%201.PNG)

![Post Without Api Key](img/Create%20Product%20Without%20API%20Key%202.PNG) Create Data Without the API Key

![Post Wrong Api Key](img/Create%20Product%20Wrong%20API%20Key%201.PNG)

![Post Wrong Api Key](img/Create%20Product%20Wrong%20API%20Key%202.PNG) Create Data Wrong the API Key

4. **PUT** `Edit Existing Product by Id`

![Put With Api Key](img/Update%20Existing%20Product%20With%20API%20Key%201.PNG)

![Put With Api Key](img/Update%20Existing%20Product%20With%20API%20Key%202.PNG) Update Data With the Right API Key

![Put Without Api Key](img/Update%20Existing%20Product%20Without%20API%20Key%201.PNG)

![Put Without Api Key](img/Update%20Existing%20Product%20Without%20API%20Key%202.PNG) Update Data Without the API Key

![Put Wrong Api Key](img/Update%20Existing%20Product%20Wrong%20API%20Key%201.PNG)

![Put Wrong Api Key](img/Update%20Existing%20Product%20Wrong%20API%20Key%202.PNG) Update Data Wrong the API Key

5. **DELETE** `Delete Existing Product by Id`

![Delete With Api Key](img/Delete%20Existing%20Product%20With%20API%20Key%201.PNG) Delete Data With the Right API Key

![Delete Without Api Key](img/Delete%20Existing%20Product%20Without%20API%20Key%201.PNG) Delete Data Without the API Key

![Delete Wrong Api Key](img/Delete%20Existing%20Product%20Wrong%20API%20Key%201.PNG) Delete Data Wrong the API Key

## Task 04: Return All Response to Client Include Header "source" : "fpt-software"

1. **GET** `All Data Product`

![Response Get All With Api Key](img/Get%20All%20Product%20With%20API%20Key%202.PNG) Response Get All With Api Key

![Response Get All Without Api Key](img/Get%20All%20Product%20Without%20API%20Key%202.PNG) Response Get All Without Api Key

![Response Get All Wrong Api Key](img/Get%20All%20Product%20Wrong%20API%20Key%202.PNG) Response Get All Wrong Api Key

2. **GET** `Product By Id`

![Response Get Data By Id With Api Key](img/Get%20Product%20By%20Id%20With%20API%20Key%202.PNG) Response Get Data By Id With Api Key

![Response Get Data By Id Without Api Key](img/Get%20Product%20By%20Id%20Without%20API%20Key%202.PNG) Response Get Data By Id Without Api Key

![Response Get Data By Id Wrong Api Key](img/Get%20Product%20By%20Id%20Wrong%20API%20Key%202.PNG) Response Get Data By Id Wrong Api Key

3. **POST** `Create New Product`

![Response Post With Api Key](img/Create%20Product%20With%20API%20Key%203.PNG) Response Create Data With Api Key

![Response Post Without Api Key](img/Create%20Product%20Without%20API%20Key%203.PNG) Response Create Data Without Api Key

![Response Post Wrong Api Key](img/Create%20Product%20Wrong%20API%20Key%202.PNG) Response Create Data Wrong Api Key

4. **PUT** `Edit Existing Product by Id`

![Response Put With Api Key](img/Update%20Existing%20Product%20With%20API%20Key%202.PNG) Response Update Data With Api Key

![Response Put Without Api Key](img/Update%20Existing%20Product%20Without%20API%20Key%202.PNG) Response Update Data Without Api Key

![Response Put Wrong Api Key](img/Update%20Existing%20Product%20Wrong%20API%20Key%202.PNG) Response Update Data Wrong Api Key

5. **DELETE** `Delete Existing Product by Id`

![Response Delete With Api Key](img/Delete%20Existing%20Product%20With%20API%20Key%202.PNG) Response Delete Data With Api Key

![Response Delete Without Api Key](img/Delete%20Existing%20Product%20Without%20API%20Key%202.PNG) Response Delete Data Without Api Key

![Response Delete Wrong Api Key](img/Delete%20Existing%20Product%20Wrong%20API%20Key%202.PNG) Response Delete Data Wrong Api Key