package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tomveselka.adventofcode2021.objects.Instruction;
import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayTwentyFour {

	public static void main(String[] args) {
		DayTwentyFour dayTwentyFour = new DayTwentyFour();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day24InputFull.txt";
		// String path = "resources/Day24InputTest.txt";
		List<String> input = rdr.readFileString(path);
		dayTwentyFour.taskOne(input);
		// dayTwentyFour.taskTwo(input);

	}

	List<Instruction> listOfInstructions = new ArrayList<Instruction>();

	public void taskOne(List<String> input) {
		processInput(input);
		long highest = Long.valueOf("99999999999999");
		int w = 0;
		int x = 0;
		int y = 0;
		int z = 0;
		boolean numberFound = false;
		int pos = -1;
		while (!numberFound) {
			for (int i = 0; i < listOfInstructions.size(); i++) {
				Instruction instruction = listOfInstructions.get(i);
				if (null == instruction.getVariabl()) {
					pos++;
					switch (instruction.getParam()) {
					case "w":
						w = Integer.valueOf((Long.toString(highest).substring(pos, pos + 1)));
						break;
					case "x":
						x = Integer.valueOf((Long.toString(highest).substring(pos, pos + 1)));
						break;
					case "y":
						y = Integer.valueOf((Long.toString(highest).substring(pos, pos + 1)));
						break;
					default:
						z = Integer.valueOf((Long.toString(highest).substring(pos, pos + 1)));
					}
				} else {
					int variable = 0;
					if (StringUtils.isNumeric(instruction.getVariabl())) {
						variable = Integer.parseInt(instruction.getVariabl());
					} else {
						switch (instruction.getVariabl()) {
						case "w":
							variable = w;
							break;
						case "x":
							variable = x;
							break;
						case "y":
							variable = y;
							break;
						default:
							variable = z;
						}
					}

					int target = 0;
					switch (instruction.getParam()) {
					case "w":
						target = w;
						break;
					case "x":
						target = x;
						break;
					case "y":
						target = y;
						break;
					default:
						target = z;
					}

					int result = 0;
					switch (instruction.getAction()) {
					case "add":
						result = target + variable;
						break;
					case "mul":
						result = target * variable;
						break;
					case "div":
						result = Math.floorDiv(target, variable);
						break;
					case "mod":
						result = target % variable;
						break;
					default:
						if (target == variable) {
							result = 1;
						} else {
							result = 0;
						}
					}
					switch (instruction.getParam()) {
					case "w":
						w = result;
						break;
					case "x":
						x = result;
						break;
					case "y":
						y = result;
						break;
					default:
						z = result;
					}
				}

			}
			if(z==0) {
				numberFound=true;
			}else {
				highest--;
				pos=-1;
			}
		}
		System.out.println("Maximum number is " + highest);
	}

	public void processInput(List<String> input) {
		int i = 0;
		while (i < input.size()) {
			String[] line = input.get(i).split("\\s");
			Instruction instruction = new Instruction(line[0], line[1], null);
			if(line.length>2) {
			 instruction.setVariabl(line[2]);
			}
			listOfInstructions.add(instruction);
			i++;
		}
	}

}
