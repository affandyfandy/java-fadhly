import java.util.List;

public class Lab6_4 {
    public long countString(List<String> strings, String startingLetter) {
        return strings.stream()
                      .filter(s -> s.startsWith(startingLetter))
                      .count();
    }
}
