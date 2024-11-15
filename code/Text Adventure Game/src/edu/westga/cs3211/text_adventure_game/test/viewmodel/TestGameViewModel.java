package edu.westga.cs3211.text_adventure_game.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameWorld;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.viewmodel.GameViewModel;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ListProperty;

class TestGameViewModel {

	private GameViewModel viewModel;
	private GameWorld gameWorld;

	@BeforeEach
	void setup() throws IOException {
		gameWorld = new GameWorld("src/testLocations.txt");
		viewModel = new GameViewModel();
	}

	@Test
	void testConstructor() throws IOException {
		assertNotNull(viewModel);
		assertNotNull(viewModel.currentLocationDescriptionProperty());
		assertNotNull(viewModel.playerStatusProperty());
		assertNotNull(viewModel.availableActionDescriptionProperty());
		assertNotNull(viewModel.gameRealWorldActivityProperty());
		assertNotNull(viewModel.getAvailableActions());
	}

	@Test
	void testTakeAction() {
		List<Action> actions = gameWorld.getAvailableActions();
		Action action = actions.get(0);

		viewModel.takeAction(action);

		assertNotNull(viewModel.gameRealWorldActivityProperty().get());
	}

	@Test
	void testMoveLocations() {
		Location currentLocation = gameWorld.getCurrentLocation();

		Action move = new Action("Move", "Move to an adjacent location");
		viewModel.moveLocations(move);
		
		assertFalse(currentLocation.getDescription().equalsIgnoreCase(viewModel.gameRealWorldActivityProperty().get()));
	}
	
	@Test
	void testNotMoveLocations() {
		Action nonMoveAction = new Action("InvalidAction", "This action is not a move.");
	    String previousActivity = viewModel.gameRealWorldActivityProperty().get();

	    viewModel.moveLocations(nonMoveAction);

	    assertEquals("", viewModel.gameRealWorldActivityProperty().get());
	    assertEquals(previousActivity, viewModel.gameRealWorldActivityProperty().get());
		
	}

	@Test
	void testCurrentLocationDescriptionProperty() {
		StringProperty property = viewModel.currentLocationDescriptionProperty();
		assertNotNull(property);
		assertFalse(property.get().contains("Location"));
	}

	@Test
	void testPlayerStatusProperty() {
		StringProperty property = viewModel.playerStatusProperty();
		assertNotNull(property);
		assertTrue(property.get().contains("Health"));
	}

	@Test
	void testAvailableActionDescriptionProperty() {
		StringProperty property = viewModel.availableActionDescriptionProperty();
		assertNotNull(property);
	}

	@Test
	void testGameRealWorldActivityProperty() {
		StringProperty property = viewModel.gameRealWorldActivityProperty();
		assertNotNull(property);
	}

	@Test
	void testGetAvailableActions() {
		ListProperty<Action> actions = viewModel.getAvailableActions();
		assertNotNull(actions);
		assertTrue(actions.size() > 0);
	}
}
