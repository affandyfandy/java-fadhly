package example.lecture7.assignment3.controller;

import example.lecture7.assignment3.service.EmployeeService4;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    private final EmployeeService4 EmployeeService4;

    @Autowired
    public EmailController(EmployeeService4 EmployeeService4) {
        this.EmployeeService4 = EmployeeService4;
    }

    @GetMapping("/send")
    public String sendEmail(@RequestParam String email, @RequestParam String message) {
        EmployeeService4.sending(email, message);
        return "Email sent to " + email;
    }
}
