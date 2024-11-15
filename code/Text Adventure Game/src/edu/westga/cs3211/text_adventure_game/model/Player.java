package edu.westga.cs3211.text_adventure_game.model;

import java.util.List;

/**
 * Represents a player in the text adventure game.
 * 
 * @author ne00040
 * @version Fall 2024
 */
public class Player {
	private static final int MAX_HEALTH = 100;

	private int health;
	private List<String> inventory;
	private String status;

	/**
	 * Instantiates a new Player.
	 *
	 * @param health    the initial health of the player
	 * @param inventory the initial inventory of the player
	 * @throws IllegalArgumentException if inventory is null
	 */
	public Player(int health, List<String> inventory) {
		if (inventory == null) {
			throw new IllegalArgumentException("Inventory cannot be null");
		}

		this.health = health;
		this.inventory = inventory;
		this.updateStatus();
	}

	/**
	 * Updates the player's status based on health.
	 */
	private void updateStatus() {
		if (this.health <= 0) {
			this.status = "dead";
		} else if (this.health < MAX_HEALTH * 0.3) {
			this.status = "critical";
		} else if (this.health < MAX_HEALTH * 0.7) {
			this.status = "wounded";
		} else {
			this.status = "healthy";
		}
	}

	/**
	 * Gets the current health of the player.
	 *
	 * @return the health of the player
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * Gets the player's inventory.
	 *
	 * @return the inventory of the player
	 */
	public List<String> getInventory() {
		return this.inventory;
	}

	/**
	 * Gets the player's status, which includes health and inventory.
	 *
	 * @return a string describing the player's health and inventory
	 */
	public String getStatus() {
		return "Status: " + this.status + ", Health: " + this.health + ", Inventory: " + this.inventory;
	}

	/**
	 * Checks if the player is alive.
	 *
	 * @return true if the player's health is greater than zero, false otherwise
	 */
	public boolean isAlive() {
		return this.health > 0;
	}

	/**
	 * Moves the player to a new location.
	 *
	 * @param newLocation the new location to move to
	 * @return moving to new locations
	 */
	public String moveTo(Location newLocation) {
		return "Moving to " + newLocation.getName();
	}

	/**
	 * Performs an action.
	 *
	 * @param action the action to perform
	 * @return the action being taken
	 */
	public String takeAction(Action action) {
		return action.execute(this);
	}

	/**
	 * Reduces the player's health by a specified amount of damage.
	 *
	 * @param damage the amount of damage to take
	 */
	public void takeDamage(int damage) {
		this.health -= damage;
		if (this.health < 0) {
			this.health = 0;
		}
		this.updateStatus();
	}

	/**
	 * Adds an item to the player's inventory.
	 *
	 * @param item the item to add
	 */
	public void addItem(String item) {
		this.inventory.add(item);
	}
}
