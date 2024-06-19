import java.util.Scanner;

public class Lab2 {
    public void pickMenu() {
        Scanner scanner = new Scanner(System.in);
        String[] menu = {"Chicken", "Beef", "Pasta", "Salad", "Curry"};

        System.out.println("List Menu");
        for(int i=0; i < menu.length; i++) {
            System.out.println(i+1 + ". " + menu[i]);
        }

        try {
            System.out.print("Choose Menu: ");
            int number = scanner.nextInt();
            if (number < 1 || number > menu.length) {
                throw new Lab2Exception("Invalid menu choice: " + number);
            }
            System.out.println("You chose: " + menu[number - 1]);
        } catch (Lab2Exception e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
