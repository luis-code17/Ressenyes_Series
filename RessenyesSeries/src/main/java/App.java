import com.mongodb.client.MongoDatabase;
import dao.ReviewDAO;
import dao.UserDAO;
import dao.SeriesDAO;
import model.Reviews;
import model.Series;
import model.Users;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        MongoDatabase database = Connection.getDatabase("RessenyesSeriesDB");
        UserDAO userDAO = new UserDAO(database);
        ReviewDAO reviewDAO = new ReviewDAO(database);
        SeriesDAO seriesDAO = new SeriesDAO(database);
        Scanner sc = new Scanner(System.in);
        View view = new View(sc);
        int option = view.initialMenu();
        boolean login = false;
        while (option != 3) {
            switch (option) {
                case 1:
                    boolean isAdmin = true; // Set to false to test
                    login = true; // Set to false to test
                    while (!login) {
                        String[] loginData = view.loginUser();
                        login = userDAO.checkLogin(loginData[0], loginData[1]);
                        if (!login) {
                            System.out.println("Incorrect email or password");
                        }
                        isAdmin = userDAO.checkAdmin(loginData[0]);
                    }

                    if (isAdmin) {
                        appAdmin(userDAO, view, reviewDAO, seriesDAO);
                    } else {
                        appUser(userDAO, view);
                    }
                    break;
                case 2:
                    String[] register = view.registerUser();
                    Users users = new Users();
                    users.setName(register[0]);
                    users.setEmail(register[1]);
                    users.setPassword(register[2]);
                    userDAO.insertUser(users);
                    break;
            }
            login = false;
            option = view.initialMenu();
        }
    }

    public static void appAdmin(UserDAO userDAO, View view, ReviewDAO reviewDAO, SeriesDAO seriesDAO) {
        int option = view.mainMenuAdmin();
        int collection;
        while (option != 5) {
            switch (option) {
                case 1: // List collection
                    collection = view.selectCollection();
                    switch (collection) {
                        case 1:// Users
                            ArrayList<Users> users = (ArrayList<Users>) userDAO.getAllUsers();
                            view.printUsers(users);
                            break;
                        case 2:// Reviews
                            option = view.menuSearchReviews();
                            switch (option) {
                                case 1:// By series
                                    break;
                                case 2:// By user
                                    ArrayList<Users> usersReviews = (ArrayList<Users>) userDAO.getAllUsers();
                                    int serieBySerch= view.selectUser(usersReviews);
                                    ArrayList<Reviews> reviewsByUser = (ArrayList<Reviews>) reviewDAO.getReviewsByUserId(usersReviews.get(serieBySerch).getId());
                                    view.printReviews(reviewsByUser);
                                    break;
                                case 3:// Return
                                    System.out.println("Return");
                                    break;
                            }
                            break;
                        case 3:// Series
                            ArrayList<Series> series = (ArrayList<Series>) seriesDAO.getAllSeries();
                            view.printSeries(series);
                            break;
                        case 4:// Return
                            System.out.println("Return");
                            break;
                    }
                    break;
                case 2:// Insert document
                    collection = view.selectCollection();
                    switch (collection) {
                        case 1:// Users
                            Users users = view.insertUser();
                            System.out.println(users);
                            userDAO.insertUser(users);
                            break;
                        case 2:// Reviews
                            ArrayList<Users> usersReviews = (ArrayList<Users>) userDAO.getAllUsers();
                            ArrayList<Series> seriesReviews = (ArrayList<Series>) seriesDAO.getAllSeries();
                            Reviews reviews = view.insertReview(usersReviews, seriesReviews);
                            reviewDAO.insertReview(reviews);
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