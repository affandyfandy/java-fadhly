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

    public String findEmployeeById(int id) {
        Employee emp = employeeRepository.findEmployeeById(id);
        if(emp == null) {
            return "Employee with Id " + id + " doesn't exist";
        }

        return emp.show();
    }

    public String createEmployee(Employee emp) {
        int status = employeeRepository.createEmployee(emp);

        if(status == 0) {
            return "Failed to Create Employee";
        }

        return "Employee Success Created";
    }

    public String updateEmployee(Employee emp, int id) {
        int status = employeeRepository.updateEmployee(emp, id);

        if(status == 0) {
            return "Failed to Edit Employee";
        } else if(status == -1) {
            return "Employee with id " + id + " doesn't exist";
        } else if(status == -2) {
            return "Body Column doesn't exist";
        }

        Employee emp1 = employeeRepository.findEmployeeById(id);
        return "After Updated:\n" + emp1.show() + "\nEmployee been Edited";
    }

    public String deleteEmployee(int id) {
        int status = employeeRepository.deleteEmployee(id);

        if(status == 0) {
            return "Failed to Delete Employee";
        } else if(status == -1) {
            return "Employee with id " + id + " doesn't exist";
        }

        return "Employee been Deleted";
    }
}
