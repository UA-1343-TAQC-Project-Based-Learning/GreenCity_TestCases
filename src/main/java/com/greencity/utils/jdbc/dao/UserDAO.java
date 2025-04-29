package com.greencity.utils.jdbc.dao;

import com.greencity.utils.jdbc.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class UserDAO {

    private final String login;
    private final String password;
    private final String url;

    public UserDAO(String url, String login, String password) {
        this.login = login;
        this.password = password;
        this.url = url;
    }
    public List<UserEntity> selectAll() {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(UserEntity.SELECT_ALL_QUERY);
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();;
        }
        return UserEntity.parseRows(rows);
    }
    public UserEntity selectByEmail(String email) {
        Statement statement = ManagerDAO.getInstance(url, login, password).getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(UserEntity.FIND_BY_EMAIL_QUERY, email));
            rows = ManagerDAO.parseResultSet(resultSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ManagerDAO.closeAllStatements();;
        }
        return UserEntity.parseRow(rows.get(0));
    }
}
