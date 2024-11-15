package edu.westga.cs3211.text_adventure_game.viewmodel;

import java.io.IOException;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameWorld;
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
		this.gameWorld = new GameWorld("locations.txt");

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
		String locationDescription = this.gameWorld.getLocationDescription();
		this.currentLocationDescription.set(locationDescription);

		String playerStatusText = this.gameWorld.getPlayerStatus();
		this.playerStatus.set(playerStatusText);

		this.availableActions.setAll(this.gameWorld.getAvailableActions());

		if (!this.availableActions.isEmpty()) {
			this.availableActionDescription.set(this.availableActions.get(0).getDescription());
		}

		this.gameRealWorldActivity.set("");
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
		this.updateUI();
		this.gameRealWorldActivity.set(this.gameWorld.performAction(action));
	}
	
	
	public void moveLocations(Action action) {
		this.updateUI();
		if ("move".equalsIgnoreCase(action.getName())) {
			this.gameRealWorldActivity.set(this.gameWorld.moveToLocation(this.gameWorld.getCurrentLocation().getAdjacentLocations().get(0)));
		}
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
