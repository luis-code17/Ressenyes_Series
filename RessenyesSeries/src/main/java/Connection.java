import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Connection {
    private static final String CONNECTION_STRING = "mongodb+srv://lbacapalma:1710@tasques.2qxcs.mongodb.net/RessenyesSeriesDB?retryWrites=true&w=majority&appName=Tasques";
    private static MongoClient mongoClient = null;

    public static MongoClient getConnection() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient;
    }

    public static MongoDatabase getDatabase(String dbName) {
        return getConnection().getDatabase(dbName);
    }
}