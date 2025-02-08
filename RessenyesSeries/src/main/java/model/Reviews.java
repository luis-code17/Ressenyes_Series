package model;

import java.util.Date;
import org.bson.Document;


public class Reviews {
    private String id;
    private String userId;
    private String seriesId;
    private String comment;
    private int rating;
    private String date;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // toString
    @Override
    public String toString() {
        return "Reviews{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", seriesId='" + seriesId + '\'' +
                ", comment='" + comment + '\'' +
                ", rating='" + rating + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Document toDocument() {
        Document doc = new Document();
        if (id != null) {
            doc.append("_id", id);
        } else {
            doc.append("_id", new org.bson.types.ObjectId().toString());
        }
        doc.append("user_id", userId);
        doc.append("series_id", seriesId);
        doc.append("comment", comment);
        doc.append("score", rating);
        doc.append("date", date);
        return doc;
    }
}
