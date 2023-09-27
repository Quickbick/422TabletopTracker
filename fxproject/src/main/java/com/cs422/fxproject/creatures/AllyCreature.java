package com.cs422.fxproject.creatures;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class AllyCreature extends Creature{

    /**
     * Creates a new Creature with image, name, health, and initiative.
     *
     * @param image          Image of Creature
     * @param name           Name of Creature
     * @param maxHealth      Max Health of Creature
     * @param initiative     Initiative Number
     */
    AllyCreature(Image image, String name, int maxHealth, int initiative) {
        super(image, name, maxHealth, initiative);
    }

    /**
     * Border color for Ally creatures.
     */
    public static Color border = Color.GREEN;
}
