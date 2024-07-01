# Assignment 01 - Lecture 07

## Task 1: Advantages and Drawbacks of Dependency Injection (DI)

`Dependency Injection` which `dependency` is an object that another object depends on it and `injection` is means passing dependencies to other object.

So `Dependency Injection` in Object Oriented Programming (OOP) is a design pattern that dependencies of class is created from external source rather than created in internal source.

### Advantages using Dependency Injection (DI)

1. `Enhanced Testability` by enabling the mocking or stubbing of dependencies, so it can be focus on functionality without external dependencies influecing the outcome.
2. `Improved Maintainability` by making the codebase easier to understand, modifiy, and extend.
3. `Increased Flexibility` by making applications more adaptable to different environments or use cases. It can inject different implementations of the same dependency based on configuration.
4. `Reduces Coupling` by relying on interfaces (abstractions) instead of concrete implementations (dependencies). Each component doesn't need to know how to create or obtain its dependencies

### Drawbacks using Dependency Injection (DI)

1. `Increased Complexity` by having a learning curve, it can be difficult for someone who are new to the concept, like interfaces, injectors, and different injection styles.
2. `Debugging Challenges` by understanding the flow of dependencies and potential configuration errors becomes crucial, since objects are instantiated by the DI container rather than directly in the code.
3. `Framework Dependence` by limit portability if you need to switch frameworks in the future and ensuring compatibility with other libraries and frameworks can be an additional maintenance burden.

## Task 2: Configuration Constructor Injection using @Bean

### Declare Controller EmployeeWork

![Controller EmployeeWork](img/Create%20Controller%20EmployeeWork.PNG)

### Declare Model Employee

![Model Employee Task 2](img/Create%20Model%20Employee%201.PNG)

### Declace Config AppConfig

![Config AppConfig Task 2](img/Create%20Config%20AppConfig.PNG)

### Main Application

![Running Appication Task 2](img/Running%20Application%20Constructor%20DI.PNG)

## Task 3: Configuration Setter and Field Injection using @Bean

### Declare Controller EmployeeWork

![Controller EmployeeWork](img/Create%20Controller%20EmployeeWork.PNG)

### Declare Model Employee

![Model Employee Task 3](img/Create%20Model%20Employee%202.PNG)

### Declace Config AppConfig

![Config AppConfig Task 3](img/Create%20Config%20AppConfig.PNG)

### Main Application

![Running Appication Task 3](img/Running%20Application%20Setter%20and%20Field%20DI.PNG)