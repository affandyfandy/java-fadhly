import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //Lab 1 if Correct input
        Lab1 File1 = new Lab1("test1.txt");
        File1.readWriteFile("test2.txt");
        System.out.println();
        //Lab 1 if Wrong input
        Lab1 File2 = new Lab1("testing.txt");
        File2.readWriteFile("test2.txt");
        System.out.println();

        //Lab 2
        Lab2 menu1 = new Lab2();
        menu1.pickMenu(scanner);
        System.out.println();

        //Lab3
        Lab3 vowel1 = new Lab3();
        vowel1.checkVowels(scanner);

        scanner.close();
    }
}
