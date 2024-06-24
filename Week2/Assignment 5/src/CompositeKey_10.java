import java.util.Objects;

public class CompositeKey_10 {
    private String department;
    private int employeeID;

    public CompositeKey_10(String department, int employeeID) {
        this.department = department;
        this.employeeID = employeeID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CompositeKey_10 that = (CompositeKey_10) obj;
        return employeeID == that.employeeID && department.equals(that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, employeeID);
    }
}
