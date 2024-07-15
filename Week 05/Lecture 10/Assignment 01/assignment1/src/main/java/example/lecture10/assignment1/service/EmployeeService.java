package example.lecture10.assignment1.service;

import example.lecture10.assignment1.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {
    List<EmployeeDTO> findAll();

    Optional<EmployeeDTO> findEmployeeById(UUID id);

    List<EmployeeDTO> findEmployeeByDepartment(String department);

    EmployeeDTO save(EmployeeDTO employeeDTO);

    EmployeeDTO updateById(UUID id, EmployeeDTO employeeDTO);

    void deleteById(UUID id);

    List<EmployeeDTO> saveCSV(MultipartFile file) throws Exception;
}
