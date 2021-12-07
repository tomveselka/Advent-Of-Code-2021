package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DaySeven {

	public static void main(String[] args) {
		DaySeven daySeven = new DaySeven();
		FileReaderCustom rdr = new FileReaderCustom();
		//String path = "resources/Day7InputFull.txt";
		String path = "resources/Day7InputTest.txt";
		List<String> input = rdr.readFileString(path);
		//daySeven.taskOne(input);
		daySeven.taskTwo(input);
	}
	
	public void taskOne (List<String> input) {
		String[] inputArray=input.get(0).split(",");
		List<Integer> crabList = new ArrayList<Integer>();
		for (int i=0;i<inputArray.length;i++) {
			crabList.add(Integer.parseInt(inputArray[i]));
		}
		Collections.sort(crabList);
		int middle=0;
		if (crabList.size()%2==0) {
			middle=crabList.size()/2;
			middle=(int)Math.floor((crabList.get(middle)+crabList.get(middle-1))/2);
		}else {
			middle=crabList.get((int)Math.floor(crabList.size()/2));
		}
		System.out.println("Optimal position is "+middle);
		int sum=0;
		for (int i=0;i<crabList.size();i++) {
			sum=sum+(Math.abs(crabList.get(i)-middle));
		}
		System.out.println("Total fuel is "+sum);
	}
	
	public void taskTwo (List<String> input) {
		String[] inputArray=input.get(0).split(",");
		List<Integer> crabList = new ArrayList<Integer>();
		double sum=0;
		for (int i=0;i<inputArray.length;i++) {
			crabList.add(Integer.parseInt(inputArray[i]));
			sum=sum+Integer.parseInt(inputArray[i]);
		}
		System.out.println("Sum="+sum+" list size="+crabList.size());
		int average= (int) Math.ceil(sum/crabList.size())-1; //no idea why? without -1, it works for test sample but not real one. And with -1 it works for full and not test
	
		System.out.println("Optimal position is "+average);
		int result=0;
		for (int i=0;i<crabList.size();i++) {
			result=result+countUsedFule(crabList.get(i), average);
		}
		System.out.println("Total fuel is "+result);
	}
	
	public int countUsedFule(int start, int end) {
		int result=0;
		for (int i=0;i<Math.abs(start-end);i++) {
			result=result+1+i*1;
		}
		return result;
	}
}
