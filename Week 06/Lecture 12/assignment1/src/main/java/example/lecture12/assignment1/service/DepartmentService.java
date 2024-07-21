package example.lecture12.assignment1.service;

import example.lecture12.assignment1.entity.Department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    Page<Department> findAll(Pageable pageable);
    Department findDepartmentById(String deptNo);
    Department save(Department department);
    Department updateDepartment(String deptNo, Department department);
    void delete(String deptNo);
}
