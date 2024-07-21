package example.lecture11.assignment1.controller;

import example.lecture11.assignment1.entity.Department;
import example.lecture11.assignment1.service.DepartmentService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v4/department")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Page<Department>> getAllDepartment(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departments = departmentService.findAll(pageable);
        if(departments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departments);
    }

    @GetMapping(value = "/{deptNo}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("deptNo") String deptNo) {
        Department dept = departmentService.findDepartmentById(deptNo);
        if(dept == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dept);
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        Department dept = departmentService.save(department);
        if(dept == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department already created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dept);
    }

    @PutMapping(value = "/{deptNo}")
    public ResponseEntity<?> updateDepartment(@PathVariable("deptNo") String deptNo, @RequestBody Department department) {
        Department dept = departmentService.updateDepartment(deptNo, department);
        if(dept == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No department found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dept);
    }

    @DeleteMapping(value = "/{deptNo}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("deptNo") String deptNo) {
        Department dept = departmentService.findDepartmentById(deptNo);
        if(dept == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No department found");
        }
        departmentService.delete(deptNo);
        return ResponseEntity.status(HttpStatus.OK).body("Department Deleted");
    }
}