package com.habittracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class StreakCalculatorTest {

    @Test
    void testContinuousStreak() {
        StreakCalculator calc = new StreakCalculator();

        int streak = calc.calculateStreak(Arrays.asList(
                LocalDate.now().minusDays(2),
                LocalDate.now().minusDays(1),
                LocalDate.now()
        ));

        assertEquals(3, streak);
    }

    @Test
    void testBrokenStreak() {
        StreakCalculator calc = new StreakCalculator();

        int streak = calc.calculateStreak(Arrays.asList(
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1)
        ));

        assertEquals(1, streak);
    }

    @Test
    void testSingleDay() {
        StreakCalculator calc = new StreakCalculator();

        int streak = calc.calculateStreak(Collections.singletonList(
                LocalDate.now()
        ));

        assertEquals(1, streak);
    }

    @Test
    void testEmptyList() {
        StreakCalculator calc = new StreakCalculator();

        int streak = calc.calculateStreak(Collections.emptyList());

        assertEquals(0, streak);
    }
}