package com.udesc.calorieservice.repository;

import com.udesc.calorieservice.model.CalorieRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalorieRepository extends CrudRepository<CalorieRecord,Long> {
    List<CalorieRecord> findAll();
    List<CalorieRecord> findByGoal(String goal);
    List<CalorieRecord> findByCaloriesBetween(int minCalories, int maxCalories);
}
