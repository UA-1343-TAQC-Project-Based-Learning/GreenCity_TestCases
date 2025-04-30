package com.greencity.utils.jdbc.entity;


import lombok.Data;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
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
    private Integer userStatus;
    private String refreshTokenKey;
    private String profilePicture;
    private Double rating;
    private LocalDateTime lastActivityTime;
    private String firstName;
    private String userCredo;
    private String showLocation;
    private String showEcoPlace;
    private String showToDoList;
    private Integer languageId;
    private UUID uuid;
    private String phoneNumber;
    private Double eventOrganizerRating;
    private Integer userLocation;


    public static UserEntity parseRow(List<String> row) {
              DateTimeFormatter formatter2 = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .optionalStart()
                .appendFraction(ChronoField.NANO_OF_SECOND, 1, 6, true)
                .optionalEnd()
                .toFormatter();

        UserEntity user = new UserEntity();
        user.setId(Integer.valueOf(row.get(0)));
        user.setDateOfRegistration(LocalDateTime.parse(row.get(1),formatter2));
        user.setEmail(row.get(2));
        user.setEmailNotification(Boolean.valueOf(row.get(3)));
        user.setName(row.get(4));
        user.setRole(row.get(5));
        user.setUserStatus(Integer.valueOf(row.get(6)));
        user.setRefreshTokenKey(row.get(7));
        user.setProfilePicture(row.get(8));
        user.setRating(Double.valueOf(row.get(9)));
        String dateTime = row.get(10);
        if (dateTime.contains("+")) {
            dateTime = dateTime.substring(0, dateTime.indexOf("+"));
        }
        user.setLastActivityTime(LocalDateTime.parse(dateTime, formatter2));
        user.setFirstName(row.get(11));
        user.setUserCredo(row.get(12));
        user.setShowLocation(row.get(13));
        user.setShowEcoPlace(row.get(14));
        user.setShowToDoList(row.get(15));
        user.setLanguageId(Integer.valueOf(row.get(16)));
        user.setUuid(UUID.fromString(row.get(17)));
        user.setPhoneNumber(row.get(18));
        if(row.get(19)== null){
            user.setEventOrganizerRating(0.0);
        }
        else
        user.setEventOrganizerRating(Double.valueOf(row.get(19)));
        user.setUserLocation(Integer.valueOf((20)));

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



