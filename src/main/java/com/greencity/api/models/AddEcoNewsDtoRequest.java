package com.greencity.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddEcoNewsDtoRequest {
    private String title;
    private String text;
    private List<String> tags;
    private String source;
    private String shortInfo;
}