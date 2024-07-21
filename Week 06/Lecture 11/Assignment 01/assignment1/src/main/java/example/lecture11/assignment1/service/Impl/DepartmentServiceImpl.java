package example.lecture11.assignment1.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import example.lecture11.assignment1.entity.Department;
import example.lecture11.assignment1.repository.DepartmentRepository;
import example.lecture11.assignment1.service.DepartmentService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public Department findDepartmentById(String deptNo) {
        return departmentRepository.findById(deptNo).orElse(null);
    }

    @Override
    public Department save(Department department) {
        Department dept = findDepartmentById(department.getDeptNo());
        if(dept == null) {
            return departmentRepository.save(department);
        }
        return null;
    }

    @Override
    public Department updateDepartment(String deptNo, Department department) {
        Department dept = findDepartmentById(deptNo);
        if(dept != null) {
            dept.setDeptNo(department.getDeptNo());
            dept.setDeptName(department.getDeptName());

            Department updateDept = departmentRepository.save(dept);
            return updateDept;
        }
        return dept;
    }

    @Override
    public void delete(String deptNo) {
        departmentRepository.deleteById(deptNo);
    }
}
