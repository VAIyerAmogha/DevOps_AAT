package com.habittracker;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        HabitTracker tracker = new HabitTracker();

        tracker.addHabit("Gym");

        // simulate past days
        tracker.getHabits().get("Gym").markCompleted(LocalDate.now().minusDays(2));
        tracker.getHabits().get("Gym").markCompleted(LocalDate.now().minusDays(1));
        tracker.getHabits().get("Gym").markCompleted(LocalDate.now());

        int streak = tracker.getStreak("Gym");

        System.out.println("Current streak: " + streak);
    }
}