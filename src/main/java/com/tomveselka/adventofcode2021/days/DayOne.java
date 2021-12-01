package com.tomveselka.adventofcode2021.days;

import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;



public class DayOne {

	public static void main(String[] args) {
		DayOne dayOne = new DayOne();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day1InputFull.txt";
		List<Integer> input = rdr.readFileInt(path);
		dayOne.taskOne(input);
		dayOne.taskTwo(input);
	}
	
	public void taskOne(List<Integer> input) {
		int numberOfIncreases=0;
		for (int i=0;i<input.size()-1;i++) {
			if (input.get(i+1)>input.get(i)) {
				numberOfIncreases++;
			}
		}
		System.out.println("Number of increases is "+numberOfIncreases);
	}

	public void taskTwo(List<Integer> input) {
		int numberOfIncreases=0;
		int i=0;
		while(i<input.size()-3) {
			int firstSum=input.get(i)+input.get(i+1)+input.get(i+2);
			int secondSum=input.get(i+1)+input.get(i+2)+input.get(i+3);
			if(secondSum>firstSum) {
				numberOfIncreases++;
			}
			i++;
		}
		System.out.println("Number of increases is "+numberOfIncreases);
	}
}
