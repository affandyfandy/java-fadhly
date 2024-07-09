package example.lecture8.assignment1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/")
    public String testingMessage() {
        return "Hello World!, Testing Spring Boot Project";
    }
}
