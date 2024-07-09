package example.lecture7.assignment3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "example.lecture7.assignment3.service")
public class AppConfig {
}
