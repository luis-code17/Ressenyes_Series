package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private MongoCollection<Document> collection;

    public UserDAO(MongoDatabase database) {
        this.collection = database.getCollection("users");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Document document = collection.find().first();
        if (document != null && document.containsKey("users")) {
            List<Document> usersArray = (List<Document>) document.get("users");
            for (Document userDoc : usersArray) {
                User user = new User();
                user.setId(userDoc.getString("_id"));
                user.setName(userDoc.getString("name"));
                user.setEmail(userDoc.getString("email"));
                user.setReviews(userDoc.getList("reviews", String.class));
                user.setPassword(userDoc.getString("password"));
                users.add(user);
            }
        }
        return users;
    }

    public void insertUser(User user) {
        Document userDoc = new Document();
        userDoc.append("name", user.getName());
        userDoc.append("email", user.getEmail());
        userDoc.append("reviews", user.getReviews());
        userDoc.append("password", user.getPassword());
        collection.insertOne(userDoc);
    }

    public User getUserByEmail(String email) {
        Document query = new Document("users.email", email);
        Document userDoc = collection.find(query).first();
        if (userDoc != null) {
            List<Document> usersArray = (List<Document>) userDoc.get("users");
            for (Document user : usersArray) {
                if (user.getString("email").equals(email)) {
                    User u = new User();
                    u.setId(user.getString("_id"));
                    u.setName(user.getString("name"));
                    u.setEmail(user.getString("email"));
                    u.setPassword(user.getString("password"));
                    return u;
                }
            }
        }
        return null;
    }
    public boolean checkAdmin(String email) {
        Document query = new Document("users.email", email);
        Document userDoc = collection.find(query).first();
        if(userDoc != null) {
            List<Document> usersArray = (List<Document>) userDoc.get("users");
            for (Document user : usersArray) {
                if (user.getString("email").equals(email)) {
                    return user.getBoolean("isAdmin");
                }
            }
        }
        return false;
    }
    public boolean checkLogin(String email, String password) {
        User u = this.getUserByEmail(email);
        if (u != null) {
            return u.getPassword().equals(password);
        }
        return false;
    }
}