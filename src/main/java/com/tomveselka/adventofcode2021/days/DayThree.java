package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayThree {

	public static void main(String[] args) {
		DayThree dayThree = new DayThree();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day3InputFull.txt";
		//String path = "resources/Day3InputTest.txt";
		List<String> input = rdr.readFileString(path);
		dayThree.taskOne(input);
		dayThree.taskTwo(input);
	}

	public void taskOne(List<String> input) {
		System.out.println("TASK ONE");
		StringBuilder gamma = new StringBuilder();
		StringBuilder epsilon = new StringBuilder();
		int bitNumber = input.get(0).length();
		for (int i = 0; i < bitNumber; i++) {
			int numberOfOnes = 0;
			int numberOfZeroes = 0;
			for (int j = 0; j < input.size(); j++) {
				if ("1".equals(String.valueOf(input.get(j).charAt(i)))) {
					numberOfOnes++;
				} else {
					numberOfZeroes++;
				}
			}
			if (numberOfOnes > numberOfZeroes) {
				gamma.append("1");
				epsilon.append("0");
			} else {
				gamma.append("0");
				epsilon.append("1");
			}
		}
		String gammaString = gamma.toString();
		String epsilonString = epsilon.toString();
		System.out.println("Binary gamma=" + gammaString + " binary epsilon=" + epsilonString);
		System.out.println("In decimal: gamma=" + Integer.parseInt(gammaString, 2) + " epsilong="
				+ Integer.parseInt(epsilonString, 2) + " Result="
				+ Integer.parseInt(gammaString, 2) * Integer.parseInt(epsilonString, 2));
	}

	public void taskTwo(List<String> input) {
		System.out.println("=======================");
		System.out.println("TASK TWO");
		System.out.println("Oxygen");
		int oxygen = filterList(input, true);
		System.out.println("CO2");
		int co2 = filterList(input, false);
		System.out.println("Result=" + (oxygen * co2));
	}

	public Integer filterList(List<String> input, boolean oxygen) {
		ArrayList<String> localList = new ArrayList<String>();
		for (int i = 0; i < input.size(); i++) {
			localList.add(input.get(i));
		}
		Integer lastNumber = 0;
		int bitNumber = input.get(0).length();
		//System.out.println(localList.toString());
		for (int i = 0; i < bitNumber; i++) {
			int numberOfOnes = 0;
			int numberOfZeroes = 0;
			String moreNumerous = "";
			for (int j = 0; j < localList.size(); j++) {
				if ("1".equals(String.valueOf(localList.get(j).charAt(i)))) {
					numberOfOnes++;
				} else {
					numberOfZeroes++;
				}
			}
			if (numberOfOnes > numberOfZeroes) {
				moreNumerous = "1";
			} else if (numberOfOnes < numberOfZeroes) {
				moreNumerous = "0";
			} else {
				moreNumerous = "1";
			}
			for (int j = localList.size() - 1; j >= 0; j--) {
				// System.out.println("More numerous="+moreNumerous+"current string at index
				// is:"+String.valueOf(input.get(j).charAt(i)));
				if (oxygen) {
					if (!moreNumerous.equals(String.valueOf(localList.get(j).charAt(i)))) {
						localList.remove(j);
					}
				} else {
					if (moreNumerous.equals(String.valueOf(localList.get(j).charAt(i)))) {
						localList.remove(j);
					}
				}
			}
			//System.out.println(localList.toString());
			if (localList.size() == 1) {
				lastNumber = Integer.parseInt(localList.get(0), 2);
				System.out.println("Last value=" + localList.get(0) + " in decimal=" + lastNumber);
				return lastNumber;
			}
		}
		lastNumber = Integer.parseInt(localList.get(0), 2);
		System.out.println("Last value=" + localList.get(0) + " in decimal=" + lastNumber);
		return lastNumber;
	}

}
