package example.lecture12.assignment1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name = "salaries")
@IdClass(SalaryId.class)
public class Salary {
    @Id
    @Column(name = "emp_no")
    private int empNo;

    @Column
    private int salary;

    @Id
    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", nullable=false, insertable = false, updatable = false)
    @JsonIgnore
    private Employee employee;
}
