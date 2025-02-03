import com.mongodb.client.MongoDatabase;
import dao.UserDAO;
import model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        MongoDatabase database = Connection.getDatabase("RessenyesSeriesDB");
        UserDAO userDAO = new UserDAO(database);
        Scanner sc = new Scanner(System.in);
        View view = new View(sc);
        int option = view.initialMenu();
        boolean login = false;
        while (option != 3) {
            switch (option) {
                case 1:
                    boolean isAdmin = false;
                    while (!login) {
                        String[] loginData = view.loginUser();
                        login = userDAO.checkLogin(loginData[0], loginData[1]);
                        if (!login) {
                            System.out.println("Incorrect email or password");
                        }
                        isAdmin = userDAO.checkAdmin(loginData[0]);
                    }

                    if (isAdmin) {
                        appAdmin(userDAO, view);
                    } else {
                        appUser(userDAO, view);
                    }
                    break;
                case 2:
                    String[] register = view.registerUser();
                    User user = new User();
                    user.setName(register[0]);
                    user.setEmail(register[1]);
                    user.setPassword(register[2]);
                    userDAO.insertUser(user);
                    break;
            }
            login = false;
            option = view.initialMenu();
        }
    }

    public static void appAdmin(UserDAO userDAO, View view) {
        int option = view.mainMenuAdmin();
        int collection;
        while (option != 5) {
            switch (option) {
                case 1:
                    collection = view.selectCollection();
                    switch (collection) {
                        case 1:// Users
                            ArrayList<User> users = (ArrayList<User>) userDAO.getAllUsers();
                            view.printUsers(users);
                            break;
                        case 2:// Reviews
                            break;
                        case 3:// Series
                            break;
                        case 4:// Comments
                            break;
                        case 5:// Return
                            System.out.println("Return");
                            break;
                    }
                    break;
                case 2:// Insert document
                    collection = view.selectCollection();
                    switch (collection) {
                        case 1:// Users
                            User user = view.insertUser();
                            break;
                        case 2:// Reviews
                            break;
                        case 3:// Series
                            break;
                        case 4:// Comments
                            break;
                        case 5:// Return
                            System.out.println("Return");
                            break;
                    }
                    break;
                case 3:// Update document
                    collection = view.selectCollection();
                    switch (collection) {
                        case 1:// Users
                            break;
                        case 2:// Reviews
                            break;
                        case 3:// Series
                            break;
                        case 4:// Comments
                            break;
                        case 5:// Return
                            System.out.println("Return");
                            break;
                    }
                    break;
                case 4:// Delete document
                    collection = view.selectCollection();
                    switch (collection) {
                        case 1:// Users
                            break;
                        case 2:// Reviews
                            break;
                        case 3:// Series
                            break;
                        case 4:// Comments
                            break;
                        case 5:// Return
                            System.out.println("Return");
                            break;
                    }
                    break;
            }
            option = view.mainMenuAdmin();
        }
        System.out.println("Exit ");
    }

    public static void appUser(UserDAO userDAO, View view) {
        int option = view.mainMenuUser();
        while (option != 3) {
            switch (option) {
                case 1:
                    // Implement user-specific functionality here
                    break;
                case 2:
                    // Implement user-specific functionality here
                    break;
            }
            option = view.mainMenuUser();
        }
        System.out.println("Exit ");
    }
}