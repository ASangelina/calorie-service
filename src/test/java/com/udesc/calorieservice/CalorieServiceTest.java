package com.udesc.calorieservice;

import com.udesc.calorieservice.model.CalorieRecord;
import com.udesc.calorieservice.service.CalorieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class CalorieServiceTest {
    @Autowired
    private CalorieService calorieService;

    @Test
    void testCalculateCaloriesToGain() {
        int[] expected = {2300, 2500}; // O esperado para objetivo de engordar
        // When
        int[] result = calorieService.calculateDailyCalories(2000, "engordar");
        // Then
        assertArrayEquals(expected, result);

    }

    @Test
    void testCalculateCaloriesToLose() {

        int[] expected = {2300, 1800}; // O esperado para objetivo de emagrecer (lose)

        // When
        int[] result = calorieService.calculateDailyCalories(2800, "emagrecer");

        // Then
        assertArrayEquals(expected, result);
    }

    @Test
    void testSaveCalorie() {
        // Given
        CalorieRecord calorieRecord = new CalorieRecord();
        calorieRecord.setCalories(2500);
        calorieRecord.setGoal("engordar");

        // When
        CalorieRecord savedRecord = calorieService.saveCalorie(calorieRecord);

        // Then
        assertNotNull(savedRecord);
        assertEquals(2500, savedRecord.getCalories());
        assertEquals("engordar", savedRecord.getGoal());
    }

    @Test
    void testGetAllCalories() {
        // Given
        // Ensure there is at least one record saved
        CalorieRecord calorieRecord = new CalorieRecord();
        calorieRecord.setCalories(2000);
        calorieRecord.setGoal("emagrecer");
        calorieService.saveCalorie(calorieRecord);

        // When
        List<CalorieRecord> allRecords = calorieService.getAllCalories();

        // Then
        assertNotNull(allRecords);
        assertFalse(allRecords.isEmpty());
    }

    @Test
    void testGetCaloriesByGoal() {
        // Given
        String goal = "engordar";
        CalorieRecord calorieRecord = new CalorieRecord();
        calorieRecord.setCalories(3000);
        calorieRecord.setGoal(goal);
        calorieService.saveCalorie(calorieRecord);

        // When
        List<CalorieRecord> recordsByGoal = calorieService.getCaloriesByGoal(goal);

        // Then
        assertNotNull(recordsByGoal);
        assertFalse(recordsByGoal.isEmpty());
        recordsByGoal.forEach(record -> assertEquals(goal, record.getGoal()));
    }
}
