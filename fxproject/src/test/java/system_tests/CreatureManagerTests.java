package system_tests;

import com.creatures.CreatureDao;
import com.creatures.CreatureManagerApp;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(ApplicationExtension.class)
class CreatureManagerAppTest extends FxRobot {
    CreatureManagerApp creatureManagerApp;

    @Start
    private void start(Stage stage) {
        creatureManagerApp = new CreatureManagerApp();
        creatureManagerApp.start(stage);
    }

    private CreatureDao getCreatureDaoViaReflection(CreatureManagerApp app) throws NoSuchFieldException, IllegalAccessException {
        Field creatureDaoField = CreatureManagerApp.class.getDeclaredField("creatureDao");
        creatureDaoField.setAccessible(true);
        return (CreatureDao) creatureDaoField.get(app);
    }

    @Test
    void testAddCreature() throws NoSuchFieldException, IllegalAccessException {
        // Click on "Add Creature" button to open the dialog
        clickOn("#addCreatureButton");

        // Now interact with the dialog's contents
        clickOn("#nameTextField").write("Test Creature");
        clickOn("#healthTextField").write("100");
        clickOn("#initiativeTextField").write("10");
        // Handle other fields as needed

        // Submit the dialog to add the creature
        clickOn("#confirmAddCreatureButton");

        CreatureDao creatureDao =  getCreatureDaoViaReflection(creatureManagerApp);
        assertEquals(creatureDao.getCreatureInventory().size(), 1);
    }

    @Test
    void testAddAndDeleteCreature() throws NoSuchFieldException, IllegalAccessException {
        // Open the dialog and add a creature
        clickOn("#addCreatureButton");

        clickOn("#nameTextField").write("Test Creature");
        clickOn("#healthTextField").write("100");
        clickOn("#initiativeTextField").write("10");
        clickOn("#confirmAddCreatureButton");

        // Retrieve the CreatureDao to check the state after addition
        CreatureDao creatureDao = getCreatureDaoViaReflection(creatureManagerApp);
        assertEquals(creatureDao.getCreatureInventory().size(), 1, "One creature should be added");

        // Simulate deletion of the creature
        // Assuming the delete button for each creature includes the creature's name in its ID
        clickOn("#deleteButton_TestCreature");

        // Confirm deletion if required
        // If your application shows a confirmation dialog, handle it here
        clickOn("#confirmDeleteYesButton"); // Replace with the actual ID of the confirmation button

        // Check the state after deletion
        assertEquals(creatureDao.getCreatureInventory().size(), 0, "Creature should be deleted");
    }

    @Test
    void testCreateMultipleCreatures() throws NoSuchFieldException, IllegalAccessException {
        Random random = new Random();

        String[] creatureTypes = {"ALLY", "NEUTRAL", "ENEMY"};
        for (int i = 0; i < 7; i++) {
            // Click on "Add Creature" button to open the dialog
            clickOn("#addCreatureButton");

            // Now interact with the dialog's contents
            clickOn("#nameTextField").write("Creature " + (i + 1));

            // Generate random initiative between 0 and 99
            int initiative = random.nextInt(100);
            clickOn("#initiativeTextField").write(Integer.toString(initiative));

            // Generate random health between 1 and 200 (rounded to nearest 5)
            int health = (random.nextInt(40) + 1) * 5;
            clickOn("#healthTextField").write(Integer.toString(health));

            // Set a random creatureType
            String creatureType = creatureTypes[random.nextInt(creatureTypes.length)];
            clickOn("#creatureTypeChoiceBox").clickOn(creatureType);
            clickOn("#confirmAddCreatureButton");

            // Retrieve the CreatureDao to check the state after addition
            CreatureDao creatureDao = getCreatureDaoViaReflection(creatureManagerApp);
            assertEquals(creatureDao.getCreatureInventory().size(), i+1);
          }
    }

    @Test
    void testAddCreatureWithConditions() throws NoSuchFieldException, IllegalAccessException {
        // Click on "Add Creature" button to open the dialog
        clickOn("#addCreatureButton");

        // Now interact with the dialog's contents
        clickOn("#nameTextField").write("Test Creature");
        clickOn("#healthTextField").write("100");
        clickOn("#initiativeTextField").write("10");
        // Handle other fields as needed

        // Submit the dialog to add the creature
        clickOn("#confirmAddCreatureButton");

        CreatureDao creatureDao =  getCreatureDaoViaReflection(creatureManagerApp);
        assertEquals(creatureDao.getCreatureInventory().size(), 1);

        // Click on "Add Condition" button
        clickOn("#addConditionButton_TestCreature");

        // Now interact with the condition dialog's contents
        // For example, set a condition name and details
        clickOn("#conditionChoiceBox").clickOn("Blinded");
        clickOn("#durationTextField").write("20");

        // Submit the condition dialog
        clickOn("#confirmConditionYesButton");
    }

    @Test
    void testAddCreatureWithMultipleConditions() throws NoSuchFieldException, IllegalAccessException {
        String[] Conditions = {"Blinded", "Charmed", "Deafened", "Frightened", "Invisible", "Stunned"};

        // Click on "Add Creature" button to open the dialog
        clickOn("#addCreatureButton");

        // Now interact with the dialog's contents
        clickOn("#nameTextField").write("Test Creature");
        clickOn("#healthTextField").write("100");
        clickOn("#initiativeTextField").write("10");
        // Handle other fields as needed

        // Submit the dialog to add the creature
        clickOn("#confirmAddCreatureButton");

        CreatureDao creatureDao =  getCreatureDaoViaReflection(creatureManagerApp);
        assertEquals(creatureDao.getCreatureInventory().size(), 1);

        for (int i = 0; i < 6; i++) {
            // Click on "Add Condition" button
            clickOn("#addConditionButton_TestCreature");

            // Now interact with the condition dialog's contents
            // For example, set a condition name and details
            clickOn("#conditionChoiceBox").clickOn(Conditions[i]);
            clickOn("#durationTextField").write("20");

            // Submit the condition dialog
            clickOn("#confirmConditionYesButton");
        }
    }
}
