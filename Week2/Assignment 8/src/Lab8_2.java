import java.io.Serializable;

public class Lab8_2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String department;

    public Lab8_2(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department; 
    }
    public void setDepartment(String department) {
        this.department = department; 
    }
}
