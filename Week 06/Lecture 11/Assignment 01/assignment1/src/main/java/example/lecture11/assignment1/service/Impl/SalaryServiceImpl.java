package example.lecture11.assignment1.service.Impl;

import example.lecture11.assignment1.entity.Salary;
import example.lecture11.assignment1.entity.SalaryId;
import example.lecture11.assignment1.repository.SalaryRepository;
import example.lecture11.assignment1.service.SalaryService;

import lombok.AllArgsConstructor;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    
    private final SalaryRepository salaryRepository;
    
    @Override
    public Salary findSalaryById(SalaryId id) {
        return salaryRepository.findById(id).orElse(null);
    }

    @Override
    public Salary save(Salary salary) {
        SalaryId id = new SalaryId(salary.getEmpNo(), salary.getFromDate());
        Salary slr = findSalaryById(id);
        if(slr == null) {
            return salaryRepository.save(salary);
        }
        return null;
    }

    @Override
    public Salary updateSalary(SalaryId id, Date toDate) {
        Salary slr = findSalaryById(id);
        if(slr != null) {
            slr.setToDate(toDate);

            Salary updateSlr = salaryRepository.save(slr);
            return updateSlr;
        }
        return null;
    }
}
