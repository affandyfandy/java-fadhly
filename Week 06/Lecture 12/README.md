# Assignment 01 - Continue Last Project

[Last Project](Assignment01.com) It is same setting (different Database) with the last project, but there is addition about search employee with dynamic criteria in this project

1. `Create Database`

[Create Table using Query](table.sql) This query contains create Database, use Database, create of each tables in ERD, and have some dummy data to test

![Create Table Query](img/Create%20Database%20Table%201.PNG)

![Create Dummy Data Query](img/Create%20Database%20Table%202.PNG)

![Create Table Query](img/Create%20Database%20Table%203.PNG)

![Check Table Database](img/Create%20Database%20Table%204.PNG)

![Check Data](img/Create%20Database%20Table%205.PNG)

2. `Connet Database`

[Application Properties](assignment1\src\main\resources\application.properties) This file used to connect Database to the Project

## Task 01: Create Search Employee with Dynamic Criteria

Search for `Employee` with Dynamic Criteria is using 4 column to search from `Employee Entity`. For the input I used DTO to determine which coloumn that i wanna use for this searching.

![Search Criteria](img/Search%20Criteria.PNG)

1. `firstName` -> Field equal to firstName in `Employee Entity`
2. `lastName` -> Field equal to lastName in `Employee Entity`
3. `gender` -> Field equal to gender in `Employee Entity`
4. `hireDate` -> Field equal to hireDate in `Employee Entity`
5. `page` -> Defining paging
5. `size` -> Defining paging

### Employee Specification

[Specification EmployeeSpecification](assignment1\src\main\java\example\lecture12\assignment1\specification\EmployeeSpecification.java) This file used to create a config file to get parameter of the search method. It is using `equal`, so it will return `Employee` that exactly like parameter of criteria

### Employee Repository

[Repository EmployeeRepository](assignment1\src\main\java\example\lecture12\assignment1\repository\EmployeeRepository.java) This file will create JPA Repository for `Employee Entity` and create JPA Specification Executor to get data for the searching `Employee`

### Employee Service

[Service EmployeeService](assignment1\src\main\java\example\lecture12\assignment1\service\EmployeeService.java) his file contains interface of service method like, show data, save data, update data, delete data, and searching data

[Service EmployeeServiceImpl](assignment1\src\main\java\example\lecture12\assignment1\service\Impl\EmployeeServiceImpl.java) This file contains implementation of the interface before

### Employee DTO for Search

[DTO EmployeeSearchCriteria](assignment1\src\main\java\example\lecture12\assignment1\dto\EmployeeSearchCriteria.java) This file contains selection field of `Employee Entity`, only use `firstName`, `lastName`, `gender`, and `hireDate`. This file used for creating searching criteria of the `Employee`

### Employee Controller

[Controller Employee Controller](assignment1\src\main\java\example\lecture12\assignment1\controller\EmployeeController.java) This file contains HTTP Request of the `Employee`, URL path that will handle request is `/api/v5/employee`

### Running Application

![Searching Employee File](img/Create%20Searching%20Employee%201.PNG)

![Searching Employee File](img/Create%20Searching%20Employee%202.PNG)

![Searching Employee File](img/Create%20Searching%20Employee%203.PNG)

![Searching Employee File](img/Create%20Searching%20Employee%204.PNG)

![Searching Employee File](img/Create%20Searching%20Employee%205.PNG)

![Searching Employee File](img/Create%20Searching%20Employee%206.PNG)

![Searching Employee](img/Create%20Searching%20Employee%207.PNG)

![Searching Employee](img/Create%20Searching%20Employee%208.PNG)

Field of the body is optional, so it's can search only by `firstName` or `lastName` or `gender` or `hireDate` or combination of the fields

## Task 02: Research - Hibernate Cache

### Explanation Hibernate Cache

Hibernate Cache is divided into two levels:

1. `First-Level Cache` -> It is a session chace that will create chace object. It can be select which cache that we want to clear or clear the cache completely. The object chace in the session isn't visible to other session, so when the session closed the cache will be lost. When fetch data or entity using session, it will look session cache first before query the database.

On first-level it can be done by using annotation `@Transactional`, it will run by a single session.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee getEmployeeById(Long id) {
        // First query to load the employee
        Employee emp1 = employeeRepository.findById(id).orElse(null);

        // Second query to load the same employee
        Employee emp2 = employeeRepository.findById(id).orElse(null);

        return emp2; // Both emp1 and emp2 refer to the same instance from the first-level cache
    }
}
```

2. `Second-Level Cache` -> It is also a session chace, but the object can across the sessions. It usually used to store entities, collections, and queries. By default this level is disables, but can be enable through configuration.

On second-level it should contain other dependencies and make some additional configuration, like using annotations `@Cache` and `@Cacheable`

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-ehcache</artifactId>
    <version>5.6.0.Final</version>
</dependency>
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <version>2.10.6</version>
</dependency>
```

```yml
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.use_query_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.EhCacheRegionFactory
spring.jpa.properties.net.sf.ehcache.configurationResourceName=/ehcache.xml
```

```xml
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd">

    <defaultCache
        maxEntriesLocalHeap="10000"
        eternal="false"
        timeToIdleSeconds="300"
        timeToLiveSeconds="600"
        overflowToDisk="true"
        diskSpoolBufferSizeMB="20"
        maxEntriesLocalDisk="10000000"
        diskPersistent="false"
        diskExpiryThreadIntervalSeconds="120"
        memoryStoreEvictionPolicy="LRU"/>
    
    <cache name="com.example.myapp.entity.Employee"
           maxEntriesLocalHeap="1000"
           eternal="false"
           timeToIdleSeconds="300"
           timeToLiveSeconds="600"
           overflowToDisk="true"
           diskSpoolBufferSizeMB="20"
           maxEntriesLocalDisk="10000000"
           diskPersistent="false"
           diskExpiryThreadIntervalSeconds="120"
           memoryStoreEvictionPolicy="LRU"/>
</ehcache>
```

```java
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    // Other fields, getters, and setters
}
```

### Advantages using Hibernate Cache

1. `Performance Improvement` -> It can be faster data retrieval and reduce number database hits by caching frequently
2. `Reduced Database Load` -> It can be ecreases the load on the database by caching results
3. `Network Efficiency` -> It can be reduces network latency and bandwidth by minimizing the number of queries

### Disadvantages using Hibernate Cache

1. `Increased Memory Usage` -> It requires additional memory to store cached data
2. `Stale Data Risk` -> It can be serves stale data if the cache is not properly synchronized with the database changes
3. `Cache Synchronization` -> It can be hard to ensuring cache consistency with the database, especially when the database is updated outside of Hibernate