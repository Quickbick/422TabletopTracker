package com.cs422.fxproject.creatures;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CreatureDao {

    public Creature createCreature(String creatureType, String name, int health, int initiative, File image);

    void deleteCreature(Creature creature);

    void sortByInitiative();

    List<Creature> getCreatureInventory();

    void saveCreatures() throws IOException;

    void loadCreatures(File file) throws IOException, ClassNotFoundException;
}
