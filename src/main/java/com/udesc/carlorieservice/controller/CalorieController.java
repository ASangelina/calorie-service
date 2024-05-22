package com.udesc.carlorieservice.controller;

import com.udesc.carlorieservice.model.CalorieRecord;
import com.udesc.carlorieservice.service.CalorieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calories")
public class CalorieController {
    private final CalorieService calorieService;

    public CalorieController(CalorieService calorieService) {
        this.calorieService = calorieService;
    }

    @PostMapping("/calculate")
    public int[] calculateCalories(@RequestBody CalorieRecord calorieRecord) {
        return calorieService.calculateDailyCalories(calorieRecord.getCalories(), calorieRecord.getGoal());
    }

    @PostMapping("/save")
    public CalorieRecord saveCalorie(@RequestBody CalorieRecord calorieRecord) {
        return calorieService.saveCalorie(calorieRecord);
    }

    @GetMapping("/all")
    public List<CalorieRecord> getAllCalories() {
        return calorieService.getAllCalories();
    }

    @GetMapping("/goal/{goal}")
    public List<CalorieRecord> getCaloriesByGoal(@PathVariable String goal) {
        return calorieService.getCaloriesByGoal(goal);
    }

    @GetMapping("/range")
    public List<CalorieRecord> getCaloriesByCaloriesRange(@RequestParam int min, @RequestParam int max) {
        return calorieService.getCaloriesByCaloriesRange(min, max);
    }
}
