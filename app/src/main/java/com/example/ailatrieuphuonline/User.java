package com.example.ailatrieuphuonline;

public class User {
    private String id;
    private String username;
    private String fullname;
    private String facebook;
    private int scores;

    public User() { }

    public User(String id, String username, String fullname, String facebook, int scores ) {
        this.username = username;
        this.fullname = fullname;
        this.facebook = facebook;
        this.scores = scores;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }
}
