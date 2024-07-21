package example.lecture12.assignment1.service;

import example.lecture12.assignment1.entity.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);
    Page<Employee> findByCriteria(String firstName, String lastName, String gender, Date hireDate, Pageable pageable);
    Employee findEmployeeById(int id);
    Employee updateEmployee(int id, Employee employee);
    Employee save(Employee employee);
    void delete(int id);
}
