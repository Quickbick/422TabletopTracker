package com.condition_manager;

import java.util.ArrayList;
import java.util.List;

public class ConditionDaoImpl implements ConditionDao {
    private List<Condition> currentConditions = new ArrayList<>();

    @Override
    public List<Condition> getCurrentConditions() {
        return currentConditions;
    }

    @Override
    public void addCurrentCondition(Condition condition) {
        this.currentConditions.add(condition);
    }


    @Override
    public void removeCurrentCondition(Condition condition) {
        this.currentConditions.remove(condition);
    }
}
