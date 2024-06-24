import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;

public class Lab8_2Des {
    @SuppressWarnings("unchecked")
    public static List<Lab8_2> deserializeEmployees(String filename) {
        List<Lab8_2> employees = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            employees = (List<Lab8_2>) in.readObject();
            System.out.println("Deserialized data from " + filename);
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return employees;
    }
}
