package com.greencity.api.models;

import lombok.Data;

@Data
public class SignInResponse {
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private String name;
    private Boolean ownRegistrations;
}