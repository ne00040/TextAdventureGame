package edu.westga.cs3211.text_adventure_game.view;

import java.net.URL;
import java.util.ResourceBundle;
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
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<?> actionComboBox;

	@FXML
	private TextArea availableActionArea;

	@FXML
	private TextArea currentLocationArea;

	@FXML
	private AnchorPane pane;

	@FXML
	private TextArea playerStatusArea;

	@FXML
	void onTakeAction(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert this.actionComboBox != null
				: "fx:id=\"actionComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.availableActionArea != null
				: "fx:id=\"availableActionArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.currentLocationArea != null
				: "fx:id=\"currentLocationArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.playerStatusArea != null
				: "fx:id=\"playerStatusArea\" was not injected: check your FXML file 'MainWindow.fxml'.";

	}

}
