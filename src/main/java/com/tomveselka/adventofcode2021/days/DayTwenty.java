package com.tomveselka.adventofcode2021.days;

import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayTwenty {

	public static void main(String[] args) {
		DayTwenty dayTwenty = new DayTwenty();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day20InputFull.txt";
		//String path = "resources/Day20InputTest.txt";
		List<String> input = rdr.readFileString(path);

		dayTwenty.taskOne(input);
		// dayTwenty.taskTwo(input);
	}

	int height = 0;
	int width = 0;
	int numberOfEnhancements = 50;
	int offset = 2;

	public void taskOne(List<String> input) {
		String algorithm = input.get(0);
		int inputSize = input.size();

		height = input.size() - 2 + offset * numberOfEnhancements;
		width = input.get(2).length() + offset * numberOfEnhancements;
		String[][] array = createArray(input);
		String[][] newArray = createEmptyArray(input);
		printArrayAndCount(array);
		for (int enhance = 1; enhance <= numberOfEnhancements; enhance++) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					StringBuilder str = new StringBuilder();
					for (int a = i - 1; a <= i + 1; a++) {
						for (int b = j - 1; b <= j + 1; b++) {
							if (a < 0 + (numberOfEnhancements - enhance) || b < 0 + (numberOfEnhancements - enhance)
									|| a >= height - (numberOfEnhancements - enhance)
									|| b >= width - (numberOfEnhancements - enhance)) {
								
								if (enhance % 2 == 0) {
									str.append("1");
								} else {
									str.append("0");
								}
								//str.append("0");
							} else {
								if (".".equals(array[a][b])) {
									str.append("0");
								} else {
									str.append("1");
								}
							}
						}
					}
					String numberBin = str.toString();
					int numberDex = Integer.parseInt(numberBin, 2);
					// System.out.println(numberDex);
					String newChar = algorithm.substring(numberDex, numberDex + 1);
					newArray[i][j] = newChar;
				}
			}
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					array[i][j] = newArray[i][j];
					newArray[i][j] = ".";
				}
			}
			//printArrayAndCount(array);

		}
		printArrayAndCount(array);

	}

	// public String[][] enhance(List<String>input)
	public String[][] createArray(List<String> input) {
		String[][] array = new String[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i < numberOfEnhancements || i >= height - numberOfEnhancements || j < numberOfEnhancements || j >= width - numberOfEnhancements) {
					array[i][j] = ".";
					// System.out.print(array[i][j]);
				} else {
					array[i][j] = input.get(i + 2 - numberOfEnhancements).substring(j - numberOfEnhancements, j - numberOfEnhancements + 1);
					// System.out.print(array[i][j]);
				}
			}
			// System.out.println("");

		}

		return array;
	}

	public String[][] createEmptyArray(List<String> input) {
		String[][] array = new String[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				array[i][j] = ".";
			}
		}
		return array;
	}

	public void printArrayAndCount(String[][] array) {
		int litPixels = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ("#".equals(array[i][j])) {
					litPixels++;
				}
				System.out.print(array[i][j]);
			}
			System.out.println("");
		}
		System.out.println("Number of lit pixels is " + litPixels);
		System.out.println();
	}

}
