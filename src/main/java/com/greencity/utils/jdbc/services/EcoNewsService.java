package com.greencity.utils.jdbc.services;

import com.greencity.utils.jdbc.dao.EcoNewsDAO;
import com.greencity.utils.jdbc.entity.EcoNewsEntity;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Optional;

public class EcoNewsService {
    private final EcoNewsDAO ecoNewsDAO;

    public EcoNewsService(String url, String login, String password) {
        this.ecoNewsDAO = new EcoNewsDAO(url, login, password);
    }

    @Step("Create News using JDBC")
    public EcoNewsEntity createNews(EcoNewsEntity news) {
        ecoNewsDAO.create(news);
        return news;
    }

    @Step("Find EcoNews by ID using JDBC")
    public Optional<EcoNewsEntity> findNewsById(Long id) {
        return ecoNewsDAO.findById(id);
    }

    @Step("Find EcoNews by title using JDBC")
    public List<EcoNewsEntity> findNewsByTitle(String titlePattern) {
        return ecoNewsDAO.findByTitle(titlePattern);
    }

    @Step("Find all EcoNews using JDBC")
    public List<EcoNewsEntity> findAllNews() {
        return ecoNewsDAO.findAll();
    }

    @Step("Delete EcoNews by ID using JDBC")
    public void deleteNewsById(Long id) {
        ecoNewsDAO.deleteById(id);
    }

    @Step("Delete all EcoNews by title using JDBC")
    public void deleteAllNewsByTitle(String titlePattern) {
        ecoNewsDAO.deleteAllByTitle(titlePattern);
    }

    @Step("Update EcoNews with tags JDBC")
    public void updateNewsWithTags(EcoNewsEntity news, List<Long> tagIds) {
        try {
            ecoNewsDAO.create(news);
            tagIds.forEach(tagId -> ecoNewsDAO.addTag(news.getId(), tagId));
        } catch (Exception e) {
            throw new RuntimeException("Transaction failed", e);
        }
    }
}