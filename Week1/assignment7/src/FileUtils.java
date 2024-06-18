import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  

import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    
    public static List<Employee> readFileCSVManual(String path) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Employee emp = new Employee(values[0], values[1], DateUtils.parseDate(values[2]), values[3], values[4]);
                employees.add(emp);
            }
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return employees;
    }

    public static List<Employee> readFileCSVOpenCSV(String path) {
        List<Employee> employees = new ArrayList<>();
        return employees;
    }

    public static void writeEmployeesToCSV(List<Employee> employess, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.append("ID,Name,DateOfBirth,Address,Department\n");
            for (Employee employee : employess) {
                writer.append(employee.toCSV()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
