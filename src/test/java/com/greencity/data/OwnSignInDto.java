package com.greencity.data;

import java.util.Objects;

public class OwnSignInDto {
    private String email;
    private String password;
    private String captchaToken;

    public OwnSignInDto(String email, String password, String captchaToken) {
        this.email = email;
        this.password = password;
        this.captchaToken = captchaToken;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCaptchaToken() {
        return captchaToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnSignInDto that = (OwnSignInDto) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(captchaToken, that.captchaToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, captchaToken);
    }

    @Override
    public String toString() {
        return "OwnSignInDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", captchaToken='" + captchaToken + '\'' +
                '}';
    }
}