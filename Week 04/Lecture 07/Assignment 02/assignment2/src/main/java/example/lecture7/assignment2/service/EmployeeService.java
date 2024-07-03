package example.lecture7.assignment2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private EmailService emailService;

    @Autowired
    public EmployeeService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sending(String email, String message) {
        System.out.println("Using Constructor Injection");
        emailService.sendEmail(email, message);
    }
}
