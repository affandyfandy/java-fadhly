package example.lecture12.assignment1.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeSearchCriteria {
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;
    private int page = 0;
    private int size = 10;
}
