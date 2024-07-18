package example.lecture11.assignment1.service;

import example.lecture11.assignment1.entity.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);
    Employee findEmployeeById(int id);
    Employee updateEmployee(int id, Employee employee);
    Employee save(Employee employee);
    void delete(int id);
}
