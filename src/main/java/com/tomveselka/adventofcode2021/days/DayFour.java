package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.tomveselka.adventofcode2021.objects.DayFourInput;
import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayFour {

	public static void main(String[] args) {
		DayFour dayFour = new DayFour();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day4InputFull.txt";
		//String path = "resources/Day4InputTest.txt";
		List<String> input = rdr.readFileString(path);
		//dayFour.taskOne(input);
		dayFour.taskTwo(input);

	}

	public void taskOne(List<String> input) {
		String[] drawnNumbers = input.get(0).replaceAll("\\s+", "").split(",");
		List<String> drawnNumbersList = Arrays.asList(drawnNumbers);
		System.out.println(drawnNumbersList.toString());
		List<DayFourInput> tablesList = sortInput(input);
		System.out.println("==============");
		System.out.println("==============");
		boolean continueGame = true;
		int i = 0;
		while (continueGame) {
			String drawnNumber = drawnNumbersList.get(i);
			System.out.println("Drawn number " + drawnNumber + " which is " + (i + 1));
			for (int tableNumber = 0; tableNumber < tablesList.size(); tableNumber++) {
				DayFourInput dayFourInput = tablesList.get(tableNumber);
				ArrayList<List<String>> numbersTable = dayFourInput.getNumbers();
				ArrayList<List<String>> statesTable = dayFourInput.getStates();
				for (int rowNumber = 0; rowNumber < numbersTable.size(); rowNumber++) {
					List<String> rowNumbers = numbersTable.get(rowNumber);
					List<String> rowStates = statesTable.get(rowNumber);
					for (int colNumber = 0; colNumber < rowNumbers.size(); colNumber++) {
						if (drawnNumber.equals(rowNumbers.get(colNumber))) {
							// System.out.println("Found match: drawnNumber="+drawnNumber+"
							// match="+rowNumbers.get(colNumber)+" rowIndex="+rowNumber+"
							// colIndex="+colNumber);
							rowStates.set(colNumber, "Y");
							statesTable.set(rowNumber, rowStates);
							break;
							// System.out.println(rowStates.toString());
						}
					}

				}
				dayFourInput.setStates(statesTable);
				printTable(dayFourInput);
				int sum = checkBingo(dayFourInput);
				if (sum > 0) {
					System.out.println("Result=" + (sum * Integer.valueOf(drawnNumber)));
					continueGame = false;
					break;
				}
				tablesList.set(tableNumber, dayFourInput);
			}
			System.out.println("==============");
			i++;
			if (i == drawnNumbersList.size()) {
				continueGame = false;
			}

		}
	}

	public void taskTwo(List<String> input) {
		String[] drawnNumbers = input.get(0).replaceAll("\\s+", "").split(",");
		List<String> drawnNumbersList = Arrays.asList(drawnNumbers);
		System.out.println(drawnNumbersList.toString());
		List<DayFourInput> tablesList = sortInput(input);
		System.out.println("==============");
		System.out.println("==============");
		List<Integer> completedTables = new ArrayList<>();
		boolean continueGame = true;
		int i = 0;
		while (continueGame) {
			String drawnNumber = drawnNumbersList.get(i);
			System.out.println("Drawn number " + drawnNumber + " which is " + (i + 1));
			for (int tableNumber = 0; tableNumber < tablesList.size(); tableNumber++) {
				DayFourInput dayFourInput = tablesList.get(tableNumber);
				ArrayList<List<String>> numbersTable = dayFourInput.getNumbers();
				ArrayList<List<String>> statesTable = dayFourInput.getStates();
				for (int rowNumber = 0; rowNumber < numbersTable.size(); rowNumber++) {
					List<String> rowNumbers = numbersTable.get(rowNumber);
					List<String> rowStates = statesTable.get(rowNumber);
					for (int colNumber = 0; colNumber < rowNumbers.size(); colNumber++) {
						if (drawnNumber.equals(rowNumbers.get(colNumber))) {
							// System.out.println("Found match: drawnNumber="+drawnNumber+"
							// match="+rowNumbers.get(colNumber)+" rowIndex="+rowNumber+"
							// colIndex="+colNumber);
							rowStates.set(colNumber, "Y");
							statesTable.set(rowNumber, rowStates);
							break;
							// System.out.println(rowStates.toString());
						}
					}

				}
				dayFourInput.setStates(statesTable);
				printTable(dayFourInput);
				int sum = checkBingo(dayFourInput);
				if (sum > 0) {
					if (!completedTables.contains(tableNumber)) {
						completedTables.add(tableNumber);
					}
					if (completedTables.size() == tablesList.size()) {
						System.out.println("Result=" + (sum * Integer.valueOf(drawnNumber)));
						continueGame = false;
						break;
					}
				}
				tablesList.set(tableNumber, dayFourInput);
			}
			System.out.println("==============");
			i++;
			if (i == drawnNumbersList.size()) {
				continueGame = false;
			}

		}
	}

	public List<DayFourInput> sortInput(List<String> input) {

		long count = input.stream().filter(x -> x.isEmpty()).count();
		boolean keepCounting = true;
		int i = 2;
		int tableHeight = 0;
		while (keepCounting) {
			if (!"".equals(input.get(i))) {
				tableHeight++;
				i++;
			} else {
				keepCounting = false;
				System.out.println("Linebreak found at " + i + " table height is " + tableHeight);
			}
		}
		keepCounting = true;
		int line = 2;

		List<DayFourInput> listOfTables = new ArrayList<DayFourInput>();

		while (line < input.size()) {

			DayFourInput dayFourInput = new DayFourInput();
			ArrayList<List<String>> numbersTable = new ArrayList<List<String>>();
			ArrayList<List<String>> statesTable = new ArrayList<List<String>>();

			for (int j = line; j < line + tableHeight; j++) {
				String[] drawnNumbers = input.get(j).split(" ");
				List<String> rowNumbers = new ArrayList<String>(Arrays.asList(drawnNumbers));
				rowNumbers.removeAll(Arrays.asList("", null));
				// System.out.println("RowNumbers size="+rowNumbers.size()+" content
				// "+rowNumbers.toString());
				List<String> rowStates = new ArrayList<String>();
				for (int k = 0; k < rowNumbers.size(); k++) {
					rowStates.add("N");
				}
				numbersTable.add(rowNumbers);
				statesTable.add(rowStates);
			}
			dayFourInput.setNumbers(numbersTable);
			dayFourInput.setStates(statesTable);
			printTable(dayFourInput);
			listOfTables.add(dayFourInput);
			line = line + tableHeight + 1;
		}
		System.out.println("listOfTables size=" + listOfTables.size());
		return listOfTables;
	}

	public void printTable(DayFourInput dayFourInput) {
		ArrayList<List<String>> numbersTable = dayFourInput.getNumbers();
		ArrayList<List<String>> statesTable = dayFourInput.getStates();

		for (int i = 0; i < numbersTable.size(); i++) {
			List<String> rowNumbers = numbersTable.get(i);
			List<String> rowStates = statesTable.get(i);
			for (int j = 0; j < rowNumbers.size(); j++) {
				System.out.print(rowNumbers.get(j) + " ");
			}
			System.out.print("   ");
			for (int j = 0; j < rowNumbers.size(); j++) {
				System.out.print(rowStates.get(j) + " ");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
	}

	public int checkBingo(DayFourInput dayFourInput) {
		ArrayList<List<String>> statesTable = dayFourInput.getStates();
		ArrayList<List<String>> numbersTable = dayFourInput.getNumbers();
		for (int i = 0; i < statesTable.size(); i++) {
			List<String> rowStates = statesTable.get(i);
			// ROWS
			long count = rowStates.stream().filter(rowState -> "Y".equals(rowState)).count();
			if (count == statesTable.size()) {
				System.out.println("BINGO");
				int sumOfRemaining = 0;
				for (int j = 0; j < statesTable.size(); j++) {
					List<String> rowStates2 = statesTable.get(j);
					List<String> rowNumbers2 = numbersTable.get(j);
					for (int k = 0; k < rowStates2.size(); k++) {
						if ("N".equals(rowStates2.get(k))) {
							sumOfRemaining = sumOfRemaining + Integer.valueOf(rowNumbers2.get(k));
						}
					}
				}
				return sumOfRemaining;
			}
			// COLUMNS
			Integer numberOfYes[] = new Integer[statesTable.size()];
			for (int j = 0; j < numberOfYes.length; j++) {
				numberOfYes[j] = 0;
			}
			for (int j = 0; j < statesTable.size(); j++) {
				List<String> rowStates2 = statesTable.get(j);

				for (int k = 0; k < rowStates2.size(); k++) {
					if ("Y".equals(rowStates2.get(k))) {
						numberOfYes[k]++;
					}
				}
			}
			List<Integer> numberOfYesList = Arrays.asList(numberOfYes);
			Collections.sort(numberOfYesList);
			if (numberOfYesList.get(numberOfYesList.size() - 1) == statesTable.size()) {
				System.out.println("BINGO");
				int sumOfRemaining = 0;
				for (int j = 0; j < statesTable.size(); j++) {
					List<String> rowStates2 = statesTable.get(j);
					List<String> rowNumbers2 = numbersTable.get(j);
					for (int k = 0; k < rowStates2.size(); k++) {
						if ("N".equals(rowStates2.get(k))) {
							sumOfRemaining = sumOfRemaining + Integer.valueOf(rowNumbers2.get(k));
						}
					}
				}
				return sumOfRemaining;
			}
		}
		return 0;
	}

}
