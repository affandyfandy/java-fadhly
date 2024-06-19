import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Lab1 {
    private String path;

    public Lab1(String path) {
        this.path = path;
    }

    public void readWriteFile(String pathOutput) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path));
        BufferedWriter bw = new BufferedWriter(new FileWriter(pathOutput))) {

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            bw.write(line);
            bw.newLine();
        }
        System.out.println("\nComplete read " + path + " and write file " + pathOutput);
        } catch (IOException e) {
            System.out.println("There is no file with name " + path);
        }
    }
}
