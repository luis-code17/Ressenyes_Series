package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Reviews;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private final MongoCollection<Document> collection;

    public ReviewDAO(MongoDatabase database) {
        this.collection = database.getCollection("reviews");
    }

    public void insertReview(Reviews reviews) {
        collection.insertOne(reviews.toDocument());
    }

    public List<Reviews> getReviewsBySeriesId(String seriesId) {
        List<Reviews> reviews = new ArrayList<>();
        for (Document document : collection.find(new Document("series_id", seriesId))) {
            Reviews review = new Reviews();
            review.setComment(document.getString("comment"));
            review.setRating(document.getInteger("score"));
            review.setUserId(document.getString("user_id"));
            review.setSeriesId(document.getString("series_id"));
            review.setDate(document.getString("date"));
            review.setId(document.getString("_id"));
            reviews.add(review);
        }
        return reviews;
    }

    public List<Reviews> getReviewsByUserId(String userId) {
        List<Reviews> reviews = new ArrayList<>();
        for (Document document : collection.find()){
            if (document.getString("user_id").equals(userId)){
                Reviews review = new Reviews();
                review.setComment(document.getString("comment"));
                review.setRating(document.getInteger("score"));
                review.setUserId(document.getString("user_id"));
                review.setSeriesId(document.getString("series_id"));
                review.setDate(document.getString("date"));
                review.setId(document.getString("_id"));
                reviews.add(review);
            }

        }

        return reviews;
    }



}
