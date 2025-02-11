// src/main/java/model/User.java
package model;

import org.bson.Document;

import java.util.List;

public class Users {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<String> reviews;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    // toString
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
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

        document.append("name", this.name);
        document.append("email", this.email);
        document.append("password", this.password);
        
        if (reviews != null) {
            document.append("reviews", reviews);
        }else {
            document.append("reviews", List.of()); // Empty list
        }
        return document;
    }
}