import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.Box;

public class App {
    public static void main(String[] args) throws Exception {
        //Assignment 7.1 Remove Duplicate
        List<Lab7_1Emp> people = new ArrayList<>();
        people.add(new Lab7_1Emp("Alice", 25));
        people.add(new Lab7_1Emp("Bob", 30));
        people.add(new Lab7_1Emp("Alice", 25));
        people.add(new Lab7_1Emp("Charlie", 28));
        people.add(new Lab7_1Emp("Alice", 35));

        Lab7_1 Lab7_1 = new Lab7_1();
        people = Lab7_1.removeDuplicates(people, Lab7_1Emp::getName);

        //Assignment 7.2 
        Lab7_2<Integer> intBox = new Lab7_2<>(10);
        Lab7_2<String> stringBox = new Lab7_2<>("Hello");

        System.out.println("\nLab 7.2");
        intBox.printContent();
        stringBox.printContent();

        //Assignment 7.3
        Lab7_3 Lab7_3 = new Lab7_3();
        Comparator<Lab7_1Emp> ageComparator = Comparator.comparingInt(Lab7_1Emp::getAge);
        Lab7_3.sortByField(people, ageComparator);
        System.out.println("\nSorted by age:");
        for(Lab7_1Emp person : people) {
            System.out.println(person.getName() + " " + person.getAge());
        }

        Optional<Lab7_1Emp> maxAgePerson = Lab7_3.maxByField(people, ageComparator);
        maxAgePerson.ifPresent(person -> System.out.println("Person with max age: " + person.getName() + " Age: " + person.getAge()));

        //Assignment 7.4
        Lab7_4 Lab7_4 = new Lab7_4();
        Map<String, Lab7_1Emp> nameToPersonMap = Lab7_4.toMapByKey(people, Lab7_1Emp::getName);

        System.out.println("");
        nameToPersonMap.forEach((key, value) -> System.out.println(key + " -> " + value.getAge()));

        //Assignment 7.5
        List<Lab7_1Emp> people2 = new ArrayList<>();
        people2.add(new Lab7_1Emp("Alice", 30));
        people2.add(new Lab7_1Emp("Bob", 25));
        people2.add(new Lab7_1Emp("Charlie", 35));
        people2.add(new Lab7_1Emp("David", 28));
        people2.add(new Lab7_1Emp("Eve", 32));
        people2.add(new Lab7_1Emp("Frank", 29));

        Lab7_5<Lab7_1Emp> pagingData = new Lab7_5<>(people2, 2, 1);

        System.out.println("");
        List<Lab7_1Emp> currentPageData = pagingData.getPageData();
        System.out.println("Page 1:");
        for (Lab7_1Emp person : currentPageData) {
            System.out.println(person.getName());
        }

        pagingData.setPageNumber(2);
        currentPageData = pagingData.getPageData();
        System.out.println("\nPage 2:");
        for (Lab7_1Emp person : currentPageData) {
            System.out.println(person.getName());
        }
    }
}
