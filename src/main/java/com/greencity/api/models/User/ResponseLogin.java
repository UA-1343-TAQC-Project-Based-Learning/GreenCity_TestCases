package com.greencity.api.models.User;


import lombok.Data;

@Data
public class ResponseLogin {
    public ResponseLogin user;
    public String accessToken;
    public String refreshToken;
}
