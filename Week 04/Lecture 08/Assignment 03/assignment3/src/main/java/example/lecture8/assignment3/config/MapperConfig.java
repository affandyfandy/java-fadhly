package example.lecture8.assignment3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.lecture8.assignment3.repository.EmployeeRowMapper;

@Configuration
public class MapperConfig {

    @Bean
    public EmployeeRowMapper employeeRowMapper() {
        return new EmployeeRowMapper();
    }
}
