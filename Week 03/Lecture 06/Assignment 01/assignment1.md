# Assignment 01

## Normalization

`Normalization` in a relational database is a process that organizes data to minimize redundancy and dependency by dividing a database into two or more tables and defining relationships between the tables.

| Advantages | Disadvantages |
|-|-|
| Reduced Data Redundancy | Increased Complexity |
| Improved Data Integrity | Performance Overhead |
| Efficient Data Update | Complex Joins |
| Enhanced Query Performance | Potentially Reduced Read Performance |
| Simplified Database Maintenance |  Difficult to Change Schema |

## 1NF, 2NF, 3NF

### Unnormalized Table

`Table storing information about students, their courses, and their instructors`

| StudentID | StudentName | CourseID | CourseName | InstructorID | InstructorName |
|-----------|-------------|----------|------------|--------------|----------------|
| 1         | Alice       | 101      | Math       | 1001         | Dr. Smith      |
| 1         | Alice       | 102      | History    | 1002         | Dr. Brown      |
| 2         | Bob         | 101      | Math       | 1001         | Dr. Smith      |
| 2         | Bob         | 103      | Physics    | 1003         | Dr. White      |

### 1NF

`1NF` table, it contains only atomic (indivisible) values, and each column contains only one value for each row. It must also have a primary key that uniquely identifies each record.

`Students-Courses-Instructors Table`

| StudentID | StudentName | CourseID | CourseName | InstructorID | InstructorName |
|-----------|-------------|----------|------------|--------------|----------------|
| 1         | Alice       | 101      | Math       | 1001         | Dr. Smith      |
| 1         | Alice       | 102      | History    | 1002         | Dr. Brown      |
| 2         | Bob         | 101      | Math       | 1001         | Dr. Smith      |
| 2         | Bob         | 103      | Physics    | 1003         | Dr. White      |

### 2NF

`2NF` table in `1NF` and all non-key attributes are fully functionally dependent on the entire primary key. This means there are no partial dependencies of any column on the primary key.

`Students-Courses Table`

| StudentID | CourseID |
|-----------|----------|
| 1         | 101      |
| 1         | 102      |
| 2         | 101      |
| 2         | 103      |

`Courses-Instructors Table`

| CourseID | CourseName | InstructorID | InstructorName |
|----------|------------|--------------|----------------|
| 101      | Math       | 1001         | Dr. Smith      |
| 102      | History    | 1002         | Dr. Brown      |
| 103      | Physics    | 1003         | Dr. White      |

### 3NF

`3NF` table is in `2NF` and all the attributes are functionally dependent only on the primary key. This means there are no transitive dependencies, where one non-key attribute is dependent on another non-key attribute.

`Students-Courses Table`

| StudentID | CourseID |
|-----------|----------|
| 1         | 101      |
| 1         | 102      |
| 2         | 101      |
| 2         | 103      |

`Courses Table`

| CourseID | CourseName | InstructorID |
|----------|------------|--------------|
| 101      | Math       | 1001         |
| 102      | History    | 1002         |
| 103      | Physics    | 1003         |

`Instructors Table`

| InstructorID | InstructorName |
|--------------|----------------|
| 1001         | Dr. Smith      |
| 1002         | Dr. Brown      |
| 1003         | Dr. White      |