package edu.westga.cs3211.text_adventure_game.test.model;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class TestLocation {

	private Location location1;
	private Location location2;
	private List<Location> adjacentLocations;
	private List<Action> actions;

	@BeforeEach
	void setUp() {
		adjacentLocations = new ArrayList<>();
		actions = new ArrayList<>();
		Hazard hazard = new Hazard("Fire", 10);
		location1 = new Location("Start", "Starting point", hazard, false, adjacentLocations, actions);
		location2 = new Location("Forest", "Dense forest", null, false, adjacentLocations, actions);
	}

	@Test
	void testConstructorInvalidName() {
		assertThrows(IllegalArgumentException.class, () -> {
	        new Location(null, "Description", null, false, adjacentLocations, actions);
	    });

	    assertThrows(IllegalArgumentException.class, () -> {
	        new Location("", "Description", null, false, adjacentLocations, actions);
	    });

	    assertThrows(IllegalArgumentException.class, () -> {
	        new Location("    ", "Description", null, false, adjacentLocations, actions);
	    });
	}

	@Test
	void testConstructorInvalidDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
	        new Location("Name", null, null, false, adjacentLocations, actions);
	    });

	    assertThrows(IllegalArgumentException.class, () -> {
	        new Location("Name", "", null, false, adjacentLocations, actions);
	    });

	    assertThrows(IllegalArgumentException.class, () -> {
	        new Location("Name", "    ", null, false, adjacentLocations, actions);
	    });
	}

	@Test
	void testConstructorInvalidAdjacentLocations() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Location("Name", "Description", null, false, null, actions);
		});
	}

	@Test
	void testConstructorInvalidActions() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Location("Name", "Description", null, false, adjacentLocations, null);
		});
	}

	@Test
	void testGetName() {
		assertEquals("Start", location1.getName());
		assertEquals("Forest", location2.getName());
	}

	@Test
	void testGetDescription() {
		assertEquals("Starting point", location1.getDescription());
		assertEquals("Dense forest", location2.getDescription());
	}

	@Test
	void testGetHazard() {
		assertNotNull(location1.getHazard());
		assertNull(location2.getHazard());
	}

	@Test
	void testHasHazard() {
		assertTrue(location1.hasHazard());
		assertFalse(location2.hasHazard());
	}

	@Test
	void testIsGoal() {
		assertFalse(location1.isGoal());
		assertFalse(location2.isGoal());
	}

	@Test
	void testGetAdjacentLocations() {
		assertEquals(0, location1.getAdjacentLocations().size());
		location1.addAdjacentLocation(location2);
		assertEquals(1, location1.getAdjacentLocations().size());
	}

	@Test
	void testGetAvailableActions() {
		assertEquals(0, location1.getAvailableActions().size());
		Action action = new Action("Pick", "Pick an item");
		location1.addAction(action);
		assertEquals(1, location1.getAvailableActions().size());
	}

	@Test
	void testAddAdjacentLocation() {
		location1.addAdjacentLocation(location2);
		assertTrue(location1.getAdjacentLocations().contains(location2));

		assertThrows(IllegalArgumentException.class, () -> {
			location1.addAdjacentLocation(null);
		});
	}

	@Test
	void testAddAction() {
		Action action = new Action("Pick", "Pick an item");
		location1.addAction(action);
		assertTrue(location1.getAvailableActions().contains(action));

		assertThrows(IllegalArgumentException.class, () -> {
			location1.addAction(null);
		});
	}

	@Test
	void testGetAdjacentLocationNames() {
		location1.addAdjacentLocation(location2);
		List<String> adjacentNames = location1.getAdjacentLocationNames();
		assertTrue(adjacentNames.contains("Forest"));
		assertEquals(1, adjacentNames.size());
	}
}