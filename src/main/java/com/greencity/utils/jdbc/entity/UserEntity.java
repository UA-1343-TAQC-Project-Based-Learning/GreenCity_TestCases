package com.greencity.utils.jdbc.entity;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserEntity {

    public static final String SELECT_ALL_QUERY = "SELECT * FROM users";
    public static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = '%s'";


    private Integer id;
    private LocalDateTime dateOfRegistration;
    private String email;
    private Boolean emailNotification;
    private String name;
    private String role;
    private String userStatus;
    private String refreshTokenKey;
    private String profilePicture;
    private Double rating;
    private LocalDateTime lastActivityTime;
    private String firstName;
    private String userCredo;
    private Boolean showLocation;
    private Boolean showEcoPlace;
    private Boolean showToDoList;
    private Integer languageId;
    private UUID uuid;
    private String phoneNumber;
    private Double eventOrganizerRating;
    private String userLocation;


    public static UserEntity parseRow(List<String> row) {
        UserEntity user = new UserEntity();
        user.setId(Integer.valueOf(row.get(0)));
        user.setDateOfRegistration(LocalDateTime.parse(row.get(1)));
        user.setEmail(row.get(2));
        user.setEmailNotification(Boolean.valueOf(row.get(3)));
        user.setName(row.get(4));
        user.setRole(row.get(5));
        user.setUserStatus(row.get(6));
        user.setRefreshTokenKey(row.get(7));
        user.setProfilePicture(row.get(8));
        user.setRating(Double.valueOf(row.get(9)));
        user.setLastActivityTime(LocalDateTime.parse(row.get(10)));
        user.setFirstName(row.get(11));
        user.setUserCredo(row.get(12));
        user.setShowLocation(Boolean.valueOf(row.get(13)));
        user.setShowEcoPlace(Boolean.valueOf(row.get(14)));
        user.setShowToDoList(Boolean.valueOf(row.get(15)));
        user.setLanguageId(Integer.valueOf(row.get(16)));
        user.setUuid(UUID.fromString(row.get(17)));
        user.setPhoneNumber(row.get(18));
        user.setEventOrganizerRating(Double.valueOf(row.get(19)));
        user.setUserLocation(row.get(20));

        return user;
    }

    public static List<UserEntity> parseRows(List<List<String>> rows) {
        List<UserEntity> users = new ArrayList<>();
        for (List<String> row : rows) {
            users.add(parseRow(row));
        }
        return users;
    }
}



