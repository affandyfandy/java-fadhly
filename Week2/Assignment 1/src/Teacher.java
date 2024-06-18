public class Teacher {
    private String name;
    private int age;
    private Subject subject;
    
    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void assignSubject(Subject subject) {
        this.subject = subject;
    }

    public void teaching() {
        System.out.println("Teacher " + getName() + " teaching " + getSubject().getName() + " for class " + getSubject().getClassID());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Subject getSubject() {
        return subject;
    }
}
