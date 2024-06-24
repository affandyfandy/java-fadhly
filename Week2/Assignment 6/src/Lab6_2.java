import java.util.List;
import java.util.stream.Collectors;

public class Lab6_2 {
    public List<String> removeDuplicates(List<String> inputList) {
        List<String> listWithoutDuplicates = inputList.stream()
                .distinct()
                .collect(Collectors.toList());
        return listWithoutDuplicates;
    }
}
