package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameWorld;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class GameWorldTest {
	private Player player;
	private Location startLocation;
	private GameWorld gameWorld;

	@BeforeEach
	public void setUp() {
		player = new Player(100, new ArrayList<>());
		startLocation = new Location("Forest", "A spooky forest.", null, false, new ArrayList<>(),
				new ArrayList<>());
		gameWorld = new GameWorld(player, startLocation);
	}

	@Test
	public void testGameWorldInitialization() {
		assertEquals(startLocation, gameWorld.getCurrentLocation());
		assertEquals("Status: healthy, Health: 100, Inventory: []", gameWorld.getPlayerStatus());
	}

	@Test
	@DisplayName("GameWorld initialization with null player")
	public void testGameWorldInitializationNullPlayer() {
		assertThrows(IllegalArgumentException.class, () -> {
			new GameWorld(null, startLocation);
		});
	}

	@Test
	@DisplayName("GameWorld initialization with null start location")
	public void testGameWorldInitializationNullStartLocation() {
		assertThrows(IllegalArgumentException.class, () -> {
			new GameWorld(player, null);
		});
	}

	@Test
	@DisplayName("Moving to a new adjacent location")
	public void testMoveToLocation() {
		Location cave = new Location("Cave", "A dark cave.", null, false, new ArrayList<>(), new ArrayList<>());
		startLocation.getAdjacentLocations().add(cave);

		gameWorld.moveToLocation(cave);
		assertEquals(cave, gameWorld.getCurrentLocation());
	}

	@Test
	@DisplayName("Moving to an invalid location")
	public void testMoveToInvalidLocation() {
		Location cave = new Location("Cave", "A dark cave.", null, false, new ArrayList<>(), new ArrayList<>());
		assertThrows(IllegalArgumentException.class, () -> {
			gameWorld.moveToLocation(cave);
		});
	}

	@Test
	@DisplayName("Performing an action")
	public void testPerformAction() {
		Action action = new Action("Look", "Look around the area");
		gameWorld.performAction(action);
	}

	@Test
	@DisplayName("Applying hazard to player at start location")
	public void testHazardAtStartLocation() {
		Hazard hazard = new Hazard("Pitfall", 20);
		startLocation = new Location("Forest", "A spooky forest.", hazard, false, new ArrayList<>(),
				new ArrayList<>());
		gameWorld = new GameWorld(player, startLocation);

		assertEquals(80, player.getHealth());
		assertEquals("Status: healthy, Health: 80, Inventory: []", player.getStatus());
	}

	@Test
	@DisplayName("No hazard at start location")
	public void testNoHazardAtStartLocation() {
		startLocation = new Location("Forest", "A spooky forest.", null, false, new ArrayList<>(),
				new ArrayList<>());
		gameWorld = new GameWorld(player, startLocation);
		assertEquals(100, player.getHealth());
		assertEquals("Status: healthy, Health: 100, Inventory: []", player.getStatus());
	}

	@Test
	@DisplayName("Applying hazard to player at new location")
	public void testHazardAtNewLocation() {
		Hazard hazard = new Hazard("Pitfall", 20);
		Location hazardousLocation = new Location("Cave", "A dark cave.", hazard, false, new ArrayList<>(),
				new ArrayList<>());
		startLocation.getAdjacentLocations().add(hazardousLocation);

		gameWorld.moveToLocation(hazardousLocation);
		assertEquals(80, player.getHealth());
		assertEquals("Status: healthy, Health: 80, Inventory: []", player.getStatus());
	}

	@Test
	@DisplayName("Getting location description")
	public void testGetLocationDescription() {
		String description = gameWorld.getLocationDescription();
		assertEquals("A spooky forest.", description);
	}

	@Test
	@DisplayName("Getting available actions")
	public void testGetAvailableActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(new Action("Look", "Look around the area"));
		startLocation = new Location("Forest", "A spooky forest.", null, false, new ArrayList<>(), actions);
		gameWorld = new GameWorld(player, startLocation);

		List<Action> availableActions = gameWorld.getAvailableActions();
		assertEquals(actions, availableActions);
	}
}
