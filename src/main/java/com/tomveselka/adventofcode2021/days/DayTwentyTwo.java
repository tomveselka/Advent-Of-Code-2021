package com.tomveselka.adventofcode2021.days;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.tomveselka.adventofcode2021.objects.Step;
import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayTwentyTwo {

	public static void main(String[] args) {
		DayTwentyTwo dayTwentyTwo = new DayTwentyTwo();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day22InputFull.txt";
		 //String path = "resources/Day22InputTest.txt";
		List<String> input = rdr.readFileString(path);

		//dayTwentyTwo.taskOne(input);
		dayTwentyTwo.taskTwo(input);
	}

	public void taskOne(List<String> input) {
		int xSize = 101;
		int ySize = 101;
		int zSize = 101;
		List<Step> stateList = createListOfActions(input);
		String[][][] reactor = new String[xSize][ySize][zSize];
		// initial set up
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				for (int z = 0; z < zSize; z++) {
					reactor[x][y][z] = "X";
				}
			}
		}
		int i = 0;
		while (i<stateList.size()&&stateList.get(i).getXleft() >= -50&&stateList.get(i).getXleft() <= 50) {
			int numberChanged = 0;
			Step step = stateList.get(i);
			System.out.println(step.toString());
			for (int x = step.getXleft()+50; x <= step.getXright()+50; x++) {
				for (int y = step.getYleft()+50; y <= step.getYright()+50; y++) {
					for (int z = step.getZleft()+50; z <= step.getZright()+50; z++) {
						if ("on".equals(step.getState())) {
							if (!"O".equals(reactor[x][y][z])) {
								numberChanged++;
							}
							reactor[x][y][z] = "O";
						} else {
							if (!"X".equals(reactor[x][y][z])) {
								numberChanged++;
							}
							reactor[x][y][z] = "X";
						}

					}
				}
			}
			System.out.println("Number of cubes changed:" + numberChanged);
			i++;
		}
		int finalNumber = 0;
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				for (int z = 0; z < zSize; z++) {
					if ("O".equals(reactor[x][y][z])) {
						finalNumber++;
					}
				}
			}
		}
		System.out.println("Final number of changed cubes is "+finalNumber);

	}

	public void taskTwo(List<String> input) {
		//Day Two is NOT happening. No, just no :) Just reading topic used to solve it make my head hurt
	}
	
	public List<Step> createListOfActions(List<String> input) {
		List<Step> stateList = new ArrayList<Step>();
		for (int i = 0; i < input.size(); i++) {
			String[] stateSplit = input.get(i).split(("\\s+"));
			String[] axisSplit = stateSplit[1].split(",");
			String[] xSplit = axisSplit[0].replace("x=", "").split("\\..");
			String[] ySplit = axisSplit[1].replace("y=", "").split("\\..");
			String[] zSplit = axisSplit[2].replace("z=", "").split("\\..");
			Step step = new Step(stateSplit[0], Integer.parseInt(xSplit[0]), Integer.parseInt(xSplit[1]),
					Integer.parseInt(ySplit[0]), Integer.parseInt(ySplit[1]), Integer.parseInt(zSplit[0]),
					Integer.parseInt(zSplit[1]));
			stateList.add(step);
		}
		return stateList;
	}

}
