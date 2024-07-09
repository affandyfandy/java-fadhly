package example.lecture7.assignment2.service;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(String to, String message) {
        System.out.println("Sending Email To: " + to);
        System.out.println("Message: " + message + "\n");
    }
}
