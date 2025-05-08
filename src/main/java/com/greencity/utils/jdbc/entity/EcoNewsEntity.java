package com.greencity.utils.jdbc.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EcoNewsEntity {
    private Long id;
    private OffsetDateTime creationDate;
    private String imagePath;
    private Long authorId;
    private String text;
    private String title;
    private String source;
    private String shortInfo;
    private boolean hidden;
}