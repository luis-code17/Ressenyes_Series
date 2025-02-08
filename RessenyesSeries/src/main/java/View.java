import model.Reviews;
import model.Series;
import model.Users;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class View {
    private Scanner sc;

    public View(Scanner sc) {
        this.sc = sc;
    }

    /** MENUS **/

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
        while (option < 1 || option > 4) {
            System.out.println(" ____________________ ");
            System.out.println("| 1. Users           |");
            System.out.println("| 2. Reviews         |");
            System.out.println("| 3. Series          |");
            System.out.println("| 4. Return          |");
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

    public int menuSearchReviews() {
        int option = 0;
        while (option < 1 || option > 4) {
            System.out.println(" ____________________ ");
            System.out.println("| 1. Search by serie |");
            System.out.println("| 2. Search by user  |");
            System.out.println("| 3. Search by Date  |");
            System.out.println("| 4. Exit            |");
            System.out.println(" ____________________ ");
            System.out.println("Choose an option: ");
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number between 1 and 4");
                sc.next();
            } else {
                option = sc.nextInt();
            }
        }
        return option;
    }

    public int menuSearchSeries(){
        int option = 0;
        while (option < 1 || option > 3) {
            System.out.println(" ____________________ ");
            System.out.println("| 1. Search All      |");
            System.out.println("| 2. Search by date  |");
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

    /** PRINTS **/

    public void printUsers(ArrayList<Users> users) {
        for (Users user : users) {
            System.out.println(user);
        }
    }

    public void printReviews(ArrayList<Reviews> reviews) {
        for (Reviews review : reviews) {
            System.out.println(review);
        }
    }

    public void printSeries(ArrayList<Series> series) {
        for (Series serie : series) {
            System.out.println(serie);
        }
    }

    /** INSERTS **/

    public Users insertUser() {
        Users users = new Users();
        System.out.println("Enter the name of the user: ");
        users.setName(sc.next());
        System.out.println("Enter the email of the user: ");
        users.setEmail(sc.next());
        System.out.println("Enter the password of the user: ");
        users.setPassword(sc.next());
        return users;
    }

    public Reviews insertReviewAdmin(ArrayList<Users> users, ArrayList<Series> series) {
        Reviews reviews = new Reviews();
        int idUser= selectUser(users);
        reviews.setUserId(users.get(idUser).getId());
        int idSerie= selectSerie(series);
        reviews.setSeriesId(series.get(idSerie).getId());
        System.out.println("Enter the comment: ");
        String comment = sc.nextLine();
        if (comment.isEmpty()) {
            comment = sc.nextLine();
        }
        reviews.setComment(comment);
        System.out.println("Enter the rating out of 10: ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid integer rating between 1 and 10");
            sc.next();
        }
        int rating = sc.nextInt();
        while (rating < 1 || rating > 10) {
            System.out.println("Please enter a rating between 1 and 10");
            rating = sc.nextInt();
        }
        reviews.setRating(rating);

        // Get the current date and format it to yyyy-MM-dd
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        reviews.setDate(formattedDate);
        return reviews;
    }
    public Series insertSeries() {
        Series series = new Series();
        System.out.println("Enter the name of the series: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            name = sc.nextLine();
        }
        series.setName(name);
        System.out.println("Enter the rating out of 10: ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid integer rating between 1 and 10");
            sc.next();
        }
        int rating = sc.nextInt();
        while (rating < 1 || rating > 10) {
            System.out.println("Please enter a rating between 1 and 10");
            rating = sc.nextInt();
        }
        series.setRating(rating);
        // Get the current date and format it to yyyy-MM-dd
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        series.setReleaseDate(formattedDate);

        return series;
    }

    public String[] insertDate() {
        String[] dates = new String[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 2; i++) {
            while (true) {
                if (i == 0) {
                    System.out.println("Enter the minimum date (yyyy-MM-dd): ");
                } else {
                    System.out.println("Enter the maximum date (yyyy-MM-dd): ");
                }
                String dateStr = sc.next();
                try {
                    LocalDate.parse(dateStr, formatter);
                    dates[i] = dateStr;
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                }
            }
        }
        return dates;
    }

    /** SELECTS **/

    public int selectSerie(ArrayList<Series> series) {
        int option = 0;
        for (int i = 0; i < series.size(); i++) {
            System.out.println((i + 1) + ". " + series.get(i).getName());
        }
        System.out.println("Choose a serie: ");
        while (option < 1 || option > series.size()) {
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number between 1 and " + series.size());
                sc.next();
            } else {
                option = sc.nextInt();
            }
        }
        return option-1;
    }

    public int selectUser(ArrayList<Users> users) {
        int option = 0;
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getName());
        }
        System.out.println("Choose a user: ");
        while (option < 1 || option > users.size()) {
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number between 1 and " + users.size());
                sc.next();
            } else {
                option = sc.nextInt();
            }
        }
        return option-1;
    }

    /** REGISTER AND LOGIN **/

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

}