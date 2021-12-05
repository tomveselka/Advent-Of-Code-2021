package com.tomveselka.adventofcode2021.days;

import java.util.Arrays;
import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayTwo {

	public static void main(String[] args) {
		DayTwo dayTwo = new DayTwo();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day2InputFull.txt";
		//String path = "resources/Day2InputTest.txt";
		List<String> input = rdr.readFileString(path);
		dayTwo.taskOne(input);
		dayTwo.taskTwo(input);
	}

	public void taskOne(List<String> input) {
		int horizont = 0;
		int depth = 0;
		for (int i = 0; i < input.size(); i++) {
			String[] splited=input.get(i).trim().split(" ");
			//System.out.println(Arrays.asList(splited).toString());
			if ("forward".equals(splited[0])) {
				horizont=horizont+Integer.parseInt(splited[1]);
			}else if ("up".equals(splited[0])) {
				depth=depth-Integer.parseInt(splited[1]);
			}else {
				depth=depth+Integer.parseInt(splited[1]);
			}
		}
		int result = horizont*depth;
		System.out.println("Task One: Horizont="+horizont+" depth="+depth+" and result is:"+result);
	}
	
	public void taskTwo(List<String> input) {
		int horizont = 0;
		int depth = 0;
		int aim=0;
		for (int i = 0; i < input.size(); i++) {
			String[] splited=input.get(i).trim().split(" ");
			//System.out.println(Arrays.asList(splited).toString());
			if ("forward".equals(splited[0])) {
				horizont=horizont+Integer.parseInt(splited[1]);
				depth=depth+Integer.parseInt(splited[1])*aim;
			}else if ("up".equals(splited[0])) {
				aim=aim-Integer.parseInt(splited[1]);
			}else {
				aim=aim+Integer.parseInt(splited[1]);
			}
		}
		int result = horizont*depth;
		System.out.println("Task Two: Horizont="+horizont+" depth="+depth+" and result is:"+result);
	}

}
