package example.lecture9.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.lecture9.assignment2.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findAllByOrderByNameAsc();
}
