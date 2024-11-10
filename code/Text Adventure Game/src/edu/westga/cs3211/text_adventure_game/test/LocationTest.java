package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import edu.westga.cs3211.text_adventure_game.model.Location;

public class LocationTest {

	private List<Location> adjacentLocations;
	private List<String> actions;

	@BeforeEach
	public void setUp() {
		this.adjacentLocations = new ArrayList<>();
		this.actions = new ArrayList<>();
		this.actions.add("Pick Item");
		this.actions.add("Move");
	}

	@Test
	public void testLocationInitialization() {
		Location location = new Location("Forest", "A spooky forest.", false, false, adjacentLocations, actions);

		assertEquals("Forest", location.getName());
		assertEquals("A spooky forest.", location.getDescription());
		assertFalse(location.isHazard());
		assertFalse(location.isGoal());
		assertEquals(this.adjacentLocations, location.getAdjacentLocations());
		assertEquals(this.actions, location.getAvailableActions());
	}

	@Test
	public void testGetAvailableActions() {
		Location location = new Location("Forest", "A spooky forest.", false, false, new ArrayList<>(), actions);

		assertEquals(2, location.getAvailableActions().size());
		assertEquals("Pick Item", location.getAvailableActions().get(0));
		assertEquals("Move", location.getAvailableActions().get(1));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("Name cannot be null or blank")
	public void testNameCannotBeNullOrBlank(String name) {
		assertThrows(IllegalArgumentException.class, () -> {
			new Location(name, "Description", false, false, adjacentLocations, actions);
		});
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("Description cannot be null or blank")
	public void testDescriptionCannotBeNullOrBlank(String description) {
		assertThrows(IllegalArgumentException.class, () -> {
			new Location("Name", description, false, false, adjacentLocations, actions);
		});
	}

	@Test
	@DisplayName("toString method with no adjacent locations")
	public void testToStringNoAdjacentLocations() {
		Location location = new Location("Forest", "A spooky forest.", false, false, adjacentLocations, actions);
		String expected = "Location: Forest\nDescription: A spooky forest.\nHazard: false\nGoal: false\nAdjacent Locations: \nActions: [Pick Item, Move]";
		assertEquals(expected, location.toString());
	}

	@Test
	@DisplayName("Adjacent locations cannot be null")
	public void testAdjacentLocationsCannotBeNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Location("Name", "Description", false, false, null, actions);
		});
	}

	@Test
	@DisplayName("Actions cannot be null")
	public void testActionsCannotBeNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Location("Name", "Description", false, false, adjacentLocations, null);
		});
	}

	@Test
	@DisplayName("toString method")
	public void testToString() {
		Location location1 = new Location("Forest", "A spooky forest.", false, false, adjacentLocations, actions);
		Location location2 = new Location("Cave", "A dark cave.", false, false, new ArrayList<>(), new ArrayList<>());
		adjacentLocations.add(location2);

		String expected = "Location: Forest\nDescription: A spooky forest.\nHazard: false\nGoal: false\nAdjacent Locations: Cave\nActions: [Pick Item, Move]";
		assertEquals(expected, location1.toString());
	}
}
