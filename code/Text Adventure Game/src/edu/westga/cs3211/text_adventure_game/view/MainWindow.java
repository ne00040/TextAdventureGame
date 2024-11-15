package edu.westga.cs3211.text_adventure_game.view;

import java.io.IOException;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.viewmodel.GameViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author ne00040
 * @version Fall 2024
 */
public class MainWindow {

	@FXML
	private ComboBox<Action> actionComboBox;

	@FXML
	private TextArea availableActionArea;

	@FXML
	private TextArea currentLocationArea;

	@FXML
	private TextArea gameRealWorldActivity;

	@FXML
	private AnchorPane pane;

	@FXML
	private TextArea playerStatusArea;

	private GameViewModel gameViewModel;

	@FXML
	void onTakeAction(ActionEvent event) {
		Action selectedAction = this.actionComboBox.getValue();
		if (selectedAction != null) {
			if (selectedAction.getName().equalsIgnoreCase("move")) {
				this.gameViewModel.moveLocations(selectedAction);
			} else {
				this.gameViewModel.takeAction(selectedAction);
			}
		}
	}

	@FXML
	void initialize() throws IOException {
		this.gameViewModel = new GameViewModel();
		this.currentLocationArea.textProperty().bind(this.gameViewModel.currentLocationDescriptionProperty());
		this.playerStatusArea.textProperty().bind(this.gameViewModel.playerStatusProperty());
		this.availableActionArea.textProperty().bind(this.gameViewModel.availableActionDescriptionProperty());
		this.gameRealWorldActivity.textProperty().bind(this.gameViewModel.gameRealWorldActivityProperty());

		this.actionComboBox.itemsProperty().bind(this.gameViewModel.getAvailableActions());
	}
}
