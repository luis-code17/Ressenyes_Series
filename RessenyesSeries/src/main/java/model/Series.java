package model;

import java.util.Date;
import java.util.List;
import org.bson.Document;


public class Series {
    private String id;
    private String name;
    private String releaseDate;
    private double rating;
    private List<String> reviews;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // toString
    @Override
    public String toString() {
        return "Series{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", rating='" + rating + '\'' +
                ", reviews=" + reviews +
                '}';
    }

    public Document toDocument() {
        Document document = new Document();

        if (this.id != null) {
            document.append("_id", this.id);
        }else {
            document.append("_id", new org.bson.types.ObjectId().toString());
        }
        document.append("name", name);
        document.append("release_date", releaseDate);
        document.append("average_score", rating);
        document.append("reviews", reviews);
        return document;
    }
}
