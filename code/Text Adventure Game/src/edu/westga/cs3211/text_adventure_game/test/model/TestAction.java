package edu.westga.cs3211.text_adventure_game.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class TestAction {

	@Test
	void testConstructorValid() {
		Action action = new Action("Pick up item", "The action to pick up an item.");
		assertNotNull(action);
		assertEquals("Pick up item", action.getName());
		assertEquals("The action to pick up an item.", action.getDescription());
	}

	@Test
	void testConstructorInvalidName() {
		assertThrows(IllegalArgumentException.class, () -> new Action(null, "Description"));
		assertThrows(IllegalArgumentException.class, () -> new Action("", "Description"));
		assertThrows(IllegalArgumentException.class, () -> new Action("   ", "Description"));
	}

	@Test
	void testConstructorInvalidDescription() {
		assertThrows(IllegalArgumentException.class, () -> new Action("Pick up item", null));
		assertThrows(IllegalArgumentException.class, () -> new Action("Pick up item", ""));
		assertThrows(IllegalArgumentException.class, () -> new Action("Pick up item", "   "));
	}

	@Test
	void testExecute() {
		Action action = new Action("Pick up item", "The action to pick up an item.");
		Player player = new Player(100, new ArrayList<>());

		String result = action.execute(player);
		assertEquals("You performed: Pick up item", result);
	}

	@Test
	void testToString() {
		Action action = new Action("Pick up item", "The action to pick up an item.");
		assertEquals("Pick up item", action.toString());
	}
}
