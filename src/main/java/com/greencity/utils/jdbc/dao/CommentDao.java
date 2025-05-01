package com.greencity.utils.jdbc.dao;

import com.greencity.utils.jdbc.entity.CommentEntity;
import com.greencity.utils.jdbc.entity.UserEntity;

import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    private final String login;
    private final String password;
    private final String url;

    public CommentDao(String url, String login, String password) {
        this.login = login;
        this.password = password;
        this.url = url;
    }

    public List<CommentEntity> selectAllComment() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(CommentEntity.SELECT_ALL_COMMENT_QUERY);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
            ;
        }
        return CommentEntity.parseRows(rows);
    }

    public List<CommentEntity> selectByUser(Integer userId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(CommentEntity.FIND_BY_USER_QUERY, userId));
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        List<CommentEntity> comments = new ArrayList<>();
        for (List<String> row : rows) {
            comments.add(CommentEntity.parseRow(row));
        }
        return comments;
    }

    public int createNewCommentByUser(int userId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        String query = String.format(
                "INSERT INTO comments (user_id,article_type,article_id, text, created_date, modified_date, status) " +
                        "VALUES (%d,'ECO_NEWS',%d, 'This is my new comment', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');",
                userId, 4
        );
        int rowsAffected = 0;
        try {
            rowsAffected = statement.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
        return rowsAffected;
    }

    public void deleteAllCommentsByUser(int userId) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        String query = "DELETE FROM comments WHERE user_id = " + userId;
        try {
            statement.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();
        }
    }

}
