package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class ActionTest {

    @Test
    public void testActionInitialization() {
        Action action = new Action("Attack", "Deals damage to the enemy.");

        assertEquals("Attack", action.getName());
        assertEquals("Deals damage to the enemy.", action.getDescription());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Name cannot be null or blank")
    public void testNameCannotBeNullOrBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Action(name, "Description");
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Description cannot be null or blank")
    public void testDescriptionCannotBeNullOrBlank(String description) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Action("Name", description);
        });
    }

    @Test
    @DisplayName("Execute method")
    public void testExecute() {
        Action action = new Action("Attack", "Deals damage to the enemy.");
        Player player = new Player(100, new ArrayList<>());

        action.execute(player);
    }
}

