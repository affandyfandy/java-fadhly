package example.lecture10.assignment1.config;

import example.lecture10.assignment1.mapper.EmployeeMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapConfig {
    @Bean
    public EmployeeMapper employeeMapper() {
        return EmployeeMapper.INSTANCE;
    }
}
