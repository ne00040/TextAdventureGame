package edu.westga.cs3211.text_adventure_game.model;

/**
 * Represents an action that can be performed in the game.
 * 
 * @author ne00040
 * @version Fall 2024
 */
public class Action {
	private String name;
	private String description;

	/**
	 * Instantiates a new Action.
	 *
	 * @param name        the name of the action
	 * @param description the description of the action
	 * @throws IllegalArgumentException if name or description is null or blank
	 */
	public Action(String name, String description) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or blank");
		}
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or blank");
		}

		this.name = name;
		this.description = description;
	}
	
	/**
	 * Gets the name of the action.
	 *
	 * @return the name of the action
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the description of the action.
	 *
	 * @return the description of the action
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Executes the action on the given player.
	 *
	 * @param player the player on whom the action is executed
	 * @return the action that is being executed.
	 */
	public String execute(Player player) {
		return "You performed: " + this.name;
	}
}
