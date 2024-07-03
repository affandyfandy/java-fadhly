package example.lecture7.assignment3.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class EmailServiceImpl2 implements EmailService {
    
    public EmailServiceImpl2() {
        System.out.println("EmailServiceImpl with Request Scope instance created\n");
    }
    
    @Override
    public void sendEmail(String to, String message) {
        System.out.println("Sending Email To: " + to);
        System.out.println("Message: " + message + "\n");
    }
}
