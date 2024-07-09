package example.lecture7.assignment2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService2 {

    @Autowired
    private EmailService emailService;

    public void sending(String email, String message) {
        emailService.sendEmail(email, message);
    }
}
