import java.util.HashMap;

public class Lab5_4 {
    public void usingClone(HashMap<Integer, String> original) {
        HashMap<Integer, String> shallowCopy = (HashMap<Integer, String>) original.clone();

        System.out.println("Original Map: " + original);
        System.out.println("New Input to Original");
        original.put(103, "Noemi");
        System.out.println("Copy Map using Clone: " + shallowCopy+"\n");
    }

    public void usingConstructor(HashMap<Integer, String> original) {
        HashMap<Integer, String> shallowCopy = new HashMap<>(original);
        
        System.out.println("Original Map: " + original);
        System.out.println("New Input to Original");
        original.put(104, "Madeline");
        System.out.println("Copy Map using Constructor: " + shallowCopy+"\n");
    }
}