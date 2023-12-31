# Tabletop Tracker Tests


## S1 ConditionDaoImpl Tests
* C1 Create Condition
    * Create empty conditionDao, list of condition types, and list of matching durations
    * Attempt to Create each condition with the matching duration
    * Expected: Unknown Condition or Condition Not Null
* C2 Add Current Condition
    * Create new conditionDao
    * Add a condition
    * Expected: Current Condition List Size = 1
    * Add another condition
    * Expected: Current Condition List Size = 2
* C3 Remove Current Condition
    * Create new conditionDao
    * Add a condition
    * Expected: Current Condition List Size = 1
    * Remove a Condition
    * Expected: Current Condition List Size = 0
* C4 Add And Remove Current Condition
    * Create new conditionDao
    * Add a condition
    * Expected: Current Condition List Size = 1
    * Remove a Condition
    * Expected: Current Condition List Size = 0
* C5 Decrease All Condition Durations Remove Condition When Duration Is One.
    * Create new ConditionDao
    * Add condition with duration of 1
    * Add condition with duration > 1
    * Decrease all condition durations
    * Expected: Current Condition List Size = 1
    * Expected: Higher Duration Condition Still in List

## S2 CreatureDaoImpl Tests
* C6 Create Creature
    * Create values needed for creature creation
    * Create list of all creature types
    * For each creature type create new creatureDao and add creature
    * Expected: One creature in each inventory
    * Expected: Creature data matches inputted fields
* C7 Delete Creature
    * Create new CreatureDao
    * Create creature in Dao
    * Expected: Creature Inventory Size = 1
    * Delete Creature from Dao
    * Expected: Creature Inventory Size = 0
* C9 Sort By Initiative
    * Create new CreatureDao
    * Create 3 creatures with different initiative values
    * Expected: 3 creatures in Dao inventory
    * Expected: Creatures sorted in order of creation
    * Sort Creatures by Initiative
    * Expected: Creatures sorted in initiative order
* C10 Advance Turn
    * Create new creatureDao
    * Create 3 creatures with different initiatives
    * Sort creatures by initiative
    * Advance Turn
    * Expected: Current Turn Creature = High Initiative Creature
    * Advance Turn
    * Expected: Current Turn Creature = Medium Initiative Creature
    * Advance Turn
    * Expected: Current Turn Creature = Low Initiative Creature
    * Advance Turn
    * Expected: Current Turn Creature = High Initiative Creature
* C11 Advance Turn No Creatures
    * Creature new creatureDao
    * Advance turn
    * Expected: Current Turn Creatures is Empty
* C12 Save Creatures
    * Create new creatureDao
    * Create 3 creatures
    * Create empty file
    * Save to file
    * Expected: File length > 0
* C13 Load Creatures
    * Create two CreatureDao
    * Create new file slot
    * Save Dao 1 to file
    * Load file into Dao 2
    * Expected: Creature inventory of both Dao are equal
* C14 Get Round Number
    * Create new creature Dao
    * Expected: Round number = 0

## S3 Creature Tests
* C15 Add Health to Full
    * Create new creature
    * Set Creature health to 20
    * Add 15 Health
    * Expected: Creature Health = Full Health
* C16 Add Health Not Full
    * Create new creature
    * Set Creature health to 20
    * Add 3 health
    * Expected: Health = 23
* C17 Remove Health With Auto Crits
    * Create a Creature
    * Create a ConditionDao with crit conditions active
    * Spy creature to use crit conditions
    * Remove 10 health from creature
    * Expected: Health = 5
* C18 Remove Health With More Bonus Health
    * Create new creature
    * Set bonus health to 15
    * remove 10 health
    * Expected: Health = 25
    * Expected: Bonus Health = 5
* C19 Remove HeRalth Kill
    * Create new creature
    * Remove 30 health with crit
    * Expected: Health = 0
    * Expected: Conditions contains Unconscious
* C20 Remove Health Just Paralyzed
    * Create Creature
    * Create ConditionDao with Paralyzed
    * Spy creature to use special conditionDao
    * Remove 10 health with no crit
    * Expected: Health = 5
* C21 Remove Health Just Incapacitated
    * Create Creature
    * Create ConditionDao with Incapacitated
    * Spy creature to use special conditionDao
    * Remove 10 health with no crit
    * Expected: Health = 5
