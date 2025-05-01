package com.greencity.utils.jdbc.entity;



import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentEntity {
    public static final String SELECT_ALL_COMMENT_QUERY = "SELECT * FROM comments";
    public static final String FIND_BY_USER_QUERY = "SELECT * FROM comments WHERE user_id = '%s'";

    private Integer id;
    private Integer parent_comment_id;
    private Integer user_id;
    private String article_type;
    private Integer article_id;
    private String text;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;
    private String status;

    public static CommentEntity parseRow(List<String> row) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .optionalStart()
                .appendFraction(ChronoField.NANO_OF_SECOND, 1, 6, true)
                .optionalEnd()
                .toFormatter();
        CommentEntity comment = new CommentEntity();
        comment.setId(Integer.valueOf(row.get(0)));
        if(row.get(1)== null){
            comment.setParent_comment_id(0);
        }
        else
        comment.setParent_comment_id(Integer.valueOf(row.get(1)));
        comment.setUser_id(Integer.valueOf(row.get(2)));
        comment.setArticle_type(row.get(3));
        comment.setArticle_id(Integer.valueOf(row.get(4)));
        comment.setText(row.get(5));
        comment.setCreated_date(LocalDateTime.parse(row.get(6), formatter));
        comment.setModified_date(LocalDateTime.parse(row.get(7), formatter));
        comment.setStatus(row.get(8));

        return comment;
    }

    public static List<CommentEntity> parseRows(List<List<String>> rows) {
        List<CommentEntity> comments = new ArrayList<>();
        for (List<String> row : rows) {
            comments.add(parseRow(row));
        }
        return comments;
    }
}
