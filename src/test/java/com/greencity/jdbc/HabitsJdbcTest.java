package com.greencity.jdbc;


import com.greencity.api.testRunners.JdbsTestRunner;
import com.greencity.utils.jdbc.entity.HabitsEntity;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

@Epic("GreenCity JDBC Tests")
@Owner("Khrystyna Martynova")
@Listeners({AllureTestNg.class})
public class HabitsJdbcTest extends JdbsTestRunner {
    SoftAssert softAssert = new SoftAssert();

    @Test
    @Link(name="Link go to site", url = "https://www.greencity.cx.ua/#/greenCity")
    @Story("Retrieve all habits")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that the list of habits from the DB is not null or empty and all names are filled")
    public void testGetAllHabitsNotEmpty() {
        List<HabitsEntity> habits = habitsService.getAllHabitsOrderedById();

        softAssert.assertNotNull(habits, "The list of habits should not be null.");
        softAssert.assertFalse(habits.isEmpty(), "The list of habits should not be empty.");

        for (HabitsEntity habit : habits) {
            softAssert.assertNotNull(habit.getName(), "Habit name should not be null for ID: " + habit.getId());
            softAssert.assertNotNull(habit.getLanguageId(), "Id should not be null for ID: " + habit.getId());
            softAssert.assertNotNull(habit.getDescription(),"Description should not be null for ID: " + habit.getId());
        }
        softAssert.assertAll();
    }
    @Test
    @Link(name="Link go to site", url = "https://www.greencity.cx.ua/#/greenCity")
    @Story("Sorting check")
    @Severity(SeverityLevel.NORMAL)
    @Description("Ensures that the habits returned from the DB are sorted correctly by ID")
    public void testGetAllHabitsOrderedById() {
        List<HabitsEntity> habits = habitsService.getAllHabitsOrderedById();

        for (int i = 1; i < habits.size(); i++) {
            int prevId = habits.get(i - 1).getId();
            int currId = habits.get(i).getId();
            softAssert.assertTrue(prevId <= currId,
                    "Habits are not sorted by ID: " + prevId + " > " + currId);
        }
        softAssert.assertAll();
    }
    @Test
    @Link(name="Link go to site", url = "https://www.greencity.cx.ua/#/greenCity")
    @Story("Subset validation")
    @Severity(SeverityLevel.MINOR)
    @Description("Verifies a subset of habits (ID 1-3) have expected names")
    public void testGetSubsetOfHabits() {
        List<HabitsEntity> habits = habitsService.getAllHabitsOrderedById();
        List<Integer> selectedIds = Arrays.asList(1, 3, 5, 7, 9);

        for (HabitsEntity habit : habits) {
            if (selectedIds.contains(habit.getId())) {
                String expectedName = getExpectedNameById(habit.getId());
                Integer expectedHabitId = habit.getId();

                softAssert.assertEquals(habit.getName(), expectedName, "Habit name does not match for ID " + habit.getId());
                softAssert.assertEquals(habit.getHabitId(), expectedHabitId, "Habit habit_id does not match for ID " + habit.getId());
                softAssert.assertAll();
            }
        }
    }
    private String getExpectedNameById(Integer id) {
        switch (id) {
            case 1: return "Use a towel instead of paper towels and napkins";
            case 3: return "Use e-reader instead of paper book";
            case 5: return "Drive an electric car";
            case 7: return "Use a reusable shopping bag";
            case 9: return "Use reusable batteries";
            default: return "Unknown habit";
        }
    }
}