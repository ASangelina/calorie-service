package com.udesc.carlorieservice.service;

import com.udesc.carlorieservice.model.CalorieRecord;

import java.util.List;

public interface CalorieService {
    int[] calculateDailyCalories(int currentCalories, String goal);
    CalorieRecord saveCalorie(CalorieRecord calorieRecord);
    List<CalorieRecord> getAllCalories();
    List<CalorieRecord> getCaloriesByGoal(String goal);
    List<CalorieRecord> getCaloriesByCaloriesRange(int minCalories, int maxCalories);
}
