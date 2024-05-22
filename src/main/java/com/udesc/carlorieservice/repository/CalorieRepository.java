package com.udesc.carlorieservice.repository;

import com.udesc.carlorieservice.model.CalorieRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalorieRepository extends CrudRepository<CalorieRecord,Long> {
    List<CalorieRecord> findAll();
    List<CalorieRecord> findByGoal(String goal);
    List<CalorieRecord> findByCaloriesBetween(int minCalories, int maxCalories);
}
