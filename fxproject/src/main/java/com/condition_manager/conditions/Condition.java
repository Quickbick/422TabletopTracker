package com.condition_manager.conditions;

import com.condition_manager.rollModifierEnum;

public abstract class Condition {
    /**
     * Duration of condition.
     */
    private int duration;

    /**
     * This function effects how attack rolls from the creature should be calculated.
     * @return Advantage, Disadvantage, or None based on condition type.
     */
    public abstract rollModifierEnum getAttacksFrom();

    /**
     * This function effects how attack rolls against the creature should be calculated.
     * @return Advantage, Disadvantage, or None based on condition type.
     */
    public abstract rollModifierEnum getAttacksAgainst();


    /**
     * This function decides if the creature should skip their turn if they have appropriate conditions.
     * @return True if should skip turn, False if otherwise.
     */
    public abstract boolean getSkipTurn();

    /**
     * @param duration Round length for condition to last.
     */
    protected Condition(int duration) {
        this.duration = duration;
    }


    /**
     * Lower duration of condition by 1 round.
     */
    public void decrementDuration() {
        --this.duration;
    }


    /**
     * Function returns duration.
     * @return Returns round duration left.
     */
    public int getDuration() {
        return duration;
    }
}
