package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayFive {
	public static void main(String[] args) {
		DayFive dayFive = new DayFive();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day5InputFull.txt";
		//String path = "resources/Day5InputTest.txt";
		List<String> input = rdr.readFileString(path);
		//dayFive.taskOne(input);
		dayFive.taskTwo(input);
	}

	public void taskOne(List<String> input) {
		List<List<Integer>> startCoordList = inputToStartCoordinates(input);
		List<List<Integer>> endCoordIntList = inputToEndCoordinates(input);
		int maxStart = findHighestCoord(startCoordList);
		int maxEnd = findHighestCoord(endCoordIntList);
		int max = 0;
		if (maxStart > maxEnd) {
			max = ((maxStart+1) / 10) * 10;
			;
		} else {
			max = ((maxEnd+1 + 9) / 10) * 10;
			;
		}
		System.out.println("width of area is " + max + "*" + max);
		int[][] area = new int[max][max];
		for (int i = 0; i < max; i++) {// row x
			for (int j = 0; j < max; j++) {// column y
				area[i][j] = 0;
				//System.out.print(area[i][j]);
			}
			//System.out.println("");
		}
		//System.out.println("");

		for (int i = 0; i < startCoordList.size(); i++) {
			int x1 = startCoordList.get(i).get(1);
			int x2 = endCoordIntList.get(i).get(1);
			int y1 = startCoordList.get(i).get(0);
			int y2 = endCoordIntList.get(i).get(0);
			if (x1 == x2) {
				if (y1 < y2) {
					for (int y = y1; y <= y2; y++) {
						area[x1][y]++;
					}
				} else {
					for (int y = y2; y <= y1; y++) {
						area[x1][y]++;
					}
				}
			} else if (y1 == y2) {
				if (x1 < x2) {
					for (int x = x1; x <= x2; x++) {
						area[x][y1]++;
					}
				} else {
					for (int x = x2; x <= x1; x++) {
						area[x][y1]++;
					}
				}
			}
		}
		int count=0;
		
		for (int i = 0; i < max; i++) {// row x
			for (int j = 0; j < max; j++) {// column y
				if(area[i][j]>1) {
					count++;
				}
				//System.out.print(area[i][j]);
			}
			//System.out.println("");
		}
		System.out.println("Result="+count);

	}
	
	public void taskTwo(List<String> input) {
		List<List<Integer>> startCoordList = inputToStartCoordinates(input);
		List<List<Integer>> endCoordIntList = inputToEndCoordinates(input);
		int maxStart = findHighestCoord(startCoordList);
		int maxEnd = findHighestCoord(endCoordIntList);
		int max = 0;
		if (maxStart > maxEnd) {
			max = ((maxStart+1) / 10) * 10;
			;
		} else {
			max = ((maxEnd+1 + 9) / 10) * 10;
			;
		}
		System.out.println("width of area is " + max + "*" + max);
		int[][] area = new int[max][max];
		for (int i = 0; i < max; i++) {// row x
			for (int j = 0; j < max; j++) {// column y
				area[i][j] = 0;
				//System.out.print(area[i][j]);
			}
			//System.out.println("");
		}
		//System.out.println("");

		for (int i = 0; i < startCoordList.size(); i++) {
			int x1 = startCoordList.get(i).get(1);
			int x2 = endCoordIntList.get(i).get(1);
			int y1 = startCoordList.get(i).get(0);
			int y2 = endCoordIntList.get(i).get(0);
			if (x1 == x2) {
				if (y1 < y2) {
					for (int y = y1; y <= y2; y++) {
						area[x1][y]++;
					}
				} else {
					for (int y = y2; y <= y1; y++) {
						area[x1][y]++;
					}
				}
			} else if (y1 == y2) {
				if (x1 < x2) {
					for (int x = x1; x <= x2; x++) {
						area[x][y1]++;
					}
				} else {
					for (int x = x2; x <= x1; x++) {
						area[x][y1]++;
					}
				}
			}else {
				int signX=Integer.signum(x2-x1);
				int signY=Integer.signum(y2-y1);
				int delta=Math.abs(x1-x2);
				for (int j=0;j<=delta;j++) {
					area[x1+j*signX][y1+j*signY]++;
				}
			}
		}
		int count=0;
		
		for (int i = 0; i < max; i++) {// row x
			for (int j = 0; j < max; j++) {// column y
				if(area[i][j]>1) {
					count++;
				}
				//System.out.print(area[i][j]);
			}
			//System.out.println("");
		}
		System.out.println("Result="+count);

	}

	public List<List<Integer>> inputToStartCoordinates(List<String> input) {
		List<List<Integer>> startCoordList = new ArrayList<List<Integer>>();
		for (String row : input) {
			String[] split = row.split(" -> ");
			String[] startCoord = split[0].split(",");
			List<Integer> startCoordInt = new ArrayList<Integer>();
			startCoordInt.add(Integer.parseInt(startCoord[0]));
			startCoordInt.add(Integer.parseInt(startCoord[1]));
			startCoordList.add(startCoordInt);
		}
		System.out.println("StartCoordList:" + startCoordList.toString());
		return startCoordList;
	}

	public List<List<Integer>> inputToEndCoordinates(List<String> input) {
		List<List<Integer>> endCoordIntList = new ArrayList<List<Integer>>();
		for (String row : input) {
			String[] split = row.split(" -> ");
			String[] endCoord = split[1].split(",");
			List<Integer> endCoordInt = new ArrayList<Integer>();
			endCoordInt.add(Integer.parseInt(endCoord[0]));
			endCoordInt.add(Integer.parseInt(endCoord[1]));
			endCoordIntList.add(endCoordInt);
		}
		System.out.println("EndCoordList:" + endCoordIntList.toString());
		return endCoordIntList;
	}

	public int findHighestCoord(List<List<Integer>> input) {
		int max = 0;
		for (int i = 0; i < input.size(); i++) {
			List<Integer> coord = input.get(i);
			if (coord.get(0) > max) {
				max = coord.get(0);
			}
			if (coord.get(1) > max) {
				max = coord.get(1);
			}
		}
		return max;
	}
}
