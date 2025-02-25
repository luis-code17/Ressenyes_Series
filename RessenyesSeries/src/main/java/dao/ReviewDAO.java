package dao;

import model.Reviews;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private final HttpClient client;

    public ReviewDAO(HttpClient clientHttp) {
        this.client = clientHttp;
    }

    public void insertReview(Reviews reviews) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app/reviews"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(reviews.toJSON()))
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Reviews> getReviewsBySeriesId(String seriesId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app/reviews/bySerie?series_id=" + seriesId))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        List<Reviews> reviews = new ArrayList<>();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            JSONArray reviewsArray = new JSONArray(body);
            for (int i = 0; i < reviewsArray.length(); i++) {
                JSONObject reviewJson = reviewsArray.getJSONObject(i);
                Reviews r = new Reviews();
                r.setId(reviewJson.getString("_id"));
                r.setUserId(reviewJson.getString("user_id"));
                r.setSeriesId(reviewJson.getString("series_id"));
                r.setComment(reviewJson.getString("comment"));
                r.setRating(reviewJson.getInt("score"));
                r.setDate(reviewJson.getString("date"));
                reviews.add(r);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public List<Reviews> getReviewsByUserId(String userId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app/reviews/byUser?user_id=" + userId))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        List<Reviews> reviews = new ArrayList<>();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            JSONArray reviewsArray = new JSONArray(body);
            for (int i = 0; i < reviewsArray.length(); i++) {
                JSONObject reviewJson = reviewsArray.getJSONObject(i);
                Reviews r = new Reviews();
                r.setId(reviewJson.getString("_id"));
                r.setUserId(reviewJson.getString("user_id"));
                r.setSeriesId(reviewJson.getString("series_id"));
                r.setComment(reviewJson.getString("comment"));
                r.setRating(reviewJson.getInt("score"));
                r.setDate(reviewJson.getString("date"));
                reviews.add(r);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public List<Reviews> getReviewsByDate(String minDate, String maxDate) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app/reviews/byDate?minDate=" + minDate + "&maxDate=" + maxDate))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        List<Reviews> reviews = new ArrayList<>();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            JSONArray reviewsArray = new JSONArray(body);
            for (int i = 0; i < reviewsArray.length(); i++) {
                JSONObject reviewJson = reviewsArray.getJSONObject(i);
                Reviews r = new Reviews();
                r.setId(reviewJson.getString("_id"));
                r.setUserId(reviewJson.getString("user_id"));
                r.setSeriesId(reviewJson.getString("series_id"));
                r.setComment(reviewJson.getString("comment"));
                r.setRating(reviewJson.getInt("score"));
                r.setDate(reviewJson.getString("date"));
                reviews.add(r);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}