package com.udesc.calorieservice.notifier;

import com.udesc.calorieservice.model.CalorieRecord;

public interface CalorieObserver {
    void update(CalorieRecord calorieRecord);

}
