import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.HashSet;
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

        //Assignment 5.4 Shallow Copy Hashmap Instance
        Lab5_4 copyIns = new Lab5_4();
        HashMap<Integer, String> original = new HashMap<>();
        original.put(101, "Stesha");
        original.put(102, "McTiernan");

        copyIns.usingClone(original);
        copyIns.usingConstructor(original);

        //Assignment 5.5 Convert List to Map
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

        //Assignment 5.9 Check Duplicate
        System.out.println();
        Set<Lab5_5> employeeSet = new HashSet<>(employees);
        System.out.println("Employees in HashSet:");
        employeeSet.forEach(emp -> System.out.println("ID: " + emp.getEmployeeID() + ", Name: " + emp.getName()));

        Lab5_5 duplicateEmployee = new Lab5_5(101, "Noemi Duplicate");
        employeeSet.add(duplicateEmployee);

        System.out.println();
        System.out.println("After attempting to add a duplicate employee:");
        employeeSet.forEach(emp -> System.out.println("ID: " + emp.getEmployeeID() + ", Name: " + emp.getName()));

        //Assignment 5.10 Map with Composite Key
        System.out.println("\nAssignment 5.10 Begin");
        List<Lab5_10> employees2 = List.of(
                new Lab5_10(104, "Stesha", "HR"),
                new Lab5_10(105, "McTiernan", "Finance"),
                new Lab5_10(101, "Noemi", "IT"),
                new Lab5_10(102, "Madeline", "IT"),
                new Lab5_10(103, "Joelle", "HR")
        );

        System.out.println("Original List of Employees:");
        employees2.forEach(emp -> System.out.println("ID: " + emp.getEmployeeID() + ", Name: " + emp.getName() + ", Department: " + emp.getDepartment()));
        System.out.println();

        Map<CompositeKey_10, Lab5_10> employeeMap2 = employees2.stream()
                .collect(Collectors.toMap(
                        emp -> new CompositeKey_10(emp.getDepartment(), emp.getEmployeeID()),
                        emp -> emp,
                        (existing, replacement) -> existing,
                        TreeMap::new
                ));

        System.out.println("Map with CompositeKey (department, employeeID):");
        employeeMap2.forEach((key, value) -> System.out.println(key + " -> " + value));

        Set<Lab5_10> employeeSet2 = new HashSet<>(employees2);
        System.out.println("\nEmployees in HashSet:");
        employeeSet2.forEach(emp -> System.out.println("ID: " + emp.getEmployeeID() + ", Name: " + emp.getName() + ", Department: " + emp.getDepartment()));

        Lab5_10 duplicateEmployee2 = new Lab5_10(101, "Noemi Duplicate", "IT");
        employeeSet2.add(duplicateEmployee2);

        System.out.println("\nAfter attempting to add a duplicate employee:");
        employeeSet2.forEach(emp -> System.out.println("ID: " + emp.getEmployeeID() + ", Name: " + emp.getName() + ", Department: " + emp.getDepartment()));

        scanner.close();
    }
}
