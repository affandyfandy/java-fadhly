package example.lecture7.assignment2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "example.lecture7.assignment2.service")
public class AppConfig {
}
