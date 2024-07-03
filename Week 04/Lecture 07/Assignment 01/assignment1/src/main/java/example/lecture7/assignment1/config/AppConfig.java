package example.lecture7.assignment1.config;

import example.lecture7.assignment1.controller.EmployeeWork;
import example.lecture7.assignment1.model.Employee;
import example.lecture7.assignment1.model.Employee2;
import example.lecture7.assignment1.model.Employee3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EmployeeWork employeeWork() {
        return new EmployeeWork();
    }

    //Assignment 01 - Task 2 Using Constructor Injection
    @Bean
    public Employee employee() {
        return new Employee("Fadhly", employeeWork());
    }

    //Assignment 01 - Task 3 Using Field Injection
    @Bean
    public Employee2 employee2() {
        Employee2 employee2 = new Employee2();
        employee2.setName("Al-farizi");

        return employee2;
    }

    //Assignment 01 - Task 3 Using Setter Injection
    @Bean
    public Employee3 employee3() {
        Employee3 employee3 = new Employee3();
        employee3.setName("Fadhly Al-farizi");

        return employee3;
    }
}
