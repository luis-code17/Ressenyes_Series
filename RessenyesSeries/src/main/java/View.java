import java.util.Scanner;

public class View {
    private Scanner sc;

    public View(Scanner sc) {
        this.sc = sc;
    }

    public int mainMenu() {
        int option = 0;

        while (option < 1 || option > 5) {

            System.out.println(" ____________________ ");
            System.out.println("| 1. List collection |");
            System.out.println("| 2. Insert document |");
            System.out.println("| 3. Update document |");
            System.out.println("| 4. Delete document |");
            System.out.println("| 5. Exit            |");
            System.out.println(" ____________________ ");
            System.out.println("Choose an option: ");
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number between 1 and 5");
                sc.next();
            } else {
                option = sc.nextInt();
            }

        }
        return option;
    }

    public int selectCollection(){
        int option = 0;

        while (option < 1 || option > 5) {

            System.out.println(" ____________________ ");
            System.out.println("| 1. Users           |");
            System.out.println("| 2. Reviews         |");
            System.out.println("| 3. Series          |");
            System.out.println("| 4. Comments        |");
            System.out.println("| 5. Return          |");
            System.out.println(" ____________________ ");
            System.out.println("Choose an option: ");
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number between 1 and 2");
                sc.next();
            } else {
                option = sc.nextInt();
            }

        }
        return option;
    }
}
