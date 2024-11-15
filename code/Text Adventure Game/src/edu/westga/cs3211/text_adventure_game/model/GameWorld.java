package edu.westga.cs3211.text_adventure_game.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.westga.cs3211.text_adventure_game.utility.LocationLoader;

/**
 * Represents the game world in the text adventure game.
 * 
 * @author ne00040
 * @version Fall 2024
 */
public class GameWorld {
	private Player player;
	private Location currentLocation;
	private List<Location> allLocations;

	/**
	 * Constructs a new GameWorld and loads all locations from the file.
	 *
	 * @param locationFilePath the file path where the location data is stored
	 * @throws IOException              if there is an error reading the file
	 * @throws IllegalArgumentException if player or locationFilePath are null
	 */
	public GameWorld(String locationFilePath) throws IOException {
		if (locationFilePath == null) {
			throw new IllegalArgumentException("Location file path cannot be null");
		}
		this.player = new Player(100, new ArrayList<>());
		this.allLocations = LocationLoader.loadLocations(locationFilePath);

		this.currentLocation = this.getRandomLocation();
		this.applyHazardIfPresent(this.currentLocation);
	}

	/**
	 * Randomly selects a starting location from all available locations.
	 *
	 * @return a random starting location
	 */
	private Location getRandomLocation() {
		Random random = new Random(0);
		return this.allLocations.get(random.nextInt(this.allLocations.size()));
	}

	/**
	 * Applies the hazard (if any) to the player when they enter a location.
	 *
	 * @param location the location to check for a hazard
	 */
	public void applyHazardIfPresent(Location location) {
		if (location.hasHazard()) {
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
	 * @return message that the player moved
	 */
	public String moveToLocation(Location location) {
		if (!this.currentLocation.getAdjacentLocations().contains(location)) {
			throw new IllegalArgumentException("Invalid location. The player can only move to adjacent locations.");
		}
		this.currentLocation = location;
		this.applyHazardIfPresent(location);
		return this.player.moveTo(location);
	}

	/**
	 * Allows the player to take an action in the current location.
	 *
	 * @param action the action to be taken
	 * @return the action being performed
	 */
	public String performAction(Action action) {
		return this.player.takeAction(action);
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

	/**
	 * Checks if the current location is the goal.
	 *
	 * @return true if location is the goal, false otherwise;
	 */
	public boolean isGoalReached() {
		return this.currentLocation.isGoal();
	}

	/**
	 * Gets Hazard
	 *
	 * @return the name and the damage of the hazard
	 */
	public String getHazard() {
		return "Hazard: " + this.currentLocation.getHazard().getName() + " Damage: "
				+ this.currentLocation.getHazard().getDamage();
	}
}
