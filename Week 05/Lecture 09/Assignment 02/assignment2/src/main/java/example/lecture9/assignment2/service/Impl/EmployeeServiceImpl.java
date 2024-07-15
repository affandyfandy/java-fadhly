package example.lecture9.assignment2.service.Impl;

import example.lecture9.assignment2.model.Employee;
import example.lecture9.assignment2.service.EmployeeService;
import example.lecture9.assignment2.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Employee findById(String theId) {
        return employeeRepository.findById(theId).orElseThrow();
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(String theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public void saveCSV(MultipartFile file) {
        SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("M/dd/yyyy");
        List<Employee> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            reader.readNext(); // Skip the header line

            while ((nextLine = reader.readNext()) != null) {
                String id = nextLine[0];
                String name = nextLine[1];
                String dateStr = nextLine[2];
                Date dob = INPUT_FORMAT.parse(dateStr);
                String address = nextLine[3];
                String department = nextLine[4];
                int salary = Integer.parseInt(nextLine[5]);

                Employee employee = new Employee(id, name, dob, address, department, salary);
                employees.add(employee);
            }

            employeeRepository.saveAll(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
