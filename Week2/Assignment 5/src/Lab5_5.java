public class Lab5_5 {
    private int employeeID;
    private String name;
    // other fields and constructors

    public Lab5_5(int employeeID, String name) {
        this.employeeID = employeeID;
        this.name = name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    //Assignment 5.9
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Lab5_5 lab5_5 = (Lab5_5) obj;
        return employeeID == lab5_5.employeeID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(employeeID);
    }
}