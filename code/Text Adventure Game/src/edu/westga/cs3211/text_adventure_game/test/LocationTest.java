package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Location;

public class LocationTest {

    private List<Location> adjacentLocations;
    private List<Action> actions;
    private Hazard hazard;

    @BeforeEach
    public void setUp() {
        adjacentLocations = new ArrayList<>();
        actions = new ArrayList<>();
        actions.add(new Action("Look", "Look around the area"));
        hazard = new Hazard("Pitfall", 20);
    }

    @Test
    public void testLocationInitialization() {
        Location location = new Location("Forest", "A spooky forest.", null, false, adjacentLocations, actions);

        assertEquals("Forest", location.getName());
        assertEquals("A spooky forest.", location.getDescription());
        assertFalse(location.hasHazard());
        assertNull(location.getHazard());
        assertFalse(location.isGoal());
        assertEquals(adjacentLocations, location.getAdjacentLocations());
        assertEquals(actions, location.getAvailableActions());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Name cannot be null or blank")
    public void testNameCannotBeNullOrBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Location(name, "Description", null, false, adjacentLocations, actions);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Description cannot be null or blank")
    public void testDescriptionCannotBeNullOrBlank(String description) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Location("Name", description, null, false, adjacentLocations, actions);
        });
    }

    @Test
    @DisplayName("Adjacent locations cannot be null")
    public void testAdjacentLocationsCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Location("Name", "Description", null, false, null, actions);
        });
    }

    @Test
    @DisplayName("Actions cannot be null")
    public void testActionsCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Location("Name", "Description", null, false, adjacentLocations, null);
        });
    }

    @Test
    @DisplayName("Location with hazard")
    public void testLocationWithHazard() {
        Location location = new Location("Forest", "A spooky forest.", hazard, false, adjacentLocations, actions);

        assertEquals("Forest", location.getName());
        assertEquals("A spooky forest.", location.getDescription());
        assertTrue(location.hasHazard());
        assertNotNull(location.getHazard());
        assertEquals(hazard, location.getHazard());
        assertFalse(location.isGoal());
    }
}
