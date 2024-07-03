package example.lecture7.assignment3.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class EmailServiceImpl implements EmailService {
    
    public EmailServiceImpl() {
        System.out.println("EmailServiceImpl instance created\n");
    }
    
    @Override
    public void sendEmail(String to, String message) {
        System.out.println("Sending Email To: " + to);
        System.out.println("Message: " + message + "\n");
    }
}
