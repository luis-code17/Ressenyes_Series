package model;

import org.json.JSONObject;



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

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userId);
        jsonObject.put("series_id", seriesId);
        jsonObject.put("comment", comment);
        jsonObject.put("score", rating);
        jsonObject.put("date", date);
        return jsonObject.toString();
    }
}
