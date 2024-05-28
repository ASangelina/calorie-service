package com.udesc.calorieservice.visitor;

import com.udesc.calorieservice.model.CalorieRecord;

public interface CalorieVisitor {
    void visit(CalorieRecord calorieRecord);

}
