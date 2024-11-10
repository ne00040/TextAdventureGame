package edu.westga.cs3211.text_adventure_game.model;

import java.util.List;

/**
 * Represents the game world in the text adventure game.
 * 
 * @author ne00040
 * @version Fall 2024
 */
public class GameWorld {
	private Player player;
	private Location currentLocation;

	/**
	 * Constructs a new GameWorld.
	 *
	 * @param player        the player in the game world
	 * @param startLocation the starting location for the player
	 * @throws IllegalArgumentException if player or startLocation are null
	 */
	public GameWorld(Player player, Location startLocation) {
		if (player == null) {
			throw new IllegalArgumentException("Player cannot be null");
		}
		if (startLocation == null) {
			throw new IllegalArgumentException("Start location cannot be null");
		}

		this.player = player;
		this.currentLocation = startLocation;
		this.applyHazardIfPresent(this.currentLocation);
	}

	private void applyHazardIfPresent(Location location) {
		if (location.isHazard() && location.getHazard() != null) {
			location.getHazard().applyTo(this.player);
		}
	}

	/**
	 * Gets the current location of the player.
	 *
	 * @return the current location of the player
	 */
	public Location getCurrentLocation() {
		return this.currentLocation;
	}

	/**
	 * Moves the player to a new adjacent location.
	 *
	 * @param location the new location to move to
	 */
	public void moveToLocation(Location location) {
		if (!this.currentLocation.getAdjacentLocations().contains(location)) {
			throw new IllegalArgumentException("Invalid location. The player can only move to adjacent locations.");
		}
		this.currentLocation = location;
		this.player.moveTo(location);
		this.applyHazardIfPresent(location);
	}

	/**
	 * Allows the player to take an action in the current location.
	 *
	 * @param action the action to be taken
	 */
	public void performAction(Action action) {
		this.player.takeAction(action);
	}

	/**
	 * Gets the description of the current location.
	 *
	 * @return the description of the current location
	 */
	public String getLocationDescription() {
		return this.currentLocation.getDescription();
	}

	/**
	 * Gets the available actions in the current location.
	 *
	 * @return a list of available actions in the current location
	 */
	public List<Action> getAvailableActions() {
		return this.currentLocation.getAvailableActions();
	}

	/**
	 * Gets the player's status.
	 *
	 * @return the player's status
	 */
	public String getPlayerStatus() {
		return this.player.getStatus();
	}
}
