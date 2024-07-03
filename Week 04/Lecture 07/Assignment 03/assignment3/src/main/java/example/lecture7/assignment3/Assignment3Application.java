package example.lecture7.assignment3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import example.lecture7.assignment3.config.AppConfig;
import example.lecture7.assignment3.service.EmployeeService;
import example.lecture7.assignment3.service.EmployeeService2;
import example.lecture7.assignment3.service.EmployeeService3;

@SpringBootApplication
public class Assignment3Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		//Using Constructor Injection
		EmployeeService employee = context.getBean(EmployeeService.class);
		employee.sending("emailtest1@gmail.com", "Test Message");

		//Using Field Injection
		EmployeeService2 employee2 = context.getBean(EmployeeService2.class);
		employee2.sending("emailtest2@gmail.com", "Test Message");

		//Using Setter Injection
		EmployeeService3 employee3 = context.getBean(EmployeeService3.class);
		employee3.sending("emailtest3@gmail.com", "Test Message");

		//Using Constructor Injection
		EmployeeService employeeAgain = context.getBean(EmployeeService.class);
		employeeAgain.sending("emailtest4@gmail.com", "Test Message");

		//Using Field Injection
		EmployeeService2 employee2Again = context.getBean(EmployeeService2.class);
		employee2Again.sending("emailtest5@gmail.com", "Test Message");

		//Using Setter Injection
		EmployeeService3 employee3Again = context.getBean(EmployeeService3.class);
		employee3Again.sending("emailtest6@gmail.com", "Test Message");
	}
}
