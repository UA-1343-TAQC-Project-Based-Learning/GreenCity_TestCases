package com.greencity.utils.jdbc.entity;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class HabitsEntity {
    public static final String TABLE_NAME = "public.habit_translation";
    public static final String SELECT_ALL_ORDERED_HABITS_QUERY =
            "SELECT * FROM public.habit_translation ORDER BY id ASC";

    private Integer id;
        private String name;
        private String description;
        private String habitItem;
        private Integer languageId;
        private Integer  habitId;

    public static HabitsEntity parseRow(List<String> row) {
        HabitsEntity habits = new HabitsEntity();
        habits.setId(Integer.valueOf(row.get(0)));
        habits.setName(row.get(1));
        habits.setDescription(row.get(2));
        habits.setHabitItem(row.get(3));
        habits.setLanguageId(Integer.valueOf(row.get(4)));
        habits.setHabitId(Integer.valueOf(row.get(5)));
        return habits;
    }
    public static List<HabitsEntity> parseRows(List<List<String>> rows) {
        List<HabitsEntity> habits = new ArrayList<>();
        for (List<String> row : rows) {
            habits.add(parseRow(row));
        }
        return habits;
    }


}
