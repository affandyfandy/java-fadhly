import java.util.List;

public class Student {
    private String name;
    private int age;
    private Subject[] subjects;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void assignSubjectStudent(Subject[] subjects) {
        this.subjects = subjects;
    }

    public void learning() {
        System.out.println("Student " + getName() + " learning:");
        for (Subject subject : subjects) {
            System.out.println("  - " + subject.getName());
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
