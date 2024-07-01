package example.lecture7.assignment1.config;

import example.lecture7.assignment1.controller.EmployeeWork;
import example.lecture7.assignment1.model.Employee;
import example.lecture7.assignment1.model.Employee2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EmployeeWork employeeWork() {
        return new EmployeeWork();
    }

    //Assignment 01 - Task 2
    @Bean
    public Employee employee() {
        return new Employee("Fadhly", employeeWork());
    }

    //Assignment 01 - Task 3
    @Bean
    public Employee2 employee2() {
        Employee2 employee = new Employee2();
        employee.setName("Al-farizi");

        return employee;
    }
}
