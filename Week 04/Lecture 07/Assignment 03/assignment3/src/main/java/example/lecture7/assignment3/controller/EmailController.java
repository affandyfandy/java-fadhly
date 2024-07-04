package example.lecture7.assignment3.controller;

import example.lecture7.assignment3.service.EmployeeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping("/api/v1/email")
public class EmailController {
    private final EmployeeService EmployeeService;

    @Autowired
    public EmailController(EmployeeService EmployeeService) {
        this.EmployeeService = EmployeeService;
    }

    @GetMapping("/send")
    public String sendEmail(@RequestParam String email, @RequestParam String message) {
        EmployeeService.sending(email, message);
        return "Email sent to " + email;
    }
}
