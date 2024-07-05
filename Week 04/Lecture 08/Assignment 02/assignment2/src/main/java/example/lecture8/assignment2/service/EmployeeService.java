package example.lecture8.assignment2.service;

import example.lecture8.assignment2.model.Employee;
import example.lecture8.assignment2.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(int id) {
        return employeeRepository.findEmployeeById(id);
    }

    public String createEmployee(Employee emp) {
        int status = employeeRepository.createEmployee(emp);

        if(status == 0) {
            return "Failed to Create Employee";
        }

        return "Employee Data:\nName: " + emp.getName() + "\nDepartment: " + emp.getDepartment() + "\nAge: " + emp.getAge() +"\nEmployee been Created" ;
    }

    public String updateEmployee(Employee emp, int id) {
        Employee emp1 = employeeRepository.findEmployeeById(id);
        int status = employeeRepository.updateEmployee(emp, id);

        if(status == 0) {
            return "Failed to Edit Employee";
        }

        return "Before Updated:\n" + emp1.show() + "\nAfter Updated:\n" + emp.show() + "\nEmployee been Edited";
    }

    public String deleteEmployee(int id) {
        Employee emp = employeeRepository.findEmployeeById(id);
        int status = employeeRepository.deleteEmployee(id);

        if(status == 0) {
            return "Failed to Delete Employee";
        }

        return "Employee Data:\n" + emp.show() + "\nEmployee been Deleted";
    }
}
