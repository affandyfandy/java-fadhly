import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppManager {

    private static AppManager instance;
    private final Scanner scanner;
    private List<Employee> employees;

    private AppManager() {
        scanner = new Scanner(System.in);
        employees = new ArrayList<>();
    }

    public static AppManager getInstance() {
        if(instance == null) {
            instance = new AppManager();
        }

        return instance;
    }

    public void run() {
        System.out.println("Selamat Datang Pada Aplikasi Pegawai");
        while(true) {
            showMenu();
            System.out.print("Choose a Number: ");
            int option = scanner.nextInt();
            System.out.println("---------------------------------");
            switch(option) {
                case 0:
                    System.out.println("App is Closing");
                    System.exit(0);
                case 1:
                    importData();
                    break;
                case 2:
                    addEmployee();
                    break;
                case 3:
                    List<Employee> filtEmp = filterEmployee();

                    for(Employee emp : filtEmp ) {
                        System.out.println(emp.toCSV());
                    }
                    break;
                case 4:
                    exportFilteredEmployee();
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("====================================");
        System.out.println("0 - exit");
        System.out.println("1 - Select file, import data");
        System.out.println("2 - Add new Employee");
        System.out.println("3 - Filter by name, id, dobirth-year, department");
        System.out.println("4 - Filter and export to csv file, order by Dobirth");
    }

    private void importData() {
        System.out.print("Enter File Path: ");
        String path = scanner.nextLine();
        System.out.println("Select Method:");
        System.out.println("1 - Manual");
        System.out.println("2 - OpenCSV");
        int option = scanner.nextInt();

        switch(option) {
            case 1:
                employees = FileUtils.readFileCSVManual(path);
            case 2:
                employees = FileUtils.readFileCSVManual(path);
        }
    }

    private void addEmployee() {
        System.out.println("Input New Employee Data");
        System.out.println("=======================");
        System.out.print("New ID: ");
        String id = scanner.nextLine();
        System.out.print("New Name: ");
        String name = scanner.nextLine();
        System.out.print("New Date of Birth (d/M/yyyy): ");
        String dob = scanner.nextLine();
        System.out.print("New Address: ");
        String address = scanner.nextLine();
        System.out.print("New Department: ");
        String dept = scanner.nextLine();

        Employee emp = new Employee(id, name, DateUtils.parseDate(dob), address, dept);
        employees.add(emp);
    }

    private List<Employee> filterEmployee() {
        System.out.println("Filter Employee by: ");
        System.out.println("====================");
        System.out.println("0 - All Data");
        System.out.println("1 - ID");
        System.out.println("2 - Name");
        System.out.println("3 - Date of Birth");
        System.out.println("4 - Address");
        System.out.println("5 - Department");
        int option = scanner.nextInt();

        List<Employee> filtEmp = new ArrayList<>();

        switch(option) {
            case 0:
                filtEmp = new ArrayList<>(employees);
            case 1:
                System.out.print("Enter ID: ");
                String id = scanner.nextLine();

                filtEmp = employees.stream().filter(e -> e.getID().contains(id)).collect(Collectors.toList());
            case 2:
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();

                filtEmp = employees.stream().filter(e -> e.getName().contains(name)).collect(Collectors.toList());
            case 3:
                System.out.print("Enter Year of Birth: ");
                int year = scanner.nextInt();

                filtEmp = employees.stream().filter(e -> e.getDOB().getYear() == year).collect(Collectors.toList());
            case 4:
                System.out.print("Enter Address: ");
                String address = scanner.nextLine();

                filtEmp = employees.stream().filter(e -> e.getAddress().contains(address)).collect(Collectors.toList());;
            case 5:
                System.out.print("Enter Department: ");
                String dept = scanner.nextLine();

                filtEmp = employees.stream().filter(e -> e.getDepartment().contains(dept)).collect(Collectors.toList());

        }

        return filtEmp;
    }

    private void exportFilteredEmployee() {
        List<Employee> filtEmp = filterEmployee();

        System.out.print("Enter Path: ");
        String path = scanner.nextLine();
        FileUtils.writeEmployeesToCSV(filtEmp, path);
    }
}
