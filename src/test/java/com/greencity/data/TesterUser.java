package com.greencity.data;



import java.util.Objects;

public class TesterUser {
    private String email;
    private String password;
    private String secretKey;
    private String username;
    private String url;

    public TesterUser(String email, String password, String secretKey, String username, String url) {
        this.email = email;
        this.password = password;
        this.secretKey = secretKey;
        this.username = username;
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TesterUser that = (TesterUser) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(secretKey, that.secretKey) && Objects.equals(username, that.username) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, secretKey, username, url);
    }

    @Override
    public String toString() {
        return "TesterUser{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", username='" + username + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
