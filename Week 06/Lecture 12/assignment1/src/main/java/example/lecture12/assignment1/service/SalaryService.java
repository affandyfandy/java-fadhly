package example.lecture12.assignment1.service;

import example.lecture12.assignment1.entity.Salary;
import example.lecture12.assignment1.entity.SalaryId;

import java.util.Date;

public interface SalaryService {
    Salary findSalaryById(SalaryId id);
    Salary save(Salary salary);
    Salary updateSalary(SalaryId id, Date toDate);
}
