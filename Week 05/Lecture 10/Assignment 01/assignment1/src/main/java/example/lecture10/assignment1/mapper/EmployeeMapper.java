package example.lecture10.assignment1.mapper;

import example.lecture10.assignment1.dto.EmployeeDTO;
import example.lecture10.assignment1.model.Employee;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "dateofbirth", target = "dateOfBirth")
    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    @Mapping(source = "dateOfBirth", target = "dateofbirth")
    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);
}
