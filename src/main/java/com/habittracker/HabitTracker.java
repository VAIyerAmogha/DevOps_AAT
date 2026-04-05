package com.habittracker;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HabitTracker {
    private Map<String, Habit> habits = new HashMap<>();
    private StreakCalculator calculator = new StreakCalculator();

    public void addHabit(String name) {
        habits.put(name, new Habit(name));
    }

    public void markHabitDone(String name) {
        Habit habit = habits.get(name);
        if (habit != null) {
            habit.markCompleted(LocalDate.now());
        }
    }

    public int getStreak(String name) {
        Habit habit = habits.get(name);
        if (habit != null) {
            return calculator.calculateStreak(habit.getCompletedDates());
        }
        return 0;
    }

    public Map<String, Habit> getHabits() {
        return habits;
    }
}