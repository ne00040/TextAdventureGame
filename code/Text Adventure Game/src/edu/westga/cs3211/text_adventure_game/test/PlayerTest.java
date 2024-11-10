package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {

    private List<String> initialInventory;

    @BeforeEach
    public void setUp() {
        initialInventory = new ArrayList<>();
        
    }

    @Test
    public void testPlayerInitialization() {
        Player player = new Player(100, initialInventory);

        assertEquals(100, player.getHealth());
        assertEquals(initialInventory, player.getInventory());
        assertEquals("Status: healthy, Health: 100, Inventory: []", player.getStatus());
    }

    @Test
    @DisplayName("Inventory cannot be null")
    public void testInventoryCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Player(100, null);
        });
    }

    @Test
    public void testTakeDamageToCritical() {
        Player player = new Player(100, initialInventory);

        player.takeDamage(80);
        assertEquals(20, player.getHealth());
        assertEquals("Status: critical, Health: 20, Inventory: []", player.getStatus());
    }

    @Test
    public void testTakeDamageToWounded() {
        Player player = new Player(100, initialInventory);

        player.takeDamage(50);
        assertEquals(50, player.getHealth());
        assertEquals("Status: wounded, Health: 50, Inventory: []", player.getStatus());
    }

    @Test
    public void testTakeDamageToDead() {
        Player player = new Player(100, initialInventory);

        player.takeDamage(100);
        assertEquals(0, player.getHealth());
        assertEquals("Status: dead, Health: 0, Inventory: []", player.getStatus());
        assertFalse(player.isAlive());
    }

    @Test
    public void testTakeDamageToHealthy() {
        Player player = new Player(100, initialInventory);

        player.takeDamage(20);
        assertEquals(80, player.getHealth());
        assertEquals("Status: healthy, Health: 80, Inventory: []", player.getStatus());
    }

    @Test
    public void testTakeDamageWithOverflow() {
        Player player = new Player(10, initialInventory);

        player.takeDamage(20);
        assertEquals(0, player.getHealth()); // Health should not go below zero
        assertEquals("Status: dead, Health: 0, Inventory: []", player.getStatus());
        assertFalse(player.isAlive());
    }

    @Test
    public void testIsAliveTrue() {
        Player player = new Player(50, initialInventory);
        assertTrue(player.isAlive());
    }

    @Test
    public void testIsAliveFalse() {
        Player player = new Player(0, initialInventory);
        assertFalse(player.isAlive());
    }

    @Test
    public void testAddItem() {
        Player player = new Player(100, initialInventory);
        player.addItem("Sword");

        assertTrue(player.getInventory().contains("Sword"));
    }

    @Test
    public void testMoveTo() {
        Player player = new Player(100, initialInventory);
        Hazard hazard = new Hazard("pitfall", 20);
        Location newLocation = new Location("Cave", "A dark cave.", false, hazard, false, new ArrayList<>(), new ArrayList<>());

        player.moveTo(newLocation);
    }

    @Test
    public void testTakeAction() {
        Player player = new Player(100, initialInventory);
        Action action = new Action("Look", "Look around the area");

        player.takeAction(action);
    }

}
