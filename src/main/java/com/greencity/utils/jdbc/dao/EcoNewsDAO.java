package com.greencity.utils.jdbc.dao;

import com.greencity.utils.jdbc.entity.EcoNewsEntity;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EcoNewsDAO {
    private static final Logger log = LoggerFactory.getLogger(EcoNewsDAO.class);
    private final ManagerDAO managerDAO;

    public EcoNewsDAO(String url, String login, String password) {
        this.managerDAO = ManagerDAO.getInstance(url, login, password);
    }

    @SneakyThrows
    public void create(EcoNewsEntity ecoNews) {
        String sql = "INSERT INTO eco_news(creation_date, image_path, author_id, text, title, source, short_info, hidden) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = managerDAO.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            setEcoNewsParameters(statement, ecoNews);
            statement.executeUpdate();

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ecoNews.setId(generatedKeys.getLong(1));
            }
        } finally {
            closeQuietly(generatedKeys);
            closeQuietly(statement);
            closeQuietly(connection);
        }
    }

    @SneakyThrows
    public Optional<EcoNewsEntity> findById(Long id) {
        String sql = "SELECT * FROM eco_news WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = managerDAO.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();
            return resultSet.next()
                    ? Optional.of(mapRowToEcoNews(resultSet))
                    : Optional.empty();
        } finally {
            closeQuietly(resultSet);
            closeQuietly(statement);
            closeQuietly(connection);
        }
    }

    @SneakyThrows
    public List<EcoNewsEntity> findByTitle(String titlePattern) {
        String sql = "SELECT * FROM eco_news WHERE title LIKE ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<EcoNewsEntity> result = new ArrayList<>();

        try {
            connection = managerDAO.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + titlePattern + "%");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(mapRowToEcoNews(resultSet));
            }
            return result;
        } finally {
            closeQuietly(resultSet);
            closeQuietly(statement);
            closeQuietly(connection);
        }
    }

    @SneakyThrows
    public List<EcoNewsEntity> findAll() {
        String sql = "SELECT * FROM eco_news";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<EcoNewsEntity> result = new ArrayList<>();

        try {
            connection = managerDAO.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.add(mapRowToEcoNews(resultSet));
            }
            return result;
        } finally {
            closeQuietly(resultSet);
            closeQuietly(statement);
            closeQuietly(connection);
        }
    }

    @SneakyThrows
    public int deleteById(Long id) {
        String deleteTagsSql = "DELETE FROM eco_news_tags WHERE eco_news_id = ?";
        String deleteNewsSql = "DELETE FROM eco_news WHERE id = ?";

        Connection connection = null;
        PreparedStatement tagsStatement = null;
        PreparedStatement newsStatement = null;

        try {
            connection = managerDAO.getConnection();
            connection.setAutoCommit(false);

            tagsStatement = connection.prepareStatement(deleteTagsSql);
            tagsStatement.setLong(1, id);
            tagsStatement.executeUpdate();

            newsStatement = connection.prepareStatement(deleteNewsSql);
            newsStatement.setLong(1, id);
            int result = newsStatement.executeUpdate();

            connection.commit();
            return result;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            closeQuietly(tagsStatement);
            closeQuietly(newsStatement);
            closeQuietly(connection);
        }
    }

    @SneakyThrows
    public int deleteAllByTitle(String titleValue) {
        String deleteTagsSql = "DELETE FROM eco_news_tags WHERE eco_news_id IN (SELECT id FROM eco_news WHERE title LIKE ?)";
        String deleteNewsSql = "DELETE FROM eco_news WHERE title LIKE ?";

        Connection connection = null;
        PreparedStatement tagsStatement = null;
        PreparedStatement newsStatement = null;

        try {
            connection = managerDAO.getConnection();
            connection.setAutoCommit(false);

            tagsStatement = connection.prepareStatement(deleteTagsSql);
            tagsStatement.setString(1, "%" + titleValue + "%");
            tagsStatement.executeUpdate();

            newsStatement = connection.prepareStatement(deleteNewsSql);
            newsStatement.setString(1, "%" + titleValue + "%");
            int result = newsStatement.executeUpdate();

            connection.commit();
            return result;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            closeQuietly(tagsStatement);
            closeQuietly(newsStatement);
            closeQuietly(connection);
        }
    }

    @SneakyThrows
    public void addTag(Long ecoNewsId, Long tagId) {
        String sql = "INSERT INTO eco_news_tags(eco_news_id, tags_id) VALUES (?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = managerDAO.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setLong(1, ecoNewsId);
            statement.setLong(2, tagId);
            statement.executeUpdate();
        } finally {
            closeQuietly(statement);
            closeQuietly(connection);
        }
    }

    @SneakyThrows
    private void setEcoNewsParameters(PreparedStatement statement, EcoNewsEntity ecoNews) {
        statement.setObject(1, ecoNews.getCreationDate());
        statement.setString(2, ecoNews.getImagePath());
        statement.setLong(3, ecoNews.getAuthorId());
        statement.setString(4, ecoNews.getText());
        statement.setString(5, ecoNews.getTitle());
        statement.setString(6, ecoNews.getSource());
        statement.setString(7, ecoNews.getShortInfo());
        statement.setBoolean(8, ecoNews.isHidden());
    }

    @SneakyThrows
    private EcoNewsEntity mapRowToEcoNews(ResultSet resultSet) {
        return new EcoNewsEntity(
                resultSet.getLong("id"),
                resultSet.getObject("creation_date", OffsetDateTime.class),
                resultSet.getString("image_path"),
                resultSet.getLong("author_id"),
                resultSet.getString("text"),
                resultSet.getString("title"),
                resultSet.getString("source"),
                resultSet.getString("short_info"),
                resultSet.getBoolean("hidden")
        );
    }

    private void closeQuietly(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                log.error("Error closing resource", e);
            }
        }
        ManagerDAO.closeAllStatements();
    }
}