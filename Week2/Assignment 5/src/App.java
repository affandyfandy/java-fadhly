import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //Assignment 5.2 Retrive an element
        ArrayList<Integer> arrElement = new ArrayList<>();
        arrElement.add(5);
        arrElement.add(4);
        arrElement.add(1);
        arrElement.add(3);
        arrElement.add(2);

        Lab5_2 numElement = new Lab5_2(arrElement);
        System.out.print("Insert Index of Element: ");
        int index = scanner.nextInt();
        System.out.println(arrElement);
        numElement.checkElement(index);

        //Assignment 5.3 Remove Duplicate
        Lab5_3 uniqueElm = new Lab5_3("ImportData.csv");
        uniqueElm.removeDuplicate("OutputData.csv", "Department");

        //Assignment 5.4 Convert List to Map
        List<Lab5_5> employees = List.of(
                new Lab5_5(104, "Stesha"),
                new Lab5_5(105, "McTiernan"),
                new Lab5_5(101, "Noemi"),
                new Lab5_5(102, "Madeline"),
                new Lab5_5(103, "Joelle")
        );

        System.out.println("Original List of Employees:");
        employees.forEach(emp -> System.out.println("ID: " + emp.getEmployeeID() + ", Name: " + emp.getName()));
        System.out.println();

        Map<Integer, String> employeeMap = employees.stream()
                .collect(Collectors.toMap(Lab5_5::getEmployeeID,
                                          Lab5_5::getName, 
                                          (existing, replacement) -> existing,
                                          () -> new TreeMap<>(Comparator.naturalOrder())));

        System.out.println("Map sorted by employeeID:");
        employeeMap.forEach((key, value) -> System.out.println(key + " -> " + value));

        scanner.close();
    }
}
