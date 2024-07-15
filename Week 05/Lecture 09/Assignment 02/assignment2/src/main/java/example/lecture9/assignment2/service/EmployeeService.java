package example.lecture9.assignment2.service;

import example.lecture9.assignment2.model.Employee;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(String theId);

    void save(Employee theEmployee);

    void deleteById(String theId);

    void saveCSV(MultipartFile file);
}
