package edu.westga.cs3211.text_adventure_game.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class TestHazard {

	@Test
    void testConstructorValid() {
        Hazard hazard = new Hazard("Fire", 10);
        assertNotNull(hazard);
        assertEquals("Fire", hazard.getName());
        assertEquals(10, hazard.getDamage());
    }

    @Test
    void testConstructorInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Hazard(null, 10));
        assertThrows(IllegalArgumentException.class, () -> new Hazard("", 10));
        assertThrows(IllegalArgumentException.class, () -> new Hazard("   ", 10));
    }

    @Test
    void testConstructorInvalidDamage() {
        assertThrows(IllegalArgumentException.class, () -> new Hazard("Fire", -1));
    }

    @Test
    void testApplyToValid() {
        Player player = new Player(100, new ArrayList<>()); 
        Hazard hazard = new Hazard("Fire", 10);

        hazard.applyTo(player);

        assertEquals(90, player.getHealth());
    }

    @Test
    void testApplyToZeroDamage() {
        Player player = new Player(100, new ArrayList<>()); 
        Hazard hazard = new Hazard("Smoke", 0);

        hazard.applyTo(player);

        assertEquals(100, player.getHealth());
    }

    @Test
    void testApplyToNegativeDamage() {
        assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player(100, new ArrayList<>());
            Hazard hazard = new Hazard("Poison", -5); 
            hazard.applyTo(player);
        });
    }
}
