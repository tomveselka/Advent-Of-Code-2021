package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayFifteen {

	// Dijkstra algorithms
	// https://www.youtube.com/watch?v=pVfj6mxhdMw
	public static void main(String[] args) {
		DayFifteen dayFifteen = new DayFifteen();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day15InputFull.txt";
		//String path = "resources/Day15InputTest.txt";
		List<String> input = rdr.readFileString(path);
		dayFifteen.taskOne(input);
		// dayFifteen.taskTwo(input);
	}

	public void taskOne(List<String> input) {
		Set<String> visited = new HashSet<String>();
		Set<String> unvisited = new HashSet<String>();
		HashMap<String, Integer> vertexesDistance = new HashMap<String, Integer>();
		HashMap<String, String> vertexesPrevious = new HashMap<String, String>();
		int width = input.get(0).length();
		int height = input.size();
		int[][] map = new int[height][width];
		for (int i = 0; i < height; i++) {
			String line = input.get(i);
			for (int j = 0; j < width; j++) {
				map[i][j] = Integer.parseInt(line.substring(j, j + 1));
				unvisited.add(i + "-" + j);
				vertexesDistance.put(i + "-" + j, Integer.MAX_VALUE);
			}
		}
		// initial two options
		vertexesDistance.put("0-1", map[0][1]);
		vertexesPrevious.put("0-1", "0-0");
		vertexesDistance.put("1-0", map[1][0]);
		vertexesPrevious.put("1-0", "0-0");
		visited.add("0-0");
		unvisited.remove("0-0");
		iterate(vertexesDistance, vertexesPrevious, map, visited, unvisited, width, height);
	}

	public void iterate(HashMap<String, Integer> vertexesDistance, HashMap<String, String> vertexesPrevious,
			int[][] map, Set<String> visited, Set<String> unvisited, int width, int height) {
		String lowestKey = findLowestElement(vertexesDistance, unvisited);
		int[] coord = returnCoord(lowestKey);
		int previousDistance = vertexesDistance.get(lowestKey);

		int i = coord[0];
		int j = coord[1];

		if (i < height - 1) {
			int currentDistance = vertexesDistance.get((i + 1) + "-" + j);
			if (map[i + 1][j] + previousDistance < currentDistance) {
				vertexesDistance.replace((i + 1) + "-" + j, map[i + 1][j] + previousDistance);
				vertexesPrevious.put((i + 1) + "-" + j, lowestKey);
			}
		}
		if (i > 0) {
			int currentDistance = vertexesDistance.get((i - 1) + "-" + j);
			if (map[i - 1][j] + previousDistance < currentDistance) {
				vertexesDistance.replace((i - 1) + "-" + j, map[i - 1][j] + previousDistance);
				vertexesPrevious.put((i - 1) + "-" + j, lowestKey);
			}
		}
		if (j < width - 1) {
			int currentDistance = vertexesDistance.get(i + "-" + (j + 1));
			if (map[i][j + 1] + previousDistance < currentDistance) {
				vertexesDistance.replace(i + "-" + (j + 1), map[i][j + 1] + previousDistance);
				vertexesPrevious.put(i + "-" + (j + 1), lowestKey);
			}
		}
		if (j > 0) {
			int currentDistance = vertexesDistance.get(i + "-" + (j - 1));
			if (map[i][j - 1] + previousDistance < currentDistance) {
				vertexesDistance.replace(i + "-" + (j - 1), map[i][j - 1] + previousDistance);
				vertexesPrevious.put(i + "-" + (j - 1), lowestKey);
			}
		}

		//visited.add(lowestKey);
		unvisited.remove(lowestKey);


		if (vertexesDistance.get((height - 1) + "-" + (width - 1)) < Integer.MAX_VALUE) {
			System.out.println(
					"Shortest path to botom-right corner is " + vertexesDistance.get((height - 1) + "-" + (width - 1)));
			System.out.println(vertexesPrevious.toString());
		} else {
			iterate(vertexesDistance, vertexesPrevious, map, visited, unvisited, width, height);
		}
	}

	public int[] returnCoord(String coordText) {
		String[] coordArray = coordText.split("-");
		return new int[] { Integer.parseInt(coordArray[0]), Integer.parseInt(coordArray[1]) };
	}

	public String findLowestElement(HashMap<String, Integer> vertexesDistance, Set<String> unvisited) {
		String lowestKey = "";
		int minValue = Integer.MAX_VALUE;
		
		for (String key : unvisited) {
			int value = vertexesDistance.get(key);
			if (value < minValue) {
				minValue = value;
				lowestKey = key;
			}
		}
		return lowestKey;
	}

}
