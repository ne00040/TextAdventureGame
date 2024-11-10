package edu.westga.cs3211.text_adventure_game.model;

/**
 * Represents a hazard in the game that can affect a player.
 * 
 * @author ne00040
 * @version Fall 2024
 */
public class Hazard {
	private String name;
	private int damage;

	/**
	 * Constructs a new Hazard.
	 *
	 * @param name   the name of the hazard
	 * @param damage the amount of damage the hazard deals
	 * @throws IllegalArgumentException if name is null or blank, or if damage is
	 *                                  negative
	 */
	public Hazard(String name, int damage) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or blank");
		}
		if (damage < 0) {
			throw new IllegalArgumentException("Damage cannot be negative");
		}
		this.name = name;
		this.damage = damage;
	}

	/**
	 * Gets the name of the hazard.
	 *
	 * @return the name of the hazard
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the damage dealt by the hazard.
	 *
	 * @return the damage dealt by the hazard
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Applies the hazard to the given player.
	 *
	 * @param player the player to apply the hazard to
	 */
	public void applyTo(Player player) {
		player.takeDamage(this.damage);
	}
}