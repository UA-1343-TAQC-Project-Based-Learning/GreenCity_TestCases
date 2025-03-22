package com.greencity.data;


import java.util.Objects;

public class LoginDto {
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private String name;
    private Boolean ownRegistrations;

    public LoginDto(Long userId, String accessToken, String refreshToken, String name, Boolean ownRegistrations) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.name = name;
        this.ownRegistrations = ownRegistrations;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getName() {
        return name;
    }

    public Boolean getOwnRegistrations() {
        return ownRegistrations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginDto loginDto)) return false;
        return Objects.equals(userId, loginDto.userId) && Objects.equals(accessToken, loginDto.accessToken) && Objects.equals(refreshToken, loginDto.refreshToken) && Objects.equals(name, loginDto.name) && Objects.equals(ownRegistrations, loginDto.ownRegistrations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accessToken, refreshToken, name, ownRegistrations);
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "userId=" + userId +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", name='" + name + '\'' +
                ", ownRegistrations=" + ownRegistrations +
                '}';
    }
}
