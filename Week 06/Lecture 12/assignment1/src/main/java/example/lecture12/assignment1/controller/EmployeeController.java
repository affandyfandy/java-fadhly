package example.lecture12.assignment1.controller;

import example.lecture12.assignment1.dto.EmployeeSearchCriteria;
import example.lecture12.assignment1.entity.Employee;
import example.lecture12.assignment1.service.EmployeeService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v5/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeService.findAll(pageable);
        if (employeePage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employeePage);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<Employee>> searchEmployees(@RequestBody EmployeeSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(searchCriteria.getPage(), searchCriteria.getSize());
        Page<Employee> employeePage = employeeService.findByCriteria(
                searchCriteria.getFirstName(), 
                searchCriteria.getLastName(), 
                searchCriteria.getGender(), 
                searchCriteria.getHireDate(),
                pageable
        );

        if (employeePage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employeePage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
        Employee emp = employeeService.findEmployeeById(id);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emp);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        Employee emp = employeeService.save(employee);
        if(emp == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        Employee emp = employeeService.updateEmployee(id, employee);
        if(emp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id) {
        Employee emp = employeeService.findEmployeeById(id);
        if(emp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee found");
        }
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted");
    }
}
