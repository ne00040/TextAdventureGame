package edu.westga.cs3211.text_adventure_game.test.utility;

import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.utility.LocationLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestLocationLoader {

    private static final String VALID_FILE_PATH = "src/testLocations.txt"; // Path to the valid test file

    @Test
    void testLoadLocationsValid() throws IOException {
        List<Location> locations = LocationLoader.loadLocations(VALID_FILE_PATH);

        assertEquals(3, locations.size(), "There should be 3 locations loaded.");

        // Test Forest Location
        Location forest = locations.stream().filter(loc -> loc.getName().equals("Forest")).findFirst().orElse(null);
        assertNotNull(forest, "Forest location should be present.");
        assertEquals("A dark and eerie forest.", forest.getDescription(), "Forest description should match.");
        assertNotNull(forest.getHazard(), "Forest should have a hazard.");
        assertEquals("Poison", forest.getHazard().getName(), "Hazard name should be Poison.");
        assertEquals(5, forest.getHazard().getDamage(), "Poison hazard should deal 5 damage.");
        assertFalse(forest.isGoal(), "Forest should not be the goal.");
        assertEquals(2, forest.getAdjacentLocations().size(), "Forest should have 2 adjacent locations.");
        assertTrue(forest.getAdjacentLocations().stream().anyMatch(loc -> loc.getName().equals("Village")), "Forest should be adjacent to Village.");
        assertTrue(forest.getAdjacentLocations().stream().anyMatch(loc -> loc.getName().equals("Cave")), "Forest should be adjacent to Cave.");

        // Test Village Location
        Location village = locations.stream().filter(loc -> loc.getName().equals("Village")).findFirst().orElse(null);
        assertNotNull(village, "Village location should be present.");
        assertEquals("A small peaceful village.", village.getDescription(), "Village description should match.");
        assertNull(village.getHazard(), "Village should have no hazard.");
        assertFalse(village.isGoal(), "Village should not be the goal.");
        assertEquals(1, village.getAdjacentLocations().size(), "Village should have 1 adjacent location.");
        assertTrue(village.getAdjacentLocations().stream().anyMatch(loc -> loc.getName().equals("Forest")), "Village should be adjacent to Forest.");

        // Test Cave Location
        Location cave = locations.stream().filter(loc -> loc.getName().equals("Cave")).findFirst().orElse(null);
        assertNotNull(cave, "Cave location should be present.");
        assertEquals("A damp and dangerous cave.", cave.getDescription(), "Cave description should match.");
        assertNotNull(cave.getHazard(), "Cave should have a hazard.");
        assertEquals("Trap", cave.getHazard().getName(), "Hazard name should be Trap.");
        assertEquals(10, cave.getHazard().getDamage(), "Trap hazard should deal 10 damage.");
        assertTrue(cave.isGoal(), "Cave should be the goal.");
        assertEquals(1, cave.getAdjacentLocations().size(), "Cave should have 1 adjacent location.");
        assertTrue(cave.getAdjacentLocations().stream().anyMatch(loc -> loc.getName().equals("Forest")), "Cave should be adjacent to Forest.");

        assertEquals(2, forest.getAvailableActions().size(), "Forest should have 2 actions.");
        assertTrue(forest.getAvailableActions().stream().anyMatch(action -> action.getName().equals("Move")), "Forest should have a 'Move' action.");
        assertTrue(forest.getAvailableActions().stream().anyMatch(action -> action.getName().equals("Pick")), "Forest should have a 'Pick' action.");
        assertEquals("Go deeper into the forest.", forest.getAvailableActions().stream().filter(action -> action.getName().equals("Move")).findFirst().get().getDescription(), "Move action description should match.");

        assertEquals(2, village.getAvailableActions().size(), "Village should have 2 actions.");
        assertTrue(village.getAvailableActions().stream().anyMatch(action -> action.getName().equals("Talk")), "Village should have a 'Talk' action.");
        assertTrue(village.getAvailableActions().stream().anyMatch(action -> action.getName().equals("Trade")), "Village should have a 'Trade' action.");

        assertEquals(1, cave.getAvailableActions().size(), "Cave should have 1 action.");
        assertTrue(cave.getAvailableActions().stream().anyMatch(action -> action.getName().equals("Enter")), "Cave should have an 'Enter' action.");
    }

    @Test
    void testLoadLocationsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> LocationLoader.loadLocations("src/invalidLocations.txt"));
    }
}