import model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    private Scanner sc;

    public View(Scanner sc) {
        this.sc = sc;
    }

    public int initialMenu() {
        int option = 0;
        System.out.println(" _______________________________");
        System.out.println("| Welcome to the application    |");
        System.out.println(" _______________________________");
        System.out.println("| 1. Login                      |");
        System.out.println("| 2. Register                   |");
        System.out.println("| 3. Exit                       |");
        System.out.println(" _______________________________");
        System.out.println("Choose an option: ");
        while (option < 1 || option > 3) {
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number between 1 and 3");
                sc.next();
            } else {
                option = sc.nextInt();
            }
        }
        return option;
    }

    public String[] registerUser() {
        String[] user = new String[3];
        System.out.println("Enter your name: ");
        user[0] = sc.next();
        System.out.println("Enter your email: ");
        user[1] = sc.next();
        System.out.println("Enter your password: ");
        user[2] = sc.next();
        return user;
    }

    public String[] loginUser() {
        String[] user = new String[2];
        System.out.println("Enter your email: ");
        user[0] = sc.next();
        System.out.println("Enter your password: ");
        user[1] = sc.next();
        return user;
    }

    public int mainMenuAdmin() {
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

    public int selectCollection() {
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
                System.out.println("Please enter a number between 1 and 5");
                sc.next();
            } else {
                option = sc.nextInt();
            }
        }
        return option;
    }

    public int mainMenuUser() {
        int option = 0;
        while (option < 1 || option > 3) {
            System.out.println(" ____________________ ");
            System.out.println("| 1. New Review      |");
            System.out.println("| 2. Modify Review   |");
            System.out.println("| 3. Exit            |");
            System.out.println(" ____________________ ");
            System.out.println("Choose an option: ");
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number between 1 and 3");
                sc.next();
            } else {
                option = sc.nextInt();
            }
        }
        return option;
    }

    public void printUsers(ArrayList<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public User insertUser() {
        User user = new User();
        System.out.println("Enter the name of the user: ");
        user.setName(sc.next());
        System.out.println("Enter the email of the user: ");
        user.setEmail(sc.next());
        System.out.println("Enter the password of the user: ");
        user.setPassword(sc.next());
        System.out.println("Quieres añadir una review? (s/n)");
        String answer = sc.next().toLowerCase();
        if (answer.equals("s")) {
            System.out.println("Enter the review of the user: ");
            user.getReviews().add(sc.next());
        }
        return user;
    }
}