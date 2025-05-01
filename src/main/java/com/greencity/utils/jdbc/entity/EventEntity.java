package com.greencity.utils.jdbc.entity;


import lombok.Getter;
import java.sql.Timestamp;
import java.util.List;

public class EventEntity {
    public static final String SELECT_ALL_QUERY = "SELECT id, title, description, organizer_id, title_image, is_open, creation_date, events_comments_id, type FROM public.events";
    @Getter
    private Long id;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private Long organizerId;
    @Getter
    private String titleImage;
    @Getter
    private Boolean isOpen;
    @Getter
    private Timestamp creationDate;
    @Getter
    private Long eventsCommentsId;
    @Getter
    private String type;

    public EventEntity(Long id, String title, String description, Long organizerId, String titleImage,
                       Boolean isOpen, Timestamp creationDate, Long eventsCommentsId, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.organizerId = organizerId;
        this.titleImage = titleImage;
        this.isOpen = isOpen;
        this.creationDate = creationDate;
        this.eventsCommentsId = eventsCommentsId;
        this.type = type;
    }

    public static EventEntity parseRow(List<String> row) {
        return new EventEntity(
                Long.parseLong(row.get(0)),
                row.get(1),
                row.get(2),
                row.get(3) != null ? Long.parseLong(row.get(3)) : null,
                row.get(4),
                row.get(5) != null ? Boolean.parseBoolean(row.get(5)) : null,
                parseDate(row.get(6)),
                row.get(7) != null ? Long.parseLong(row.get(7)) : null,
                row.get(8)
        );
    }

    private static Timestamp parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        String dateTimeStr = dateStr + " 00:00:00";
        try {
            return Timestamp.valueOf(dateTimeStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr +
                    ". Expected format: yyyy-MM-dd");
        }
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", organizerId=" + organizerId +
                ", titleImage='" + titleImage + '\'' +
                ", isOpen=" + isOpen +
                ", creationDate=" + creationDate +
                ", eventsCommentsId=" + eventsCommentsId +
                ", type='" + type + '\'' +
                '}';
    }
}
