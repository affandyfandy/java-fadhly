import java.time.LocalDate;

public class Employee {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String department;

    public Employee(String id, String name, LocalDate dateOfBirth, String address, String department) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.department = department;
    }

    public String toCSV() {
        return id + "," + name + "," + DateUtils.formatDate(dateOfBirth) + "," + address + "," + department;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getDOB() {
        return this.dateOfBirth;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDepartment() {
        return this.department;
    }
}
