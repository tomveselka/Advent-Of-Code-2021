package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DaySix {

	public static void main(String[] args) {
		DaySix daySix = new DaySix();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day6InputFull.txt";
		//String path = "resources/Day6InputTest.txt";
		List<String> input = rdr.readFileString(path);
		//daySix.taskOne(input);
		daySix.taskTwo(input);
	}
	
	public void taskOne(List<String> input) {
		String[] inputSplit=input.get(0).split(",");
		List<Integer> fishList = new ArrayList<Integer>();
		for (int i=0;i<inputSplit.length;i++) {
			fishList.add(Integer.parseInt(inputSplit[i]));
		}
		for (int i=0;i<80;i++) {
			int numberOfNew=0;
			for (int j=0;j<fishList.size();j++) {
				if(fishList.get(j)>0) {
					fishList.set(j, fishList.get(j)-1);
				}else {
					fishList.set(j, 6);
					numberOfNew++;
				}
			}
			for (int j=0;j<numberOfNew;j++) {
				fishList.add(8);
			}
			//System.out.println(fishList.toString());
		}
		System.out.println("After 80 days there will be "+fishList.size());
	}
	
	public void taskTwo(List<String> input) {
		String[] inputSplit=input.get(0).split(",");
		List<Integer> fishList = new ArrayList<Integer>();
		for (int i=0;i<inputSplit.length;i++) {
			fishList.add(Integer.parseInt(inputSplit[i]));
		}
		long[] occurences = new long[] {0,0,0,0,0,0,0,0,0};
		for (int i=0;i<8;i++) {
			occurences[i]=Collections.frequency(fishList, i);
		}
		for (int i=0;i<256;i++) {
			long occurZero=occurences[0];
			for (int j=0;j<8;j++) {
				occurences[j]=occurences[j+1];
			}
			occurences[6]=occurences[6]+occurZero;
			occurences[8]=occurZero;
			
		}
		long total=0;
		for (int i=0;i<9;i++) {
			total=total+occurences[i];
		}
		System.out.println("After 256 days there will be "+total);
	}

}
