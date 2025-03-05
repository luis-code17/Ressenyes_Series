package dao;

import model.Users;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserDAO {
    private final HttpClient client;
    private static final String BASE_URL = "https://m6-uf-3-api-git-main-luis-projects-e603fc68.vercel.app";
    public UserDAO(HttpClient clientHttp) {
        this.client = clientHttp;
    }

    /**
     * Get all users from the database and return them as a list of User objects
     * @return
     */
    public CompletableFuture<List<Users>> getAllUsers() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    List<Users> users = new ArrayList<>();
                    JSONArray usersArray = new JSONArray(response.body());
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
                    return users;
                });
    }


    /**
     * Insert a new user into the database
     * @param user
     */
    public CompletableFuture<Void> insertUser(Users user) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(user.toJson()))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    System.out.println("User inserted: " + response.body());
                });
    }

    /**
     * Get a user by email from the database
     * @param email
     * @return
     */
    public CompletableFuture<Users> getUserByEmail(String email) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/byEmail?email=" + email))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    // Verificar si la respuesta es vacía o contiene algún error
                    if (response.body() == null || response.body().trim().isEmpty()) {
                        throw new JSONException("La respuesta está vacía o no válida.");
                    }

                    // Verificar si el servidor devuelve un objeto JSON válido
                    JSONObject userJson = new JSONObject(response.body());

                    // Si no se encuentra el usuario, se devuelve null o un objeto vacío
                    if (userJson.isEmpty()) {
                        throw new JSONException("No se encontró el usuario.");
                    }

                    Users u = new Users();
                    u.setId(userJson.getString("_id"));
                    u.setName(userJson.getString("name"));
                    u.setEmail(userJson.getString("email"));
                    u.setPassword(userJson.getString("password"));

                    // Procesar las reseñas (si existen)
                    JSONArray reviewsArray = userJson.optJSONArray("reviews");
                    List<String> reviews = new ArrayList<>();
                    if (reviewsArray != null) {
                        for (int j = 0; j < reviewsArray.length(); j++) {
                            reviews.add(reviewsArray.getString(j));
                        }
                    }
                    u.setReviews(reviews);

                    return u;
                })
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null; // En caso de error, devolvemos null
                });
    }


    /**
     * Check if the user is an admin
     * @param email
     * @return
     */
    public boolean checkAdmin(String email) {
        Users u = this.getUserByEmail(email).join();
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
        Users u = this.getUserByEmail(email).join();
        if (u != null) {
            return u.getPassword().equals(password);
        }
        return false;
    }
}