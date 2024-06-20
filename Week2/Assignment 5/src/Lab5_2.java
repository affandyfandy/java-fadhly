import java.util.ArrayList;

public class Lab5_2 {
    private ArrayList<Integer> arr;

    public Lab5_2(ArrayList<Integer> arr) {
        this.arr = arr;
    }

    public void checkElement(int index) {
        try {
            if(index < arr.size()) {
                for (int i = 0; i < arr.size(); i++) {
                    if(index == i) {
                        System.out.println("Element of Index " + index + ": " + arr.get(i));
                    }
                }
            } else { 
                throw new IndexOutOfBoundsException("Invalid index " + index);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}