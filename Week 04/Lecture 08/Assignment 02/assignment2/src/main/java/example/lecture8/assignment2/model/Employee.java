package example.lecture8.assignment2.model;

public class Employee {
    private int id;
    private String name;
    private String department;
    private int age;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public String show() {
        return "Id: " + id + "\nName: " + name + "\nDepartment: " + department + "\nAge: " + age;
    }
}