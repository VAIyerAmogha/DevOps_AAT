package com.habittracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Habit {
    private String name;
    private List<LocalDate> completedDates;

    public Habit(String name) {
        this.name = name;
        this.completedDates = new ArrayList<>();
    }

    public void markCompleted(LocalDate date) {
        completedDates.add(date);
    }

    public List<LocalDate> getCompletedDates() {
        return completedDates;
    }

    public String getName() {
        return name;
    }
}