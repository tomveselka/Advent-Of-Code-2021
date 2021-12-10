package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayTen {

	public static void main(String[] args) {
		DayTen dayTen = new DayTen();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day10InputFull.txt";
		//String path = "resources/Day10InputTest.txt";
		List<String> input = rdr.readFileString(path);
		//dayTen.taskOne(input);
		dayTen.taskTwo(input);
	}

	public void taskOne(List<String> input) {
		List<String> opening = Arrays.asList(new String[] { "(", "<", "[", "{" });
		List<String> closing = Arrays.asList(new String[] { ")", ">", "]", "}" });
		int points = 0;
		for (int i = input.size() - 1; i >= 0; i--) {
			List<Character> line = input.get(i).chars().mapToObj(e -> (char) e).collect(Collectors.toList());
			boolean continueLoop = true;
			int pos = 0;
			while (line.size() > 0 && continueLoop && pos < line.size()) {
				String current = line.get(pos).toString();
				if (pos == 0 && closing.contains(current)) {
					points = points + getPoints(current);
					continueLoop = false;
				}
				if (closing.contains(current)) {
					int closingPos = closing.indexOf(current);
					if (!opening.get(closingPos).equals(line.get(pos - 1).toString())) {
						points = points + getPoints(current);
						continueLoop = false;
					} else {
						line.remove(pos);
						line.remove(pos - 1);
						pos = 0;
					}
				} else {
					pos++;
				}
			}
		}
		System.out.println("Final point score is " + points);
	}

	public void taskTwo(List<String> input) {
		List<String> opening = Arrays.asList(new String[] { "(", "<", "[", "{" });
		List<String> closing = Arrays.asList(new String[] { ")", ">", "]", "}" });
		int points = 0;
		List<List<Character>> listOfLines= new ArrayList<List<Character>>();
		for (int i = input.size() - 1; i >= 0; i--) {
			List<Character> line = input.get(i).chars().mapToObj(e -> (char) e).collect(Collectors.toList());
			boolean continueLoop = true;
			int pos = 0;
			while (line.size() > 0 && continueLoop && pos < line.size()) {
				String current = line.get(pos).toString();
				if (pos == 0 && closing.contains(current)) {
					points = points + getPoints(current);
					continueLoop = false;
					input.remove(i);
				}
				if (closing.contains(current)) {
					int closingPos = closing.indexOf(current);
					if (!opening.get(closingPos).equals(line.get(pos - 1).toString())) {
						points = points + getPoints(current);
						continueLoop = false;
						input.remove(i);
					} else {
						line.remove(pos);
						line.remove(pos - 1);
						pos = 0;
					}
				} else {
					pos++;
				}
				
			}
			listOfLines.add(line);
			//System.out.println(line.toString());

		}
		for (int i=listOfLines.size()-1;i>=0;i--) {
			List<Character> line = listOfLines.get(i);
			int numberOfClosing=0;
			for (Character character:line) {
				if (closing.contains(character.toString())) {
					numberOfClosing++;
				}
			}
			if (numberOfClosing>0) {
				listOfLines.remove(i);
			}
		}
		List<Long> listOfTotalScores = new ArrayList<Long>();
		for (List<Character>line:listOfLines) {
			System.out.println(line.toString());
			long totalScore=0;
			for (int i=line.size()-1;i>=0;i--) {
				totalScore=totalScore*5;
				totalScore=totalScore+getPointsTaskTwo(line.get(i).toString());
			}
			listOfTotalScores.add(totalScore);
		}
		Collections.sort(listOfTotalScores);
		System.out.println(listOfTotalScores.toString());
		System.out.println("Middle score is "+listOfTotalScores.get((listOfTotalScores.size()/2)));
		
	}

	public int getPoints(String input) {
		if (")".equals(input)) {
			return 3;
		}
		if ("]".equals(input)) {
			return 57;
		}
		if ("}".equals(input)) {
			return 1197;
		} else {
			return 25137;
		}
	}
	
	public int getPointsTaskTwo(String input) {
		if ("(".equals(input)) {
			return 1;
		}
		if ("[".equals(input)) {
			return 2;
		}
		if ("{".equals(input)) {
			return 3;
		} else {
			return 4;
		}
	}

}
