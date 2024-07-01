package example.lecture7.assignment1;

import example.lecture7.assignment1.model.Employee;
import example.lecture7.assignment1.model.Employee2;
import example.lecture7.assignment1.config.AppConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Assignment1Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Application.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		//Assignment 01 - Task 2
		// Employee employee = context.getBean(Employee.class);
		// employee.working();

		//Assignment 01 - Task 3
		Employee2 employee2 = context.getBean(Employee2.class);
		employee2.working();
	}
}
