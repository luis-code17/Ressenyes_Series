package model;

import java.util.List;
import org.json.JSONObject;



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

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("releaseDate", releaseDate);
        json.put("average_score", rating);
        json.put("reviews", reviews);
        return json.toString();
    }
}
