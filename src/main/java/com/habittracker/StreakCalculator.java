package com.habittracker;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class StreakCalculator {

    public int calculateStreak(List<LocalDate> dates) {
        if (dates.isEmpty()) return 0;

        Collections.sort(dates);

        int streak = 1;

        for (int i = dates.size() - 1; i > 0; i--) {
            LocalDate current = dates.get(i);
            LocalDate previous = dates.get(i - 1);

            if (current.minusDays(1).equals(previous)) {
                streak++;
            } else {
                break;
            }
        }

        return streak;
    }
}