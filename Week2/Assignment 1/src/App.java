public class App {
    public static void main(String[] args) throws Exception {
        //Number 1.1 class Dog
        Dog dog1 = new Dog("White", "Milky", "Puppy");

        System.out.println("Dog Name : " + dog1.getName());
        System.out.println("Dog Color: " + dog1.getColor());
        System.out.println("Dog Breed: " + dog1.getbreed());
        System.out.print("Dog Can  : ");
        dog1.waggingTheTail();
        System.out.print("Dog Can  : ");
        dog1.barking();
        System.out.print("Dog Can  : ");
        dog1.eating();
        System.out.println();

        //Number 1.2 class Teacher and Subject
        Teacher teacher1 = new Teacher("Tam", 36);
        Subject subject1 = new Subject("Mathematics");

        subject1.assignClassID(1);
        teacher1.assignSubject(subject1);
        teacher1.teaching();
        System.out.println();

        //Number 1.3 class Teacher, Subject, and Student
        Subject subject2 = new Subject("Science");
        Student student1 = new Student("Fadhly", 14);
        Subject[] subjects = { subject1, subject2 };

        subject2.assignClassID(1);
        student1.assignSubjectStudent(subjects);
        student1.learning();
    }
}