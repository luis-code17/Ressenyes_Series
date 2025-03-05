import dao.ReviewDAO;
import dao.UserDAO;
import dao.SeriesDAO;
import model.Reviews;
import model.Series;
import model.Users;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        HttpClient clientHttp = Connection.getHttpClient();
        UserDAO userDAO = new UserDAO(clientHttp);
        ReviewDAO reviewDAO = new ReviewDAO(clientHttp);
        SeriesDAO seriesDAO = new SeriesDAO(clientHttp);
        Scanner sc = new Scanner(System.in);
        View view = new View(sc);
        int option = view.initialMenu();
        boolean login = true;
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
                        //appUser(userDAO, view); // Not implemented
                        appAdmin(userDAO, view, reviewDAO, seriesDAO);
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
                            ArrayList<Users> users = (ArrayList<Users>) userDAO.getAllUsers().join();
                            view.printUsers(users);
                            break;
                        case 2:// Reviews
                            String[] dates = view.insertDate();
                            ArrayList<Reviews> reviews = (ArrayList<Reviews>) reviewDAO.getReviewsByDate(dates[0], dates[1]).join();
                            view.printReviews(reviews);
                            break;
                        case 3:// Series
                            option = view.menuSearchSeries();
                            switch (option) {
                                case 1:// All
                                    ArrayList<Series> seriesAll = (ArrayList<Series>) seriesDAO.getAllSeries().join();
                                    view.printSeries(seriesAll);
                                    break;
                                case 2:// By date
                                    String[] datess = view.insertDate();
                                    ArrayList<Series> seriesByDate = (ArrayList<Series>) seriesDAO.getSeriesByDate(datess[0], datess[1]).join();
                                    view.printSeries(seriesByDate);
                                    break;
                                case 3:// Return
                                    System.out.println("Return");
                                    break;
                            }
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
                            userDAO.insertUser(users).join();
                            break;
                        case 2:// Reviews
                            ArrayList<Users> usersReviews = (ArrayList<Users>) userDAO.getAllUsers().join();
                            ArrayList<Series> seriesReviews = (ArrayList<Series>) seriesDAO.getAllSeries().join();
                            Reviews reviews = view.insertReviewAdmin(usersReviews, seriesReviews);
                            reviewDAO.insertReview(reviews).join();
                            break;
                        case 3:// Series
                            Series series = view.insertSeries();
                            seriesDAO.insertSeries(series).join();
                            break;
                        case 4:// Return
                            System.out.println("Return");
                            break;
                    }
                    break;
                case 3:// Update document
                    collection = view.selectCollection();
                    switch (collection) {
                        case 1:// Users
                            System.out.println("Not implemented");
                            break;
                        case 2:// Reviews
                            System.out.println("Not implemented");
                            break;
                        case 3:// Series
                            System.out.println("Not implemented");
                            break;
                        case 4:// Return
                            System.out.println("Return");
                            break;
                    }
                    break;
                case 4:// Delete document
                    collection = view.selectCollection();
                    switch (collection) {
                        case 1:// Users
                            System.out.println("Not implemented");
                            break;
                        case 2:// Reviews
                            System.out.println("Not implemented");
                            break;
                        case 3:// Series
                            System.out.println("Not implemented");
                            break;
                        case 4:// Return
                            System.out.println("Return");
                            break;
                    }
                    break;
            }
            option = view.mainMenuAdmin();
        }
        System.out.println("Exit ");
    }


    /**
     * Falta implementar
     */
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