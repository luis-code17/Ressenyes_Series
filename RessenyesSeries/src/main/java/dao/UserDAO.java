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

    // Obtener todos los usuarios desde el array "users"
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        // Encontrar el documento que contiene el array "users"
        Document document = collection.find().first();
        if (document != null && document.containsKey("users")) {
            // Extraer el array "users" del documento
            List<Document> usersArray = (List<Document>) document.get("users");

            // Mapear cada usuario al modelo User
            for (Document userDoc : usersArray) {
                User user = new User();
                user.setId(userDoc.getString("_id"));
                user.setName(userDoc.getString("name"));
                user.setEmail(userDoc.getString("email"));
                user.setReviews(userDoc.getList("reviews", String.class));
                users.add(user);
            }
        }

        return users;
    }
}
