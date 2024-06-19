import java.util.Scanner;

public class Lab3 {
    public void checkVowels(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter Sentence: ");
        String sentence = scanner.nextLine();

        try {
            if(!sentence.matches(".*[AEIOUaeiou].*")) {
                throw new Lab3Exception("The sentence don't have any vowels");
            } else {
                System.out.println("There is any vowel in this sentence");
            }
        } catch(Lab3Exception e) {
            System.out.println("Lab3Exception caught: " + e.getMessage());
        }
    }
}
