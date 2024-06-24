import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Lab8_2> employees = new ArrayList<>();
        Lab8_2Ser Lab8_2Ser = new Lab8_2Ser();
        Lab8_2Des Lab8_2Des = new Lab8_2Des();
        employees.add(new Lab8_2(1, "Alice", "HR"));
        employees.add(new Lab8_2(2, "Bob", "Engineering"));
        employees.add(new Lab8_2(3, "Charlie", "Sales"));

        String filename = "employees.txt";

        // Serialize the list of employees
        Lab8_2Ser.serializeEmployees(employees, filename);

        // Deserialize the list of employees
        List<Lab8_2> deserializedEmployees = Lab8_2Des.deserializeEmployees(filename);

        // Print deserialized employees
        for (Lab8_2 employee : deserializedEmployees) {
            System.out.println(employee.getName() + " " + employee.getDepartment());
        }
    }
}
