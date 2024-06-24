import java.util.*;
import java.util.function.Function;

public class Lab7_1 {
    public <T> List<T> removeDuplicates(List<T> list, Function<? super T, ?> fieldExtractor) {
        Set<Object> seen = new HashSet<>();
        List<T> uniqueList = new ArrayList<>();

        for (T item : list) {
            Object fieldValue = fieldExtractor.apply(item);
            if (seen.add(fieldValue)) {
                uniqueList.add(item);
            }
        }

        // Print unique items
        System.out.println("Unique items based on the specified field:");
        for (T uniqueItem : uniqueList) {
            System.out.println(uniqueItem);
        }

        return uniqueList;
    }
}
