package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class HazardTest {

	@Test
	public void testHazardInitialization() {
		Hazard hazard = new Hazard("Pitfall", 20);

		assertEquals("Pitfall", hazard.getName());
		assertEquals(20, hazard.getDamage());
	}

	@Test
	@DisplayName("Applying hazard to player")
	public void testApplyTo() {
		Player player = new Player(100, new ArrayList<>());
		Hazard hazard = new Hazard("Pitfall", 20);

		hazard.applyTo(player);

		assertEquals(80, player.getHealth());
		assertEquals("Status: healthy, Health: 80, Inventory: []", player.getStatus());
	}

	@Test
	@DisplayName("Applying lethal hazard to player")
	public void testApplyLethalHazardTo() {
		Player player = new Player(10, new ArrayList<>());
		Hazard hazard = new Hazard("Pitfall", 20);

		hazard.applyTo(player);

		assertEquals(0, player.getHealth());
		assertEquals("Status: dead, Health: 0, Inventory: []", player.getStatus());
		assertFalse(player.isAlive());
	}

	@Test
	@DisplayName("Damage cannot be negative")
	public void testNegativeDamage() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Hazard("Pitfall", -10);
		});
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("Name cannot be null or blank")
	public void testNameCannotBeNullOrBlank(String name) {
		assertThrows(IllegalArgumentException.class, () -> {
			new Hazard(name, 10);
		});
	}
}
