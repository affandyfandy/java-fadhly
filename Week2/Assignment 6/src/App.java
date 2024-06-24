import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        //Assignment 6.2 Remove Duplicate
        List<String> listDuplicates = List.of("apple", "orange", "banana", "apple", "orange");

        Lab6_2 Lab6_2 = new Lab6_2();
        List<String> listWithoutDuplicates = Lab6_2.removeDuplicates(listDuplicates);

        System.out.println("List with duplicates: " + listDuplicates);
        System.out.println("List without duplicates: " + listWithoutDuplicates+"\n");

        //Assignment 6.3 Remove Duplicates using Stream
        Lab6_3 uniqueElm = new Lab6_3("ImportData.csv");
        uniqueElm.removeDuplicate("OutputData.csv", "Department");

        //Assignment 6.4 Count String
        List<String> strings = List.of("Red", "Green", "Blue", "Pink", "Brown");
        String startingLetter = "G";

        Lab6_4 Lab6_4 = new Lab6_4();
        long count = Lab6_4.countString(strings, startingLetter);
        
        System.out.println("\nCount String Starting with Letter "+ startingLetter + ": " + count + "\n");

        //Assignment 6.5 Sort, Find, Check
        List<Lab6_5Emp> employees = List.of(
            new Lab6_5Emp(1, "Alice", 30, 50000),
            new Lab6_5Emp(4, "Charlie", 25, 60000),
            new Lab6_5Emp(2, "David", 35, 55000),
            new Lab6_5Emp(3, "Bob", 40, 70000)
        );

        Lab6_5 Lab6_5 = new Lab6_5();

        List<Lab6_5Emp> sortedEmployees = Lab6_5.sortByName(employees);
        for(Lab6_5Emp emp : sortedEmployees) {
            System.out.println("Sorted Employees by Name: " + emp.getName() + "\n");
        }

        Optional<Lab6_5Emp> maxSalaryEmployee = Lab6_5.findEmployeeWithMaxSalary(employees);
        maxSalaryEmployee.ifPresent(employee -> 
            System.out.println("Employee with Max Salary: " + employee.getName() + " " + employee.getSalary() + "\n")
        );

        List<String> keywords = List.of("Alice", "Eve");
        boolean isMatch = Lab6_5.checkIfAnyEmployeeNameMatches(employees, keywords);
        System.out.println("Is there any match for keywords in employee names? " + isMatch + "\n");

        //Assignment 6.6 Convert List to Map
        Map<Integer, Lab6_5Emp> employeeMap = employees.stream()
                .collect(Collectors.toMap(Lab6_5Emp::getId, employee -> employee));

        employeeMap.forEach((id, employee) ->
                System.out.println("ID: " + id + ", Employee: " + employee.getName()));
    }
}
