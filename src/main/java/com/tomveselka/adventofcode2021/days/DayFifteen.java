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
		//dayFifteen.taskOne(input);
		dayFifteen.taskTwo(input);
	}

	HashMap<String, Integer> vertexesDistance = new HashMap<String, Integer>();
	HashMap<String, String> vertexesPrevious = new HashMap<String, String>();
	Set<String> unvisited = new HashSet<String>();
	int width = 0;
	int height = 0;

	public void taskOne(List<String> input) {
		width = input.get(0).length();
		height = input.size();
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
		unvisited.remove("0-0");
		while (vertexesDistance.get((height - 1) + "-" + (width - 1)) == Integer.MAX_VALUE) {
			iterate(map);
		}
		System.out.println(
				"Shortest path to botom-right corner is " + vertexesDistance.get((height - 1) + "-" + (width - 1)));
	}
	
	public void taskTwo(List<String> input) {
		width = input.get(0).length();
		height = input.size();
		int[][] map = new int[height*5][width*5];
		for (int i = 0; i < height; i++) {
			String line = input.get(i);
			for (int j = 0; j < width; j++) {
				map[i][j] = Integer.parseInt(line.substring(j, j + 1));
				unvisited.add(i + "-" + j);
				vertexesDistance.put(i + "-" + j, Integer.MAX_VALUE);
				int currentValue=Integer.parseInt(line.substring(j, j + 1));
				for (int k=0;k<5;k++) {														
					int newWidth=j+k*width;					
					vertexesDistance.put(i + "-" + (newWidth), Integer.MAX_VALUE);	
					unvisited.add(i + "-" + newWidth);
					map[i][newWidth]=currentValue;
					currentValue++;
					if(currentValue>9) {
						currentValue=1;
					}
					int currentValueH=currentValue;
					for (int m=1;m<5;m++) {						
						int newHeight=i+m*height;
						vertexesDistance.put((newHeight) + "-" + newWidth, Integer.MAX_VALUE);
						unvisited.add(newHeight + "-" + newWidth);
						map[newHeight][newWidth]=currentValueH;
						currentValueH++;
						if(currentValueH>9) {
							currentValueH=1;
						}
					}
				}
				
			}
		}
		width=width*5;
		height=height*5;				
		// initial two options
		vertexesDistance.put("0-1", map[0][1]);
		vertexesPrevious.put("0-1", "0-0");
		vertexesDistance.put("1-0", map[1][0]);
		vertexesPrevious.put("1-0", "0-0");
		unvisited.remove("0-0");
		/*
		for (int i=0;i<height;i++) {
			for (int j=0;j<width;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}*/
		while (vertexesDistance.get((height - 1) + "-" + (width - 1)) == Integer.MAX_VALUE) {
			iterate(map);
		}
		System.out.println(
				"Shortest path to botom-right corner is " + vertexesDistance.get((height - 1) + "-" + (width - 1)));
	}

	public void iterate(int[][] map) {
		String lowestKey = findLowestElement();
		//System.out.println(lowestKey);
		int[] coord = returnCoord(lowestKey);
		int previousDistance = vertexesDistance.get(lowestKey);

		int i = coord[0];
		int j = coord[1];

		if (i < height - 1) {
			int currentDistance = vertexesDistance.get((i + 1) + "-" + j);
			if (map[i + 1][j] + previousDistance < currentDistance) {
				vertexesDistance.replace((i + 1) + "-" + j, map[i + 1][j] + previousDistance);
				// vertexesPrevious.put((i + 1) + "-" + j, lowestKey);
			}
		}
		if (i > 0) {
			int currentDistance = vertexesDistance.get((i - 1) + "-" + j);
			if (map[i - 1][j] + previousDistance < currentDistance) {
				vertexesDistance.replace((i - 1) + "-" + j, map[i - 1][j] + previousDistance);
				// vertexesPrevious.put((i - 1) + "-" + j, lowestKey);
			}
		}
		if (j < width - 1) {
			int currentDistance = vertexesDistance.get(i + "-" + (j + 1));
			if (map[i][j + 1] + previousDistance < currentDistance) {
				vertexesDistance.replace(i + "-" + (j + 1), map[i][j + 1] + previousDistance);
				// vertexesPrevious.put(i + "-" + (j + 1), lowestKey);
			}
		}
		if (j > 0) {
			int currentDistance = vertexesDistance.get(i + "-" + (j - 1));
			if (map[i][j - 1] + previousDistance < currentDistance) {
				vertexesDistance.replace(i + "-" + (j - 1), map[i][j - 1] + previousDistance);
				// vertexesPrevious.put(i + "-" + (j - 1), lowestKey);
			}
		}
		unvisited.remove(lowestKey);
	}

	public int[] returnCoord(String coordText) {
		String[] coordArray = coordText.split("-");
		return new int[] { Integer.parseInt(coordArray[0]), Integer.parseInt(coordArray[1]) };
	}

	public String findLowestElement() {
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
