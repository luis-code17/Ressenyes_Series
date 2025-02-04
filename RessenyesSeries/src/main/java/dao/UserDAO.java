package dao;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Users;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final MongoCollection<Document> collection;

    public UserDAO(MongoDatabase database) {
        this.collection = database.getCollection("users");
    }

    /**
     * Get all users from the database and return them as a list of User objects
     * @return
     */
    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        for (Document document : collection.find()) {
            Users user = new Users();
            user.setId(document.getString("_id"));
            user.setName(document.getString("name"));
            user.setEmail(document.getString("email"));
            user.setPassword(document.getString("password"));
            user.setReviews(document.getList("reviews", String.class));
            users.add(user);
        }
        return users;
    }
    /**
     * Insert a new user into the database
     * @param user
     */
    public void insertUser(Users user) {
        collection.insertOne(user.toDocument());
    }

    /**
     * Get a user by email from the database
     * @param email
     * @return
     */
    public Users getUserByEmail(String email) {
        Document query = new Document("email", email);
        Document userDoc = collection.find(query).first();
        if (userDoc != null) {
            Users u = new Users();
            u.setId(userDoc.getString("_id")); // Cambiar getObjectId a getString
            u.setName(userDoc.getString("name"));
            u.setEmail(userDoc.getString("email"));
            u.setPassword(userDoc.getString("password"));
            u.setReviews(userDoc.getList("reviews", String.class));
            return u;
        }
        return null;
    }
    /**
     * Check if the user is an admin
     * @param email
     * @return
     */
    public boolean checkAdmin(String email) {
        Document query = new Document("email", email);
        Document userDoc = collection.find(query).first();
        if (userDoc != null) {
            return userDoc.getBoolean("isAdmin", false); // Directly check the isAdmin field
        }
        return false;
    }

    /**
     * Check if the login is correct
     * @param email
     * @param password
     * @return
     */
    public boolean checkLogin(String email, String password) {
        Users u = this.getUserByEmail(email);
        if (u != null) {
            return u.getPassword().equals(password);
        }
        return false;
    }


}