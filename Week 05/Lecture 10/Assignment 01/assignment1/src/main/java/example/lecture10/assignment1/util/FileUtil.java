package example.lecture10.assignment1.util;

import example.lecture10.assignment1.dto.EmployeeDTO;

import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {

    public List<EmployeeDTO> parseCSVFile(MultipartFile file) throws Exception {
        SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("M/dd/yyyy");
        List<EmployeeDTO> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            reader.readNext(); // Skip the header line

            while ((nextLine = reader.readNext()) != null) {
                UUID id = UUID.randomUUID();
                String name = nextLine[1];
                String dateStr = nextLine[2];
                Date dob = INPUT_FORMAT.parse(dateStr);
                String address = nextLine[3];
                String department = nextLine[4];
                String email = nextLine[5];
                String phone = nextLine[6];

                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId(id);
                employeeDTO.setName(name);
                employeeDTO.setDateOfBirth(dob);
                employeeDTO.setAddress(address);
                employeeDTO.setDepartment(department);
                employeeDTO.setEmail(email);
                employeeDTO.setPhone(phone);

                employees.add(employeeDTO);
            }
        }
        return employees;
    }
}
