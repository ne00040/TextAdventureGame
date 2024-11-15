package edu.westga.cs3211.text_adventure_game.viewmodel;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameWorld;
import edu.westga.cs3211.text_adventure_game.model.Location;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * ViewModel for the game, responsible for updating the UI with game state.
 * 
 * This class serves as an intermediary between the game's logic and the user
 * interface. It manages the game state and updates various UI components with
 * that state, including the current location, player status, available actions,
 * and game feedback.
 * 
 * @author ne00040
 * @version Fall 2024
 */
public class GameViewModel {

	private GameWorld gameWorld;
	private StringProperty currentLocationDescription;
	private StringProperty playerStatus;
	private StringProperty availableActionDescription;
	private StringProperty gameRealWorldActivity;
	private ListProperty<Action> availableActions;

	/**
	 * Constructs the GameViewModel with the provided GameWorld.
	 * 
	 * This constructor initializes the ViewModel with the game's world and state.
	 * It also sets up the String properties that are bound to the UI elements for
	 * automatic updates when the game state changes.
	 *
	 * @throws IOException if there is an error reading the file
	 */
	public GameViewModel() throws IOException {
		this.gameWorld = new GameWorld("src/location.txt");

		this.currentLocationDescription = new SimpleStringProperty();
		this.playerStatus = new SimpleStringProperty();
		this.availableActionDescription = new SimpleStringProperty();
		this.gameRealWorldActivity = new SimpleStringProperty();
		this.availableActions = new SimpleListProperty<>(FXCollections.observableArrayList());

		this.updateUI();
	}

	/**
	 * Updates the UI properties with the current state of the game.
	 * 
	 * This method retrieves information from the GameWorld, such as the current
	 * location, player status, available actions, and feedback. It then updates the
	 * corresponding properties that are bound to the UI.
	 */
	public void updateUI() {
		StringBuilder locationDescriptionBuilder = new StringBuilder(this.gameWorld.getLocationDescription());

	    String hazardInfo = this.gameWorld.getHazard();
	    if (hazardInfo != null && !hazardInfo.trim().isEmpty()) {
	        locationDescriptionBuilder.append("\n").append(hazardInfo);
	    } else {
	    	locationDescriptionBuilder.append("\n").append("No hazard");
	    }
	    this.currentLocationDescription.set(locationDescriptionBuilder.toString());

	    String playerStatusText = this.gameWorld.getPlayerStatus();
	    this.playerStatus.set(playerStatusText);

	    this.availableActions.setAll(this.gameWorld.getAvailableActions());

	    if (!this.availableActions.isEmpty()) {
	        StringBuilder sb = new StringBuilder();
	        for (Action action : this.availableActions) {
	            sb.append(action.getName()).append(" - ").append(action.getDescription()).append("\n");
	        }
	        this.availableActionDescription.set(sb.toString());
	    }

	}

	/**
	 * Executes the action selected by the player from the available actions list.
	 * 
	 * After performing the action in the game world, this method updates the UI
	 * with the results of the action, including any game feedback (e.g., "You
	 * picked an item").
	 *
	 * @param action the action to be performed
	 */
	public void takeAction(Action action) {
		this.gameRealWorldActivity.set(this.gameWorld.performAction(action));
		this.updateUI();
	}

	/**
	 * Moves the player to a new location based on the specified action. This method
	 * checks if the action name is "move" (case-insensitive), and if so, it moves
	 * the player to the first adjacent location from the current location in the
	 * game world.
	 * 
	 * 
	 * @param action the action to be performed, which should have the name "move"
	 *               to trigger the movement to the next adjacent location
	 */
	public void moveLocations(Action action) {
		if ("move".equalsIgnoreCase(action.getName())) {
			Random random = new Random();
			List<Location> adjacentLocations = this.gameWorld.getCurrentLocation().getAdjacentLocations();

			if (!adjacentLocations.isEmpty()) {
				int randomIndex = random.nextInt(adjacentLocations.size());
				Location randomAdjacentLocation = adjacentLocations.get(randomIndex);
				String info = this.gameWorld.moveToLocation(randomAdjacentLocation);
				this.gameRealWorldActivity.set(info);
			}
		}

		this.updateUI();
	}

	/**
	 * Gets the property that holds the current location description.
	 * 
	 * @return the StringProperty representing the current location description
	 */
	public StringProperty currentLocationDescriptionProperty() {
		return this.currentLocationDescription;
	}

	/**
	 * Gets the property that holds the player's status.
	 * 
	 * @return the StringProperty representing the player's status
	 */
	public StringProperty playerStatusProperty() {
		return this.playerStatus;
	}

	/**
	 * Gets the property that holds the available action description.
	 * 
	 * @return the StringProperty representing the available action description
	 */
	public StringProperty availableActionDescriptionProperty() {
		return this.availableActionDescription;
	}

	/**
	 * Gets the property that holds the game real-world activity (feedback).
	 * 
	 * @return the StringProperty representing the real-world feedback for the
	 *         action
	 */
	public StringProperty gameRealWorldActivityProperty() {
		return this.gameRealWorldActivity;
	}

	/**
	 * Gets the list of available actions for the player to choose from.
	 * 
	 * @return an ObservableList containing the available actions
	 */
	public ListProperty<Action> getAvailableActions() {
		return this.availableActions;
	}
}
