package example.lecture12.assignment1.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DeptManagerId implements Serializable {
    private int empNo;
    private String deptNo;

    public DeptManagerId(int empNo, String deptNo) {
        this.empNo = empNo;
        this.deptNo = deptNo;
    }
}
