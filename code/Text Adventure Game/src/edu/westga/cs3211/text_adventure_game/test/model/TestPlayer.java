package edu.westga.cs3211.text_adventure_game.test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.Player;

import java.util.ArrayList;
import java.util.List;

public class TestPlayer {

	@Test
	void testConstructorValid() {
		List<String> inventory = new ArrayList<>();
		inventory.add("Sword");
		Player player = new Player(50, inventory);

		assertNotNull(player);
		assertEquals(50, player.getHealth());
		assertEquals(inventory, player.getInventory());
	}

	@Test
	void testConstructorInvalidInventory() {
		assertThrows(IllegalArgumentException.class, () -> new Player(50, null));
	}

	@Test
	void testUpdateStatusHealthy() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(80, inventory); // Health > 70

		assertEquals("healthy", player.getStatus().split(",")[0].split(":")[1].trim());
	}

	@Test
	void testUpdateStatusWounded() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(50, inventory); // Health between 30 and 70

		assertEquals("wounded", player.getStatus().split(",")[0].split(":")[1].trim());
	}

	@Test
	void testUpdateStatusCritical() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(20, inventory); // Health < 30

		assertEquals("critical", player.getStatus().split(",")[0].split(":")[1].trim());
	}

	@Test
	void testUpdateStatusDead() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(0, inventory); // Health <= 0

		assertEquals("dead", player.getStatus().split(",")[0].split(":")[1].trim());
	}

	@Test
	void testGetStatus() {
		List<String> inventory = new ArrayList<>();
		inventory.add("Sword");
		Player player = new Player(50, inventory); // Wounded status

		String expectedStatus = "Status: wounded, Health: 50, Inventory: [Sword]";
		assertEquals(expectedStatus, player.getStatus());
	}

	@Test
	void testIsAliveTrue() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(50, inventory); // Health > 0

		assertTrue(player.isAlive());
	}

	@Test
	void testIsAliveFalse() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(0, inventory); // Health <= 0

		assertFalse(player.isAlive());
	}

	@Test
	void testTakeDamage() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(50, inventory);

		player.takeDamage(20);
		assertEquals(30, player.getHealth());
		assertEquals("wounded", player.getStatus().split(",")[0].split(":")[1].trim());
	}

	@Test
	void testTakeDamageMoreThanHealth() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(10, inventory);

		player.takeDamage(15); // Should reduce health to 0, not negative
		assertEquals(0, player.getHealth());
		assertEquals("dead", player.getStatus().split(",")[0].split(":")[1].trim());
	}

	@Test
	void testAddItem() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(50, inventory);

		player.addItem("Potion");
		assertTrue(player.getInventory().contains("Potion"));
	}

	@Test
	void testMoveTo() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(50, inventory);
		Hazard hazard = new Hazard("Pitfall", 20);
		Location location = new Location("Forest", "A spooky forest.", hazard, false, new ArrayList<>(),
				new ArrayList<>());

		player.moveTo(location);
	}

	@Test
	void testTakeAction() {
		List<String> inventory = new ArrayList<>();
		Player player = new Player(50, inventory);
		Action action = new Action("Attack", "Attack an enemy");

		String result = player.takeAction(action);
		assertEquals("You performed: Attack", result);
	}
}
