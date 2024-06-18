public class Subject {
    private String name;
    private int classID;

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getClassID() {
        return classID;
    }

    public void assignClassID(int id) {
        this.classID = id;
    }
}
