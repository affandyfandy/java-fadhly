package example.lecture10.assignment1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    private UUID id;
    private String name;
    private Date dateofbirth;
    private String address;
    private String department;
    private String email;
    private String phone;

    public Employee() {}
    
    public Employee(UUID id, String name, Date dateOfBirth, String address, String department, String email, String phone) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateOfBirth;
        this.address = address;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }
}