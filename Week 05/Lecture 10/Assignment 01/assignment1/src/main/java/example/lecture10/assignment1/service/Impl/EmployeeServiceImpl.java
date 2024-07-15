package example.lecture10.assignment1.service.Impl;

import example.lecture10.assignment1.dto.EmployeeDTO;
import example.lecture10.assignment1.mapper.EmployeeMapper;
import example.lecture10.assignment1.model.Employee;
import example.lecture10.assignment1.service.EmployeeService;
import example.lecture10.assignment1.repository.EmployeeRepository;
import example.lecture10.assignment1.util.FileUtil;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final FileUtil fileUtil;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> findEmployeeById(UUID id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::employeeToEmployeeDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findEmployeeByDepartment(String department) {
        return employeeRepository.findByDepartment(department).stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
        employee.setId(UUID.randomUUID());
        employee.setDateofbirth(employeeDTO.getDateOfBirth());
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeDTO(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDTO updateById(UUID id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.setName(employeeDTO.getName());
            employee.setAddress(employeeDTO.getAddress());
            employee.setDepartment(employeeDTO.getDepartment());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPhone(employeeDTO.getPhone());

            Employee updatedEmployee = employeeRepository.save(employee);
            return employeeMapper.employeeToEmployeeDTO(updatedEmployee);
        } else {
            throw new IllegalArgumentException("Employee not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<EmployeeDTO> saveCSV(MultipartFile file) throws Exception {
        List<EmployeeDTO> employeesDTO = fileUtil.parseCSVFile(file);
        List<Employee> employees = employeesDTO.stream()
                .map(employeeMapper::employeeDTOToEmployee)
                .collect(Collectors.toList());
        List<Employee> savedEmployees = employeeRepository.saveAll(employees);
        return savedEmployees.stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }
}