package com.example.ailatrieuphuonline;

public class User {
    private String username;
    private String email;
    private String facebooklink;

    public User() { }

    public User(String username, String email, String facebooklink) {
        this.username = username;
        this.email = email;
        this.facebooklink = facebooklink;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebooklink() {
        return facebooklink;
    }

    public void setFacebooklink(String facebooklink) {
        this.facebooklink = facebooklink;
    }
}
