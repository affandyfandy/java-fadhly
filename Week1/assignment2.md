# 4 Principles of OOP in Java

## Encapsulation
Encapsulation is the bundling of data (attributes) and methods (functions) that operate on the data into a single unit, known as a class. It hides the internal state of objects and only exposes the necessary functionalities through well-defined interfaces. Encapsulation helps in achieving data hiding, abstraction, and modularity, enhancing the security and maintainability of the code.

## Inheritance
Inheritance is the mechanism by which a new class inherits properties and behaviors (methods) from an existing class, known as the superclass or base class. It promotes code reusability by allowing new classes (subclasses or derived classes) to extend or modify the behavior of existing classes. Subclasses can access and reuse the attributes and methods of their superclass, while also having the ability to override or extend them.

## Abstraction
Abstraction refers to the process of simplifying complex systems by hiding unnecessary implementation details and focusing on essential features. It involves defining a set of interfaces or abstract classes that provide a clear and concise view of the functionalities without exposing the internal complexities. Abstraction enables programmers to work at higher levels of abstraction, promoting code understandability, maintainability, and scalability.

## Polymorphism
Polymorphism allows objects of different classes to be treated as objects of a common superclass. It enables a single interface to represent multiple underlying data types or objects, facilitating code flexibility and extensibility. Polymorphism is often achieved through method overriding (runtime polymorphism) and method overloading (compile-time polymorphism).

## Example Code
```java
// Encapsulation
class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

// Inheritance
class Manager extends Employee {
    private double bonus;

    public Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    // Polymorphism: Method Overriding
    @Override
    public double getSalary() {
        return super.getSalary() + bonus;
    }
}

// Abstraction
abstract class Department {
    public abstract void departmentName();
}

class HRDepartment extends Department {
    @Override
    public void departmentName() {
        System.out.println("Human Resources");
    }
}

class FinanceDepartment extends Department {
    @Override
    public void departmentName() {
        System.out.println("Finance");
    }
}

// Polymorphism: Method Overloading
class SalaryCalculator {
    public double calculate(Employee emp) {
        return emp.getSalary();
    }

    public double calculate(Manager mgr) {
        return mgr.getSalary();
    }
}

public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee("John Doe", 50000);
        Manager mgr = new Manager("Jane Smith", 70000, 10000);

        // Polymorphism: Objects of different classes treated as superclass type
        Employee emp1 = mgr;
        
        // Display details
        System.out.println(emp.getName() + "'s salary: " + emp.getSalary());
        System.out.println(mgr.getName() + "'s salary: " + mgr.getSalary());
        System.out.println(emp1.getName() + "'s salary: " + emp1.getSalary());

        // Abstract class
        Department hrDept = new HRDepartment();
        Department finDept = new FinanceDepartment();
        hrDept.departmentName();
        finDept.departmentName();

        // Polymorphism: Method Overloading
        SalaryCalculator calculator = new SalaryCalculator();
        System.out.println("Calculated salary for emp: " + calculator.calculate(emp));
        System.out.println("Calculated salary for mgr: " + calculator.calculate(mgr));
    }
}
```