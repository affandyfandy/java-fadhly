package example.lecture10.assignment1.dto;

import example.lecture10.assignment1.util.UUIDDeserializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
public class EmployeeDTO {
    @JsonDeserialize(using = UUIDDeserializer.class)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters and spaces")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date dateOfBirth;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Department is mandatory")
    private String department;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Phone is mandatory")
    @Pattern(regexp = "^\\+62[0-9]{9,13}$", message = "Phone number must start with +62 and contain 9 to 13 digits")
    private String phone;
}
