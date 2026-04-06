package com.habittracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitTracker habitTracker;

    @Autowired
    public HabitController(HabitTracker habitTracker) {
        this.habitTracker = habitTracker;
    }

    @GetMapping
    public Map<String, Habit> getHabits() {
        return habitTracker.getHabits();
    }

    @PostMapping
    public ResponseEntity<String> addHabit(@RequestParam String name) {
        if (habitTracker.getHabits().containsKey(name)) {
            return ResponseEntity.badRequest().body("Habit already exists.");
        }
        habitTracker.addHabit(name);
        return ResponseEntity.ok("Habit added successfully.");
    }

    @PostMapping("/{name}/complete")
    public ResponseEntity<String> markHabitDone(@PathVariable String name) {
        if (!habitTracker.getHabits().containsKey(name)) {
            return ResponseEntity.notFound().build();
        }
        habitTracker.markHabitDone(name);
        return ResponseEntity.ok("Habit marked as done today.");
    }

    @GetMapping("/{name}/streak")
    public ResponseEntity<Integer> getStreak(@PathVariable String name) {
        if (!habitTracker.getHabits().containsKey(name)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(habitTracker.getStreak(name));
    }
}
