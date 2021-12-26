package com.tomveselka.adventofcode2021.days;

import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayTwentyFive {
	public static void main(String[] args) {
		DayTwentyFive dayTwentyFive = new DayTwentyFive();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day25InputFull.txt";
		//String path = "resources/Day25InputTest.txt";
		List<String> input = rdr.readFileString(path);
		dayTwentyFive.taskOne(input);
		// dayTwentyFive.taskTwo(input);
	}

	int width = 0;
	int height = 0;

	public void taskOne(List<String> input) {
		width = input.get(0).length();
		height = input.size();
		String[][] area = createArray(input);
		String[][] areaNew = createEmptyArray();
		//printArea(area);
		int numberOfChanges = 0;
		int step = 0;
		do {
			//System.out.println("Step nr. " + step);
			numberOfChanges = 0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (j == (width - 1)) {
						if (">".equals(area[i][j])) {
							if (".".equals(area[i][0])) {
								areaNew[i][0] = ">";
								areaNew[i][j] = ".";
								numberOfChanges++;
							} else {
								areaNew[i][j] = area[i][j];
							}
						}else if("v".equals(area[i][j])) {
							areaNew[i][j] = area[i][j];
						}
					} else {
						if (">".equals(area[i][j])) {
							if (".".equals(area[i][j + 1])) {
								areaNew[i][j + 1] = ">";
								areaNew[i][j] = ".";
								numberOfChanges++;

							} else {
								areaNew[i][j] = area[i][j];
							}
						}else if("v".equals(area[i][j])) {
							areaNew[i][j] = area[i][j];
						}
					}
				}
			}
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					area[i][j] = areaNew[i][j];
					areaNew[i][j] = ".";
				}
			}
			//printArea(area);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (i == (height - 1)) {
						if ("v".equals(area[i][j])) {
							if (".".equals(area[0][j])) {
								areaNew[0][j] = "v";
								areaNew[i][j] = ".";
								numberOfChanges++;
							} else {
								areaNew[i][j] = area[i][j];
							}
						}else if(">".equals(area[i][j])) {
							areaNew[i][j] = area[i][j];
						}
					} else {
						if ("v".equals(area[i][j])) {
							if (".".equals(area[i + 1][j])) {
								areaNew[i + 1][j] = "v";
								areaNew[i][j] = ".";
								numberOfChanges++;
							} else {
								areaNew[i][j] = area[i][j];
							}
						}else if(">".equals(area[i][j])) {
							areaNew[i][j] = area[i][j];
						}
					}
				}
			}
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					area[i][j] = areaNew[i][j];
					areaNew[i][j] = ".";
				}
			}
			step++;
			//printArea(area);
		//} while (step < 5);
		} while (numberOfChanges > 0);
		System.out.println("Sea cucumbers stop moving after " + step + " steps");
	}

	public String[][] createArray(List<String> input) {
		String[][] array = new String[height][width];
		for (int i = 0; i < height; i++) {
			String row = input.get(i);
			for (int j = 0; j < width; j++) {
				array[i][j] = row.substring(j, j + 1);
			}
		}
		return array;
	}

	public String[][] createEmptyArray() {
		String[][] array = new String[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				array[i][j] = ".";
			}
		}
		return array;
	}

	public void printArea(String[][] array) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
