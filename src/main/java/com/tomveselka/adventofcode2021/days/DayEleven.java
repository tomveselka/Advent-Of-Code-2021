package com.tomveselka.adventofcode2021.days;

import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayEleven {
	public static void main(String[] args) {
		DayEleven dayEleven = new DayEleven();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day11InputFull.txt";
		//String path = "resources/Day11InputTest.txt";
		List<String> input = rdr.readFileString(path);
		//dayEleven.taskOne(input);
		dayEleven.taskTwo(input);

	}

	public void taskOne(List<String> input) {
		int height = input.size();
		int width = input.get(0).length();
		int[][] area = new int[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				area[i][j] = Integer.parseInt(input.get(i).substring(j, j + 1));
			}
		}
		System.out.println("Initial state");
		printArray(area, height, width);
		int numberOfFlashes = 0;
		int nrOfTurns=100;
		for (int turn = 0; turn <nrOfTurns ; turn++) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (area[i][j] < 10) {
						area[i][j] = area[i][j] + 1;
					}
				}
			}
			//System.out.println("Before flashing");
			//printArray(area, height, width);
			int newFlashes = 0;
			do {
				newFlashes = 0;
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (area[i][j] == 10) {
							for (int a = i - 1; a <= i + 1; a++) {
								for (int b = j - 1; b <= j + 1; b++) {
									if (a >= 0 && b >= 0 && a < height && b < width) {
										if (area[a][b] < 10) {
											area[a][b] = area[a][b] + 1;
										}
									}
								}
							}
							area[i][j] = 11;
							newFlashes++;
							numberOfFlashes++;
						}
					}
				}
			} while (newFlashes > 0);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (area[i][j] > 9) {
						area[i][j] = 0;
					}
				}
			}
			//System.out.println("Step " + (turn + 1));
			//printArray(area, height, width);
		}
		System.out.println("Number of flashes after "+nrOfTurns+" turns is " + numberOfFlashes);
	}

	public void taskTwo(List<String> input) {
		int height = input.size();
		int width = input.get(0).length();
		int[][] area = new int[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				area[i][j] = Integer.parseInt(input.get(i).substring(j, j + 1));
			}
		}
		System.out.println("Initial state");
		printArray(area, height, width);
		int numberOfFlashesPerRound = 0;
		int turn=0;
		boolean allFlashed=false;
		while (!allFlashed) {
			numberOfFlashesPerRound=0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (area[i][j] < 10) {
						area[i][j] = area[i][j] + 1;
					}
				}
			}
			//System.out.println("Before flashing");
			//printArray(area, height, width);
			int newFlashes = 0;
			do {
				newFlashes = 0;
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (area[i][j] == 10) {
							for (int a = i - 1; a <= i + 1; a++) {
								for (int b = j - 1; b <= j + 1; b++) {
									if (a >= 0 && b >= 0 && a < height && b < width) {
										if (area[a][b] < 10) {
											area[a][b] = area[a][b] + 1;
										}
									}
								}
							}
							area[i][j] = 11;
							newFlashes++;
							numberOfFlashesPerRound++;
						}
					}
				}
			} while (newFlashes > 0);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (area[i][j] > 9) {
						area[i][j] = 0;
					}
				}
			}
			//System.out.println("Step " + (turn + 1));
			//printArray(area, height, width);
			if(numberOfFlashesPerRound==(width*height)) {
				allFlashed=true;
			}else {
				turn++;
			}
		}
		printArray(area, height, width);
		System.out.println("First step when all octopuses flash is "+(turn+1));
		
	}
	public void printArray(int[][] area, int height, int width) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(area[i][j]);
			}
			System.out.println("");

		}
		System.out.println("");
		System.out.println("");
	}
}
