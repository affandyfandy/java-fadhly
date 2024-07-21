package example.lecture12.assignment1.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SalaryId implements Serializable {
    private int empNo;
    private Date fromDate;

    public SalaryId(int empNo, Date fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }
}
