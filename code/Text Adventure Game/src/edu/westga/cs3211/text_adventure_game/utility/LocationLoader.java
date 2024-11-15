package edu.westga.cs3211.text_adventure_game.utility;

import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads Location objects from a text file.
 * 
 * @author ne00040
 * @version Fall 2024
 */
public class LocationLoader {

	/**
	 * Loads locations from a text file.
	 *
	 * @param filePath the path to the text file
	 * @return a list of loaded locations
	 * @throws IOException if an error occurs while reading the file
	 */
	public static List<Location> loadLocations(String filePath) throws IOException {
		Map<String, Location> locationMap = new HashMap<>();
		List<LocationData> locationDataList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			LocationData currentData = null;

			while ((line = reader.readLine()) != null) {
				line = line.trim();

				if (line.startsWith("Location:")) {
					if (currentData != null) {
						locationDataList.add(currentData);
					}
					currentData = new LocationData();
					currentData.name = line.substring(9).trim();
				} else if (line.startsWith("Description:")) {
					currentData.description = line.substring(12).trim();
				} else if (line.startsWith("Hazard:")) {
					currentData.hazard = parseHazard(line);
				} else if (line.startsWith("Goal:")) {
					currentData.isGoal = Boolean.parseBoolean(line.substring(5).trim());
				} else if (line.startsWith("AdjacentLocations:")) {
					String[] adjLocations = line.substring(18).trim().split(",\\s*");
					for (String adj : adjLocations) {
						currentData.adjacentLocationNames.add(adj);
					}
				} else if (line.startsWith("- ")) {
					currentData.actions.add(parseAction(line));
				}
			}
			if (currentData != null) {
				locationDataList.add(currentData);
			}
		}

		for (LocationData data : locationDataList) {
			Location location = new Location(data.name, data.description, data.hazard, data.isGoal, new ArrayList<>(),
					data.actions);
			locationMap.put(data.name, location);
		}

		for (LocationData data : locationDataList) {
			Location location = locationMap.get(data.name);
			for (String adjName : data.adjacentLocationNames) {
				Location adjacent = locationMap.get(adjName);
				if (adjacent != null) {
					location.addAdjacentLocation(adjacent);
				}
			}
		}

		return new ArrayList<>(locationMap.values());
	}

	/**
	 * Parses a Hazard from a line.
	 *
	 * @param line the line containing the hazard
	 * @return the parsed Hazard
	 */
	private static Hazard parseHazard(String line) {
		String hazardData = line.substring(7).trim();
		if (hazardData.equals("None")) {
			return null;
		}
		String[] parts = hazardData.split(":\\s*");

		String hazardName = parts[0].trim();
		int damage = 0;

		if (parts.length > 1) {
			damage = Integer.parseInt(parts[1].trim());
		}
		return new Hazard(hazardName, damage);
	}

	/**
	 * Parses an Action from a line.
	 *
	 * @param line the line containing the action
	 * @return the parsed Action
	 */
	private static Action parseAction(String line) {
		String actionText = line.substring(2).trim();
		String[] actionParts = actionText.split(": ");

		String actionName = actionParts[0].trim();
		String actionDescription = "";

		if (actionParts.length > 1) {
			actionDescription = actionParts[1].trim();
		}

		return new Action(actionName, actionDescription);
	}

	/**
	 * Helper class for temporary storage of location data during loading.
	 */
	private static final class LocationData {
		private String name;
		private String description;
		private Hazard hazard;
		private boolean isGoal;
		private List<String> adjacentLocationNames = new ArrayList<>();
		private List<Action> actions = new ArrayList<>();
	}
}