package com.udesc.carlorieservice.service;

public class LoseCaloriesStrategy implements CalorieStrategy{
    @Override
    public int[] calculate(int currentCalories) {
        int minCalories = currentCalories - 1000;
        int maxCalories = currentCalories - 500;
        return new int[]{minCalories, maxCalories};
    }
}