* C22 Remove Health Just Unconscious
    * Create Creature
    * Create ConditionDao with Unconscious
    * Spy creature to use special conditionDao
    * Remove 10 health with no crit
    * Expected: Health = 5
* C23 Add Bonus Health
    * Create Creature
    * Add 5 Bonus Health
    * Expected: Bonus Health = 5
* C24 Add Bonus Health No Change
    * Create Creature
    * Set Bonus Health to 5
    * Add 3 Bonus Health
    * Expected: Bonus Health  = 5
* C25 Add Condition
    * Create New Creature
    * Add Condition
    * Expected: Condition is there
* C26 Add Condition More Than Once
    * Create Creature
    * Add Condition
    * Add Same Condition Again
    * Expected: Current Conditions Size = 1
* C27 Remove Condition
    * Create Creature
    * Add Condition to Creature
    * Get Initial Condition Count
    * Remove Condition
    * Get After Condition Count
    * Expected: Condition Count = Initial Count - 1
* C28 Decrement Conditions
    * Create New Creature
    * Add Condition with Duration 2
    * Add Condition with Duration 3
    * Collect Condition Durations
    * Decrement Conditions
    * Collect New Condition Durations
    * Expected: Each New Condition Duration = Condition Duration - 1
* C29 Get Name
    * Create Creature with Specific Name
    * Get Name
    * Expected: Name = Specific Name
* C30 Get Image
    * Create Creature with Specific Image
    * Get Image
    * Expected: Image = Specific Image
* C31 Get Max Health
    * Create Creature with Specific Health
    * Get Max Health
    * Expected: Max Health = Specific Max Health
* C32 Get Current Health
    * Create Creature
    * Set Health to specific non-max value
    * Get Current Health
    * Expected: Current Health = Specific Value
* C33 Get Bonus Health
    * Create Creature
    * Set Creature Bonus Health to value
    * Get Bonus Health
    * Expected: Bonus Health = Value
* C34 Get Initiative
    * Create Creature with Specific Initiative
    * Get Initiative
    * Expected: Initiative = Specific Initiative
* C35 Get Current Conditions
    * Create Creature
    * Add 2 Unique Conditions
    * Expected: Current Condition Count  = 2
    * Expected: Current Conditions Contains Condition 1
    * Expected: Current Conditions Contains Condition 2

## S4 Pairwise Integration Test 1
* C36 AdvanceTurn - Empty groupedByTurnCreatures
  * Do not add any creatures to creatureDao.
  * Call creatureDao.advanceTurn().
  * Expected: getCurrentTurnCreatures() should be empty.
  * Expected: getRoundNumber() should be 0.
* C37 AdvanceTurn - Single Group with Condition
  * Add a single creature with a condition to creatureDao.
  * Call creatureDao.advanceTurn() twice to simulate advancing to a new round.
  * Expected: Condition duration on the creature should be decremented.
  * Expected: getRoundNumber() should increment.
  * Expected: The single group remains as the current turn creatures.
* C38 AdvancedTurn - Multiple Groups, Middle Group Without Condition
  * Add multiple creatures to form at least three groups in creatureDao.
  * Set the current turn to a middle group.
  * Call creatureDao.advanceTurn().
  * Expected: getCurrentTurnCreatures() should update to the next group.
  * Expected: getRoundNumber() should not increment.
  * Expected: No conditions are decremented as no creature has conditions.
* C39 AdvancedTurn - Multiple Groups, Last Group With Condition
  * Add multiple creatures to form at least two groups, with the last group having conditions.
  * Set the current turn to the last group.
  * Call creatureDao.advanceTurn().
  * Expected: getCurrentTurnCreatures() should wrap around to the first group.
  * Expected: getRoundNumber() should increment.
  * Expected: Conditions on creatures in the last group should be decremented.

## S5 Pairwise Integration Test 2
* C40 SortByInitiative - No Groups
  * Create new CreatureDao
  * Add three creatures with different types
  * SortByInitiative
  * Expected: Each list in groupedTurnCreatures has a length = 1
* C41 SortByInitiative - Groups
  * Create new CreatureDao
  * Add three ally creatures
  * SortByInitiative
  * Expected: The list in groupedTurnCreatures has length = 3

