package example.lecture7.assignment2.service;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmailService emailService;

    public EmployeeService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sending(String email, String message) {
        emailService.sendEmail(email, message);
    }
}
