package dao;

import model.Reviews;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReviewDAO {
    private final HttpClient client;
    private static final String BASE_URL = "https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app";

    public ReviewDAO(HttpClient clientHttp) {
        this.client = clientHttp;
    }

    public CompletableFuture<Void> insertReview(Reviews reviews) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app/reviews"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(reviews.toJSON()))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    System.out.println("Review inserted: " + response.body());
                });
    }

    public CompletableFuture<List<Reviews>> getReviewsByDate(String minDate, String maxDate) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/reviews/byDate?minDate=" + minDate + "&maxDate=" + maxDate))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    List<Reviews> reviewsList = new ArrayList<>();
                    JSONArray reviewsArray = new JSONArray(response.body());
                    for (int i = 0; i < reviewsArray.length(); i++) {
                        JSONObject reviewJson = reviewsArray.getJSONObject(i);
                        Reviews r = new Reviews();
                        r.setId(reviewJson.getString("_id"));
                        r.setSeriesId(reviewJson.getString("series_id"));
                        r.setUserId(reviewJson.getString("user_id"));
                        r.setRating(reviewJson.getInt("score"));
                        r.setComment(reviewJson.getString("comment"));
                        r.setDate(reviewJson.getString("date"));
                        reviewsList.add(r);
                    }
                    return reviewsList;
                });
    }
}