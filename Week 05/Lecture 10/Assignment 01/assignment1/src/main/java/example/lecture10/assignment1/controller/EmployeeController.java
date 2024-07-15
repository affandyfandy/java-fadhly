package example.lecture10.assignment1.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import example.lecture10.assignment1.dto.EmployeeDTO;
import example.lecture10.assignment1.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v3/employee")
@AllArgsConstructor
public class EmployeeController {
    
    @Autowired
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> listAllEmployee(){
        List<EmployeeDTO> listEmployee = employeeService.findAll();
        if (listEmployee.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<EmployeeDTO> findEmployeebyID(@PathVariable("id") UUID id) {
        return employeeService.findEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/department/{department}")
    public ResponseEntity<List<EmployeeDTO>> findEmployeebyDepartment(@PathVariable("department") String department) {
        List<EmployeeDTO> listEmployee = employeeService.findEmployeeByDepartment(department);
        if (listEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(value = "id") UUID id,
                                                 @RequestBody EmployeeDTO employeeForm) {
        try {
            EmployeeDTO updatedEmployee = employeeService.updateById(id, employeeForm);
            return ResponseEntity.ok(updatedEmployee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employee) {
        try {
            EmployeeDTO savedEmployee = employeeService.save(employee);
            return ResponseEntity.ok(savedEmployee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/upload-csv")
    public ResponseEntity<List<EmployeeDTO>> saveEmployeebyCSV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        try {
            List<EmployeeDTO> savedEmployees = employeeService.saveCSV(file);
            return ResponseEntity.ok(savedEmployees);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable(value = "id") UUID id) {
        try {
            employeeService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}