package example.lecture8.assignment3.controller;

import example.lecture8.assignment3.model.Employee;
import example.lecture8.assignment3.service.EmployeeService;

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
@RequestMapping("/api/v2/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/ds1/allEmployee")
    public List<Employee> findAll1() {
        return employeeService.findAll1();
    }

    @GetMapping("/ds2/allEmployee")
    public List<Employee> findAll2() {
        return employeeService.findAll2();
    }

    @GetMapping("/ds1/getEmployee/{id}")
    public String findEmployeeById1(@PathVariable int id) {
        return employeeService.findEmployeeById1(id);
    }

    @GetMapping("/ds2/getEmployee/{id}")
    public String findEmployeeById2(@PathVariable int id) {
        return employeeService.findEmployeeById2(id);
    }

    @PostMapping("/ds1/add")
    public String createEmployee1(@RequestBody Employee employee) {
        return employeeService.createEmployee1(employee);
    }

    @PostMapping("/ds1/testTransaction")
    public String createEmployeeTestTransaction(@RequestBody Employee emp1) {
        try {
            employeeService.createEmployeeTestTransaction(emp1, null);
            return "Success Insert Data";
        } catch(Exception e) {
            return "Failed Insert Data " + e.getMessage();
        }
    }

    @PostMapping("/ds2/add")
    public String createEmployee2(@RequestBody Employee employee) {
        return employeeService.createEmployee2(employee);
    }

    @PutMapping("/ds1/change/id/{id}")
    public String changeEmployee1(@RequestBody Employee employee, @PathVariable int id) {
        return employeeService.updateEmployee1(employee, id);
    }

    @PutMapping("/ds2/change/id/{id}")
    public String changeEmployee2(@RequestBody Employee employee, @PathVariable int id) {
        return employeeService.updateEmployee2(employee, id);
    }

    @DeleteMapping("/ds1/delete/id/{id}")
    public String deleteEmployee1(@PathVariable int id) {
        return employeeService.deleteEmployee1(id);
    }

    @DeleteMapping("/ds2/delete/id/{id}")
    public String deleteEmployee2(@PathVariable int id) {
        return employeeService.deleteEmployee2(id);
    }
}
