package dao;

import model.Series;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SeriesDAO {
    private final HttpClient client;
    private static final String BASE_URL = "https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app";

    public SeriesDAO(HttpClient clientHttp) {
        this.client = clientHttp;
    }

    public CompletableFuture<Void> insertSeries(Series series) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/series"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(series.toJson()))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    System.out.println("Serie insertada: " + response.body());
                });
    }


    public CompletableFuture<List<Series>>getAllSeries() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/series"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    List<Series> seriesList = new ArrayList<>();
                    JSONArray seriesArray = new JSONArray(response.body());
                    for (int i = 0; i < seriesArray.length(); i++) {
                        JSONObject seriesJson = seriesArray.getJSONObject(i);
                        Series s = new Series();
                        s.setId(seriesJson.getString("_id"));
                        s.setName(seriesJson.getString("name"));
                        s.setRating(seriesJson.getDouble("average_score"));
                        s.setReleaseDate(seriesJson.getString("release_date"));
                        JSONArray reviewsArray = seriesJson.getJSONArray("reviews");
                        List<String> reviews = new ArrayList<>();
                        for (int j = 0; j < reviewsArray.length(); j++) {
                            reviews.add(reviewsArray.getString(j));
                        }
                        s.setReviews(reviews);
                        seriesList.add(s);
                    }
                    return seriesList;
                });
    }

    public CompletableFuture<List<Series>> getSeriesByDate(String minDate, String maxDate) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/series/byDate?minDate=" + minDate + "&maxDate=" + maxDate))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    List<Series> seriesList = new ArrayList<>();
                    JSONArray seriesArray = new JSONArray(response.body());
                    for (int i = 0; i < seriesArray.length(); i++) {
                        JSONObject seriesJson = seriesArray.getJSONObject(i);
                        Series s = new Series();
                        s.setId(seriesJson.getString("_id"));
                        s.setName(seriesJson.getString("name"));
                        s.setRating(seriesJson.getDouble("average_score"));
                        s.setReleaseDate(seriesJson.getString("release_date"));
                        JSONArray reviewsArray = seriesJson.getJSONArray("reviews");
                        List<String> reviews = new ArrayList<>();
                        for (int j = 0; j < reviewsArray.length(); j++) {
                            reviews.add(reviewsArray.getString(j));
                        }
                        s.setReviews(reviews);
                        seriesList.add(s);
                    }
                    return seriesList;
                });
    }
}