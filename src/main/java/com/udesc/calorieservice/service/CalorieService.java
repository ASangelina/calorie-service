package com.udesc.calorieservice.service;

import com.udesc.calorieservice.model.CalorieRecord;

import java.util.List;

public interface CalorieService {
    int[] calculateDailyCalories(int currentCalories, String goal);
    CalorieRecord saveCalorie(CalorieRecord calorieRecord);
    List<CalorieRecord> getAllCalories();
    List<CalorieRecord> getCaloriesByGoal(String goal);
    List<CalorieRecord> getCaloriesByCaloriesRange(int minCalories, int maxCalories);
}
