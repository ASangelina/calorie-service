package com.udesc.carlorieservice.service;

import com.udesc.carlorieservice.model.CalorieRecord;
import com.udesc.carlorieservice.repository.CalorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalorieServiceImpl implements CalorieService{
    private final CalorieRepository calorieRepository;

    public CalorieServiceImpl(CalorieRepository calorieRepository) {
        this.calorieRepository = calorieRepository;
    }

    @Override
    public int[] calculateDailyCalories(int currentCalories, String goal) {
        CalorieStrategy strategy;
        if ("engordar".equalsIgnoreCase(goal)) {
            strategy = new GainCaloriesStrategy();
        } else if ("emagrecer".equalsIgnoreCase(goal)) {
            strategy = new LoseCaloriesStrategy();
        } else {
            // Retorna um array vazio como valor padrão
            return new int[]{};
        }
        return strategy.calculate(currentCalories);
    }

    @Override
    public CalorieRecord saveCalorie(CalorieRecord calorieRecord) {
        return calorieRepository.save(calorieRecord);
    }

    @Override
    public List<CalorieRecord> getAllCalories() {
        return calorieRepository.findAll();
    }

    @Override
    public List<CalorieRecord> getCaloriesByGoal(String goal) {
        return calorieRepository.findByGoal(goal);
    }

    @Override
    public List<CalorieRecord> getCaloriesByCaloriesRange(int minCalories, int maxCalories) {
        return calorieRepository.findByCaloriesBetween(minCalories, maxCalories);
    }
}