## S6 Pairwise Integration Test 3
* C42 RemoveHealth - Add Condition
  * Create new creature
  * RemoveHealth for more than creature max health
  * Expected: CurrentConditions contains unconscious condition

## S7 Pairwise Integration Test 4
* C43 Add Condition - Actually Add
  * Create new creature
  * Add a condition to creature
  * Expected: condition was added and is in currentConditions list

## S8 Neighborhood Integration Test
* C44 Test Create Ally Creature
  * Create new ally creature from CreatureDao
  * Expected: creatureDao inventory size = 1
  * Expected: creature created is an AllyCreature
  * Expected: creature values match inputs
* C45 Test Create Neutral Creature
  * Create new neutral creature from CreatureDao
  * Expected: creatureDao inventory size = 1
  * Expected: creature created is an NeutralCreature
  * Expected: creature values match inputs
* C46 Test Create Enemy Creature
  * Create new enemy creature from CreatureDao
  * Expected: creatureDao inventory size = 1
  * Expected: creature created is an EnemyCreature
  * Expected: creature values match inputs

## S9 System Tests
* C47 Test Add Creature
  * Create new creature using the "Add Creature" dialog.
  * Expected: CreatureDao inventory size should increase to 1.
  * Assert the creature's name is "Test Creature".
  * Assert the creature's max health is 100.
  * Assert the creature's initiative is 10.

* C48 Test Add and Delete Creature
  * Add a new creature using the "Add Creature" dialog.
  * Verify the creature is added (inventory size is 1).
  * Assert the creature's name is "Test Creature".
  * Assert the creature's max health is 100.
  * Assert the creature's initiative is 10.
  * Delete the added creature.
  * Confirm the deletion if required.
  * Expected: CreatureDao inventory size should return to 0.

* C49 Test Create Multiple Creatures
  * Add multiple creatures (at least 4) of different types (Ally, Neutral, Enemy) with random attributes.
  * For each addition, check the incremented size of CreatureDao inventory.
  * Expected: CreatureDao inventory size should match the number of creatures added.

* C50 Test Advance Turn with Multiple Creatures
  * Populate CreatureDao inventory with multiple creatures (at least 4) of different types.
  * Use the "Next Turn" button to advance turns.
  * Advance turns multiple times (e.g., 10 times).
  * Expected: The round number or turn order should update appropriately with each turn advancement.
  * Assert that the round number changes after advancing turns.

* C51 Test Add Creature with Conditions
  * Add a new creature.
  * Use the "Add Condition" button to add a condition to the creature.
  * Submit the condition details.
  * Expected: The specified condition should be successfully added to the creature.
  * Assert the number of conditions on the creature is 1.
  * Assert the duration of the added condition is 20.

* C52 Test Add Creature with Multiple Conditions
  * Add a new creature.
  * Repeatedly add different conditions to the creature.
  * Expected: All specified conditions should be successfully added to the creature.
  * Assert the number of conditions on the creature is equal to the number of added conditions (6).
  * Assert the duration of each added condition is 20.

* C53 Test Damage Creature without Critical Hit
  * Add a new creature.
  * Use the "Damage" button to apply damage to the creature.
  * Enter the damage amount without marking it as a critical hit.
  * Expected: The creature's health is reduced by the specified damage amount.
  * Assert the creature's health is reduced to 50.

* C54 Test Damage Creature with Critical Hit
  * Add a new creature.
  * Apply damage to the creature and mark it as a critical hit.
  * Expected: The creature's health is reduced accordingly, considering the critical hit.
  * Assert the creature's health is reduced to 0 (100 - 50*2).

* C55 Test Damage and Heal Creature
  * Add a new creature.
  * Apply damage to the creature.
  * Heal the creature.
  * Expected: The creature's health reflects the damage and subsequent healing.
  * Assert the creature's health after healing is 30.

* C56 Test Damage Creature with Bonus Health and Heal
  * Add a new creature.
  * Apply bonus health to the creature.
  * Damage the creature.
  * Heal the creature.
  * Expected: The creature's health should reflect the combined effect of bonus health, damage, and healing.
  * Assert the creature's health after healing is 100.
  * Assert healing does not apply to bonus health.
