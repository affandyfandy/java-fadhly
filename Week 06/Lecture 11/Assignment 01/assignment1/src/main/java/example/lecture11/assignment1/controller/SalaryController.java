package example.lecture11.assignment1.controller;

import org.springframework.web.bind.annotation.*;

import example.lecture11.assignment1.entity.Salary;
import example.lecture11.assignment1.entity.SalaryId;
import example.lecture11.assignment1.service.SalaryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v4/salary")
@AllArgsConstructor
public class SalaryController {
    private final SalaryService salaryService;

    @PostMapping
    public ResponseEntity<?> createSalary(@RequestBody Salary salary) {
        Salary slr = salaryService.save(salary);
        if(slr == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate Salary");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(slr);
    }

    @PutMapping
    public ResponseEntity<?> updateSalary(@RequestBody Salary salary) {
        SalaryId id = new SalaryId(salary.getEmpNo(), salary.getFromDate());
        Salary slr = salaryService.updateSalary(id, salary.getToDate());
        if(slr == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Salary Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(slr);
    }
}
