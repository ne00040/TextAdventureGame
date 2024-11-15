package edu.westga.cs3211.text_adventure_game.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameWorld;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.utility.LocationLoader;

class TestGameWorld {

	private GameWorld gameWorld;
	private List<Location> locations;

	@BeforeEach
	void setup() throws IOException {
		String locationFilePath = "src/testLocations.txt";
		locations = LocationLoader.loadLocations(locationFilePath);
		gameWorld = new GameWorld(locationFilePath);
	}

	@Test
	void testConstructorWithNullFilePath() {
		assertThrows(IllegalArgumentException.class, () -> new GameWorld(null));
	}

	@Test
	void testConstructorWithValidFilePath() throws IOException {
		assertNotNull(gameWorld);
	}

	@Test
	void testLinkAdjacentLocations() {
		Location forest = locations.stream().filter(loc -> loc.getName().equals("Forest")).findFirst().orElse(null);
		Location village = locations.stream().filter(loc -> loc.getName().equals("Village")).findFirst().orElse(null);
		Location cave = locations.stream().filter(loc -> loc.getName().equals("Cave")).findFirst().orElse(null);

		assertTrue(forest.getAdjacentLocations().contains(village));
		assertTrue(forest.getAdjacentLocations().contains(cave));
	}

	@Test
	void testApplyHazardIfPresentWithHazard() {
		Location cave = locations.stream().filter(loc -> loc.getName().equals("Cave")).findFirst().orElse(null);
		assertNotNull(cave);
		gameWorld.applyHazardIfPresent(cave);
	}

	@Test
	void testApplyHazardIfPresentWithoutHazard() {
		Location village = locations.stream().filter(loc -> loc.getName().equals("Village")).findFirst().orElse(null);
		assertNotNull(village);
		gameWorld.applyHazardIfPresent(village);
	}

	@Test
	void testMoveToValidLocation() {
		Location location = gameWorld.getCurrentLocation().getAdjacentLocations().get(0);
		gameWorld.moveToLocation(location);
		assertEquals(location, gameWorld.getCurrentLocation());
	}

	@Test
	void testMoveToInvalidLocation() {
		Location village = locations.stream().filter(loc -> loc.getName().equals("Village")).findFirst().orElse(null);
		assertThrows(IllegalArgumentException.class, () -> gameWorld.moveToLocation(village));
	}

	@Test
	void testPerformAction() {
		Location village = locations.stream().filter(loc -> loc.getName().equals("Village")).findFirst().orElse(null);
		Action tradeAction = village.getAvailableActions().stream().filter(action -> action.getName().equals("Trade"))
				.findFirst().orElse(null);
		String actionResult = gameWorld.performAction(tradeAction);
		assertEquals("You performed: Trade", actionResult);
	}

	@Test
	void testGetLocationDescription() {
		Location cave = locations.stream().filter(loc -> loc.getName().equals("Cave")).findFirst().orElse(null);
		String description = gameWorld.getLocationDescription();
		assertEquals(cave.getDescription(), description);
	}

	@Test
	void testGetAvailableActions() {
		List<Action> actions = gameWorld.getAvailableActions();
		assertTrue(actions.size() > 0);
	}

	@Test
	void testGetPlayerStatus() {
		String status = gameWorld.getPlayerStatus();
		assertTrue(status.contains("Health"));
	}

	@Test
	void testIsGoalReached() {
		assertTrue(gameWorld.isGoalReached());
	}

}
