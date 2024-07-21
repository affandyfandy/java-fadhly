package example.lecture12.assignment1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "emp_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empNo;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String gender;

    @Column(name = "hire_date")
    private Date hireDate;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<DepartmentEmployee> deptEmp;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<DepartmentManager> deptManager;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Salary> salary;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Title> title;
}
