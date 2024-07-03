package example.lecture7.assignment2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService3 {
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sending(String email, String message) {
        System.out.println("Using Setter Injection");
        emailService.sendEmail(email, message);
    }
}
