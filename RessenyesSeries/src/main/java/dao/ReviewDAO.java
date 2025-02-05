package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import model.Reviews;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private final MongoCollection<Document> collection;
    private final MongoCollection<Document> seriesCollection;

    public ReviewDAO(MongoDatabase database) {
        this.collection = database.getCollection("reviews");
        this.seriesCollection = database.getCollection("series");
    }

    public void insertReview(Reviews reviews) {
        collection.insertOne(reviews.toDocument());
        Bson filter = Filters.eq("_id", reviews.getSeriesId());
        Document series = seriesCollection.find(filter).first();
        List<String> reviewsList = series.getList("reviews", String.class);
        reviewsList.add(reviews.getId());
        seriesCollection.updateOne(filter, new Document("$set", new Document("reviews", reviewsList)));
    }

    public List<Reviews> getReviewsBySeriesId(String seriesId) {
        List<Reviews> reviews = new ArrayList<>();
        Bson filter = Filters.eq("series_id", seriesId);
        for (Document document : collection.find(filter)) {
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
        Bson filter = Filters.eq("user_id", userId);
        for (Document document : collection.find(filter)) {
            Reviews review = new Reviews();
            review.setComment(document.getString("comment"));
            review.setRating(document.getInteger("score"));
            review.setUserId(document.getString("user_id"));
            review.setSeriesId(document.getString("series_id"));
            review.setDate(document.getString("date"));
            review.setId(document.getString("_id"));
            reviews.add(review);
        }
        if (reviews.isEmpty()) {
            System.out.println("No reviews found for this user");
        }
        return reviews;
    }

    public List<Reviews> getReviewsByDate(String minDate, String maxDate) {
        List<Reviews> reviews = new ArrayList<>();
        Bson filter = Filters.and(Filters.gte("date", minDate), Filters.lte("date", maxDate));
        for (Document document : collection.find(filter)) {
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



}
