package com.greencity.utils.jdbc.services;

import com.greencity.utils.jdbc.dao.HabitsDAO;
import com.greencity.utils.jdbc.entity.HabitsEntity;

import java.util.List;

public class HabitsService {
    private final HabitsDAO habitsDAO;

    public HabitsService(String url, String login, String password) {
        this.habitsDAO = new HabitsDAO(url, login, password);
    }

    public List<HabitsEntity> getAllHabitsOrderedById() {
        return habitsDAO.selectAllOrderedById();
    }
}
