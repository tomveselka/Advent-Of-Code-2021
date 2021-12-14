package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayTwelve {

	public static void main(String[] args) {
		DayTwelve dayTwelve = new DayTwelve();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day12InputFull.txt";
		//String path = "resources/Day12InputTest3.txt";
		List<String> input = rdr.readFileString(path);
		// dayTwelve.taskOne(input);
		dayTwelve.taskTwo(input);

	}

	private List<String> fromStart = new ArrayList<String>();
	private List<String> toEnd = new ArrayList<String>();
	private List<String> listOfPaths = new ArrayList<String>();

	public void taskOne(List<String> input) {
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).contains("start")) {
				fromStart.add(input.get(i).replace("start", "").replace("-", ""));
			}
			if (input.get(i).contains("end")) {
				toEnd.add(input.get(i).replace("end", "").replace("-", ""));
			}
		}

		for (int i = 0; i < fromStart.size(); i++) {
			String currentPath = "start";
			findAllPathsFromPoint(input, fromStart.get(i), currentPath.concat(",").concat(fromStart.get(i)));
		}

		for (String path : listOfPaths) {
			System.out.println(path);
		}
		System.out.println("Number of paths is " + listOfPaths.size());

	}

	public void findAllPathsFromPoint(List<String> input, String startingPoint, String currentPath) {
		List<String> possiblePaths = new ArrayList<String>();
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).contains(startingPoint)) {
				String nextPoint = input.get(i).replace(startingPoint, "").replace("-", "");
				if (nextPoint.equals("end")) {
					String path = currentPath + ",end";
					if (!listOfPaths.contains(path)) {
						listOfPaths.add(path);
					}
				} else if (!currentPath.contains(nextPoint) || onlyCaps(nextPoint)) {
					possiblePaths.add(nextPoint);
				}
			}
		}
		for (int i = 0; i < possiblePaths.size(); i++) {
			findAllPathsFromPoint(input, possiblePaths.get(i), currentPath.concat(",").concat(possiblePaths.get(i)));
		}
	}

	public void taskTwo(List<String> input) {
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).contains("start")) {
				fromStart.add(input.get(i).replace("start", "").replace("-", ""));
			}
			if (input.get(i).contains("end")) {
				toEnd.add(input.get(i).replace("end", "").replace("-", ""));
			}
		}

		for (int i = 0; i < fromStart.size(); i++) {
			String currentPath = "start";
			findAllPathsFromPointV2(input, fromStart.get(i), currentPath.concat(",").concat(fromStart.get(i)), false);
		}

		for (String path : listOfPaths) {
			System.out.println(path);
		}
		System.out.println("Number of paths is " + listOfPaths.size());

	}

	public void findAllPathsFromPointV2(List<String> input, String startingPoint, String currentPath,
			boolean smallVisitedTwice) {
		List<String> possiblePaths = new ArrayList<String>();
		List<Boolean> visitedTwice = new ArrayList<Boolean>();
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).contains(startingPoint)) {
				String nextPoint = input.get(i).replace(startingPoint, "").replace("-", "");
				if (nextPoint.equals("end")) {
					String path = currentPath + ",end";
					if (!listOfPaths.contains(path)) {
						listOfPaths.add(path);
						//System.out.println(listOfPaths.size());
					}
				} else if (!currentPath.contains(nextPoint) || onlyCaps(nextPoint)
						|| (currentPath.contains(nextPoint)&&!onlyCaps(nextPoint) && !smallVisitedTwice)) {
					if (!"start".equals(nextPoint)) {
						possiblePaths.add(nextPoint);
						if ((currentPath.contains(nextPoint)&&!onlyCaps(nextPoint)) || smallVisitedTwice) {
							visitedTwice.add(true);
						} else {
							visitedTwice.add(false);
						}
					}

				}
			}
		}
		for (int i = 0; i < possiblePaths.size(); i++) {
			findAllPathsFromPointV2(input, possiblePaths.get(i), currentPath.concat(",").concat(possiblePaths.get(i)),
					visitedTwice.get(i));
		}
	}

	public boolean onlyCaps(String point) {
		for (int i = 0; i < point.length(); i++) {
			char character = point.charAt(i);
			if (!Character.isUpperCase(character)) {
				return false;
			}
		}
		return true;
	}

}
