package com.greencity.api.models.User;


import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseUser {
    private String name;
    private String surname;
    private String userName;
    private String aboutYourself;
    private int avatarId;
    private ArrayList<ResponseExpertise> expertises;
    private String role;
    private String phoneNumber;
    private String email;
}
