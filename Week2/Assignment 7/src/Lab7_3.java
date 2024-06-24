import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

public class Lab7_3 {
    public static <T> Optional<T> maxByField(List<T> list, Comparator<? super T> comparator) {
        return list.stream().max(comparator);
    }

    public static <T> void sortByField(List<T> list, Comparator<? super T> comparator) {
        Collections.sort(list, comparator);
    }
}
