package com.greencity.data;

import lombok.Getter;

import java.util.ArrayList;

public class User {
    @Getter
    private String email;
    @Getter
    private String password;
    @Getter
    private String userName;
    @Getter
    private String url;

    ArrayList<User> users = new ArrayList<User>();
    ArrayList<String> emails = new ArrayList<String>();
}

