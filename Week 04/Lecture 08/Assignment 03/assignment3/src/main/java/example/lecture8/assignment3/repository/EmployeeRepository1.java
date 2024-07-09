package example.lecture8.assignment3.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import example.lecture8.assignment3.model.Employee;

@Repository
public class EmployeeRepository1 {
    private final JdbcTemplate jdbcTemplate;
    private final EmployeeRowMapper employeeRowMapper;

    public EmployeeRepository1(@Qualifier("jdbcTemplate1") JdbcTemplate jdbcTemplate, EmployeeRowMapper employeeRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.employeeRowMapper = employeeRowMapper;
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee", employeeRowMapper);
    }

    public Employee findEmployeeById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id = ?", employeeRowMapper, id);
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public int createEmployee(Employee emp) {
        return jdbcTemplate.update("INSERT INTO employee (name, department, age) VALUES (?, ?, ?)", emp.getName(), emp.getDepartment(), emp.getAge());
    }

    public int createEmployee2(Employee emp) {
        return jdbcTemplate.update("INSERT INTO employee_test (name, department, age) VALUES (?, ?, ?)", emp.getName(), emp.getDepartment(), emp.getAge());
    }

    public int updateEmployee(Employee emp, int id) {
        try {
            if(findEmployeeById(id) == null) {
                return -1;
            }
            
            return jdbcTemplate.update("UPDATE employee SET name = ?, department = ?, age = ? WHERE id = ?", emp.getName(), emp.getDepartment(), emp.getAge(), id);
        } catch(DataIntegrityViolationException ve) {
            return -2;
        }
    }

    public int deleteEmployee(int id) {
        if(findEmployeeById(id) == null) {
            return -1;
        }
        
        return jdbcTemplate.update("DELETE FROM employee WHERE id = ?", id);
    }
}
