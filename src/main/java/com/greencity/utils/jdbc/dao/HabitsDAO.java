package com.greencity.utils.jdbc.dao;

import com.greencity.utils.jdbc.entity.HabitsEntity;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class HabitsDAO {
    private final String url;
    private final String login;
    private final String password;

    public HabitsDAO(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public List<HabitsEntity> selectAllOrderedById() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;

        try {
            ResultSet resultSet = statement.executeQuery(HabitsEntity.SELECT_ALL_ORDERED_HABITS_QUERY);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch habits", e);
        } finally {
            ManagerDAO.closeAllStatements();
        }

        return HabitsEntity.parseRows(rows);
    }
}
