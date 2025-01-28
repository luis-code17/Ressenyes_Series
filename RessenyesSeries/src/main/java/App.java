import com.mongodb.client.MongoDatabase;
import dao.UserDAO;
import model.User;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        MongoDatabase database = Connection.getDatabase("RessenyesSeriesDB");
        System.out.println("Connected to database: " + database.getName());
        UserDAO userDAO = new UserDAO(database);
        ArrayList<User> users = (ArrayList<User>) userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

    }
}