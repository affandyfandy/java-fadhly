# Assignment 01 - Lecture 08

## Task 01: Spring Boot Project

There are many ways to create Spring Boot Project, one of them is using Visual Studio Code Extensions. There are many needs for creating Spring Boot Project, so let's take a look.

### Visual Studio Code Extensions

1. `Spring Initializr Java Support`

![Spring Initializr Java Support](img/Extensions%20VSCode%201.png)

2. `Project Manager for Java`

![Project Manager for Java](img/Extensions%20VSCode%202.png)

3. `Maven for Java`

![Maven for Java](img/Extensions%20VSCode%203.png)

4. `Java`

![Java](img/Extensions%20VSCode%204.png)

5. `Extension Pack for Java`

![Extension Pack for Java](img/Extensions%20VSCode%205.png)

6. `Debugger for Java`

![Debugger for Java](img/Extensions%20VSCode%206.png)

7. `Test Runner for Java`

![Test Runner for Java](img/Extensions%20VSCode%207.png)

### Create Project

1. `Create Project from Schortcut in Visual Studio Code`

![Create Project from Schortcut in Visual Studio Code](img/Create%20Project%201.png)

2. `Choose Spring Boot Framework`

![Choose Spring Boot Framework](img/Create%20Project%202.png)

3. `Choose Maven Project`

![Choose Maven Project](img/Create%20Project%203.png)

4. `Choose Version that Suitable`

![Choose Version](img/Create%20Project%204.png)

5. `Choose Java as Project Language`

![Choose Java as Project Language](img/Create%20Project%205.png)

6. `Identify Group Id for Project`

![Identify Folder](img/Create%20Project%206.png)

7. `Identify Artifact Id for Project`

![](img/Create%20Project%207.png)

8. `Specifiy Packaging Type`

![Specifiy Packaging Type](img/Create%20Project%208.png)

9. `Choose Java Version that Suitable`

![Choose Java 17](img/Create%20Project%209.png)

10. `Choose Dependencies (If Any)`

![Choose Dependencies](img/Create%20Project%2010.png)

11. `Choose Folder or Directory to Store Project`

![Choose Folder or Directory to Store Project](img/Create%20Project%2011.png)

### File Structure

![File Structure](img/File%20Structure%201.png)

## Task 02: Running Local

I try to running local with additional dependency `Spring Web`.

1. `Update Dependency`

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

2. `Create Controller to Handle End-Point`

![Create Controller](img/Create%20Controller.png)

3. `Running and Result`

![Running Application](img/Running%20Application%201.png)

![Result Application](img/Running%20Application%202.png)