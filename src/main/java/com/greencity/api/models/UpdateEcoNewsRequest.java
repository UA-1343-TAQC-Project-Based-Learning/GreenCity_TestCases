package com.greencity.api.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.IOException;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEcoNewsRequest {

    @JsonSerialize(using = LongToStringSerializer.class)
    @NonNull
    private Long id;

    private List<String> tags;
    private String content;
    private String title;
    private String source;
    private String text;

    public static class LongToStringSerializer extends JsonSerializer<Long> {
        @Override
        public void serialize(Long value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeString(value.toString());
        }
    }
}
