package example.lecture7.assignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import example.lecture7.assignment2.service.EmployeeService;
import example.lecture7.assignment2.service.EmployeeService2;
import example.lecture7.assignment2.service.EmployeeService3;

@SpringBootApplication
public class Assignment2Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Assignment2Application.class, args);

		//Using Constructor Injection
		EmployeeService employee = context.getBean(EmployeeService.class);
		employee.sending("email1@gmail.com", "Test Message Constructor Injection");

		//Using Field Injection
		EmployeeService2 employee2 = context.getBean(EmployeeService2.class);
		employee2.sending("email2@gmail.com", "Test Message Field Injection");

		//Using Setter Injection
		EmployeeService3 employee3 = context.getBean(EmployeeService3.class);
		employee3.sending("email3@gmail.com", "Test Message Setter Injection");
	}
}
