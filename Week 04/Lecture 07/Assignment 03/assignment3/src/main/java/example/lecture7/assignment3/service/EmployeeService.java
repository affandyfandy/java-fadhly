package example.lecture7.assignment3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class EmployeeService {
    private EmailService emailService;

    @Autowired
    public EmployeeService(EmailService emailService) {
        this.emailService = emailService;
        System.out.println("EmployeeService instance created Using Constructor Injection\n");
    }

    public void sending(String email, String message) {
        emailService.sendEmail(email, message);
    }
}
