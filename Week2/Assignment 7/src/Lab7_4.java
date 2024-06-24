import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lab7_4 {
    public static <K, V> Map<K, V> toMapByKey(List<V> list, Function<? super V, ? extends K> keyExtractor) {
        return list.stream().collect(Collectors.toMap(keyExtractor, v -> v));
    }
}
