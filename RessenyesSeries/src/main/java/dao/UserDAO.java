package dao;

import model.Users;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final HttpClient client;

    public UserDAO(HttpClient clientHttp) {
        this.client = clientHttp;
    }

    /**
     * Get all users from the database and return them as a list of User objects
     * @return
     */
    public List<Users> getAllUsers() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app/users"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        List<Users> users = new ArrayList<>();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            JSONArray usersArray = new JSONArray(body);
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                Users u = new Users();
                u.setId(userJson.getString("_id"));
                u.setName(userJson.getString("name"));
                u.setEmail(userJson.getString("email"));
                u.setPassword(userJson.getString("password"));
                JSONArray reviewsArray = userJson.getJSONArray("reviews");
                List<String> reviews = new ArrayList<>();
                for (int j = 0; j < reviewsArray.length(); j++) {
                    reviews.add(reviewsArray.getString(j));
                }
                u.setReviews(reviews);
                users.add(u);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Insert a new user into the database
     * @param user
     */
    public void insertUser(Users user) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(user.toJson()))
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a user by email from the database
     * @param email
     * @return
     */
    public Users getUserByEmail(String email) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app/users/byEmail?email=" + email))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            JSONObject userJson = new JSONObject(body);
            Users u = new Users();
            u.setId(userJson.getString("_id"));
            u.setName(userJson.getString("name"));
            u.setEmail(userJson.getString("email"));
            u.setPassword(userJson.getString("password"));
            JSONArray reviewsArray = userJson.getJSONArray("reviews");
            List<String> reviews = new ArrayList<>();
            for (int j = 0; j < reviewsArray.length(); j++) {
                reviews.add(reviewsArray.getString(j));
            }
            u.setReviews(reviews);
            return u;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check if the user is an admin
     * @param email
     * @return
     */
    public boolean checkAdmin(String email) {
        Users u = this.getUserByEmail(email);
        if (u != null) {
            return u.getEmail().equals("admin@gmail.com");
        }
        return false;
    }

    /**
     * Check if the login is correct
     * @param email
     * @param password
     * @return
     */
    public boolean checkLogin(String email, String password) {
        Users u = this.getUserByEmail(email);
        if (u != null) {
            return u.getPassword().equals(password);
        }
        return false;
    }
}