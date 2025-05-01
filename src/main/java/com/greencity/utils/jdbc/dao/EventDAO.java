package com.greencity.utils.jdbc.dao;

import com.greencity.utils.jdbc.entity.EventEntity;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventDAO {
    private final String login;
    private final String password;
    private final String url;

    public EventDAO(String url, String login, String password) {
        this.login = login;
        this.password = password;
        this.url = url;
    }

    public List<EventEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(EventEntity.SELECT_ALL_QUERY);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute select all query", e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        if (rows == null || rows.isEmpty()) {
            return Collections.emptyList();
        }

        return rows.stream()
                .map(EventEntity::parseRow)
                .collect(Collectors.toList());
    }
}
