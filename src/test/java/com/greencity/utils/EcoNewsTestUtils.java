package com.greencity.utils;

import com.greencity.utils.jdbc.entity.EcoNewsEntity;

import java.time.OffsetDateTime;

public class EcoNewsTestUtils {
    public static EcoNewsEntity createTestNewsEntity(String title) {
        return EcoNewsEntity.builder()
                .title(title)
                .creationDate(OffsetDateTime.now())
                .authorId(1L)
                .text("Test content for " + title)
                .source("JUnitTestSource")
                .shortInfo("Test short info")
                .hidden(false)
                .build();
    }

    public static EcoNewsEntity createTestNewsEntityWithImage(String title, String imagePath) {
        EcoNewsEntity news = createTestNewsEntity(title);
        news.setImagePath(imagePath);
        return news;
    }
}
