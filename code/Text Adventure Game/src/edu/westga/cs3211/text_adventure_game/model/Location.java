package edu.westga.cs3211.text_adventure_game.model;

import java.util.List;

/**
 * Represents a location in the text adventure game.
 * 
 * @author ne00040
 * @version Fall 2024
 */
public class Location {
	private String name;
	private String description;
	private boolean isHazard;
	private boolean isGoal;
	private List<Location> adjacentLocations;
	private List<String> actions;

	/**
	 * Instantiates a new Location.
	 *
	 * @param name              the name of the location
	 * @param description       the description of the location
	 * @param isHazard          indicates if the location is hazardous
	 * @param isGoal            indicates if the location is the goal
	 * @param adjacentLocations a list of locations adjacent to this location
	 * @param actions           a list of actions that can be performed in this
	 *                          location
	 * @throws IllegalArgumentException if name or description is null or blank, or
	 *                                  if adjacentLocations or actions is null
	 */
	public Location(String name, String description, boolean isHazard, boolean isGoal, List<Location> adjacentLocations,
			List<String> actions) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or blank");
		}
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or blank");
		}
		if (adjacentLocations == null) {
			throw new IllegalArgumentException("Adjacent locations cannot be null");
		}
		if (actions == null) {
			throw new IllegalArgumentException("Actions cannot be null");
		}
		this.name = name;
		this.description = description;
		this.isHazard = isHazard;
		this.isGoal = isGoal;
		this.adjacentLocations = adjacentLocations;
		this.actions = actions;
	}

	/**
	 * Gets the name of the location.
	 *
	 * @return the name of the location
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the description of the location.
	 *
	 * @return the description of the location
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Checks if the location is hazardous.
	 *
	 * @return true if the location is hazardous, false otherwise
	 */
	public boolean isHazard() {
		return this.isHazard;
	}

	/**
	 * Checks if the location is the goal.
	 *
	 * @return true if the location is the goal, false otherwise
	 */
	public boolean isGoal() {
		return this.isGoal;
	}

	/**
	 * Gets the list of adjacent locations.
	 *
	 * @return the list of adjacent locations
	 */
	public List<Location> getAdjacentLocations() {
		return this.adjacentLocations;
	}

	/**
	 * Gets the available actions in the location.
	 *
	 * @return the list of available actions
	 */
	public List<String> getAvailableActions() {
		return this.actions;
	}

	@Override
	public String toString() {
		StringBuilder adjacentNames = new StringBuilder();
		for (Location loc : this.adjacentLocations) {
			adjacentNames.append(loc.getName()).append(", ");
		}
		if (adjacentNames.length() > 0) {
			adjacentNames.setLength(adjacentNames.length() - 2);
		}
		return "Location: " + this.name + "\nDescription: " + this.description + "\nHazard: " + this.isHazard
				+ "\nGoal: " + this.isGoal + "\nAdjacent Locations: " + adjacentNames + "\nActions: "
				+ this.actions;
	}
}
