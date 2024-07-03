package example.lecture7.assignment3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class EmployeeService4 {
    private EmailService emailService;

    @Autowired
    public EmployeeService4(EmailService emailService) {
        this.emailService = emailService;
        System.out.println("EmployeeService instance created Using Constructor Injection\n");
    }

    public void sending(String email, String message) {
        emailService.sendEmail(email, message);
    }
}
