import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Lab5_3 {
    private String path;

    public Lab5_3(String path) {
        this.path = path;
    }

    public void removeDuplicate(String pathOutput, String fieldName) throws IOException {
        Set<String> uniqueKeys = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path));
        BufferedWriter bw = new BufferedWriter(new FileWriter(pathOutput))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                String keyValue = fields[4];

                if (uniqueKeys.add(keyValue)) {
                    bw.write(line);
                    bw.newLine();
                }
            }
            System.out.println("Unique records based on key field '" + fieldName + "' written to " + pathOutput);

        } catch (IOException e) {
            System.out.println("There is no file with name " + path);
        }
    }
}
