package example.lecture12.assignment1.repository;

import example.lecture12.assignment1.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

}
