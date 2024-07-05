package example.lecture8.assignment2.controller;

import example.lecture8.assignment2.model.Employee;
import example.lecture8.assignment2.service.EmployeeService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/allEmployee")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/getEmployee/{id}")
    public Employee findEmployeeById(@PathVariable int id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping("/add")
    public String createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/change/id/{id}")
    public String changeEmployee(@RequestBody Employee employee, @PathVariable int id) {
        return employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping("/delete/id/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }
}
