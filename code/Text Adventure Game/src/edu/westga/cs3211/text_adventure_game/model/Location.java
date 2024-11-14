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
	private Hazard hazard;
	private boolean isGoal;
	private List<Location> adjacentLocations;
	private List<Action> actions;

	/**
	 * Instantiates a new Location.
	 *
	 * @param name              the name of the location
	 * @param description       the description of the location
	 * @param hazard            the hazard if there is a hazard
	 * @param isGoal            indicates if the location is the goal
	 * @param adjacentLocations a list of locations adjacent to this location
	 * @param actions           a list of actions that can be performed in this
	 *                          location
	 * @throws IllegalArgumentException if name or description is null or blank, or
	 *                                  if adjacentLocations or actions is null
	 */
	public Location(String name, String description, Hazard hazard, boolean isGoal, List<Location> adjacentLocations,
			List<Action> actions) {
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
		this.hazard = hazard;
		this.description = description;
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
	 * Gets the hazard in the location.
	 *
	 * @return the hazard in the location
	 */
	public Hazard getHazard() {
		return this.hazard;
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
	public boolean hasHazard() {
		return this.hazard != null;
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
	public List<Action> getAvailableActions() {
		return this.actions;
	}
}
