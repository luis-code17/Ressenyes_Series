package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Series;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class SeriesDAO {
    private final MongoCollection<Document> collection;

    public SeriesDAO(MongoDatabase database) {
        this.collection = database.getCollection("series");
    }

    public void insertSeries(Document series) {
        collection.insertOne(series);
    }

    public List<Series> getAllSeries() {
        List<Series> series = new ArrayList<>();
        for (Document document : collection.find()) {
            Series s = new Series();
            s.setId(document.getString("_id"));
            s.setName(document.getString("name"));
            s.setRating(document.getDouble("average_score"));
            s.setReviews(document.getList("reviews", String.class));
            s.setReleaseDate(document.getString("release_date"));
            series.add(s);
        }
        return series;
    }

}
