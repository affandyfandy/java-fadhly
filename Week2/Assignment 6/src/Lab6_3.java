import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lab6_3 {
    private String path;

    public Lab6_3(String path) {
        this.path = path;
    }

    public void removeDuplicate(String pathOutput, String fieldName) throws IOException {
        Set<String> uniqueKeys = new HashSet<>();

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            List<String> uniqueLines = lines
                    .filter(line -> {
                        String[] fields = line.split(",");
                        String keyValue = fields[4];
                        return uniqueKeys.add(keyValue);
                    })
                    .collect(Collectors.toList());

            Files.write(Paths.get(pathOutput), uniqueLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            
            System.out.println("Unique records based on key field '" + fieldName + "' written to " + pathOutput);
        } catch (IOException e) {
            System.out.println("There is no file with name " + path);
            throw e; 
        }
    }
}
