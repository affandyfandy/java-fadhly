package example.lecture12.assignment1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "dept_no")
    private String deptNo;

    @Column(name = "dept_name")
    private String deptName;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<DepartmentEmployee> deptEmp;
    
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<DepartmentManager> deptManager;
}
