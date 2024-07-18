package example.lecture11.assignment1.service.Impl;

import example.lecture11.assignment1.entity.Employee;
import example.lecture11.assignment1.repository.EmployeeRepository;
import example.lecture11.assignment1.service.EmployeeService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Employee emp = findEmployeeById(id);
        if(emp != null) {
            emp.setBirthDate(employee.getBirthDate());
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setGender(employee.getGender());
            emp.setHireDate(employee.getHireDate());

            Employee updateEmp = save(emp);
            return updateEmp;
        }
        return emp;
    }

    @Override
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }
}
