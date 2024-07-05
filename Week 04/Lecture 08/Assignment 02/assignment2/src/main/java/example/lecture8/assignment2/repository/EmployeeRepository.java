package example.lecture8.assignment2.repository;

import example.lecture8.assignment2.model.Employee;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setName(rs.getString("name"));
            emp.setDepartment(rs.getString("department"));
            emp.setAge(rs.getInt("age"));
            return emp;
        }
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee", new EmployeeRowMapper());
    }

    public Employee findEmployeeById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id = ?", new EmployeeRowMapper(), id);
    }

    public int createEmployee(Employee emp) {
        return jdbcTemplate.update("INSERT INTO employee (name, department, age) VALUES (?, ?, ?)", emp.getName(), emp.getDepartment(), emp.getAge());
    }

    public int updateEmployee(Employee emp, int id) {
        return jdbcTemplate.update("UPDATE employee SET name = ?, department = ?, age = ? WHERE id = ?", emp.getName(), emp.getDepartment(), emp.getAge(), id);
    }

    public int deleteEmployee(int id) {
        return jdbcTemplate.update("DELETE FROM employee WHERE id = ?", id);
    }
}