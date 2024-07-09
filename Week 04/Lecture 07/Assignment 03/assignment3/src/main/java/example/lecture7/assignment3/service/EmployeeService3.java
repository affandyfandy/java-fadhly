package example.lecture7.assignment3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class EmployeeService3 {

    private EmailService emailService;

    public EmployeeService3() {
        System.out.println("EmployeeService instance created Using Setter Injection\n");
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sending(String email, String message) {
        emailService.sendEmail(email, message);
    }
}
