package example.lecture7.assignment3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class EmployeeService2 {
    
    @Autowired
    private EmailService emailService;

    public EmployeeService2() {
        System.out.println("EmployeeService instance created Using Field Injection\n");
    }

    public void sending(String email, String message) {
        emailService.sendEmail(email, message);
    }
}
