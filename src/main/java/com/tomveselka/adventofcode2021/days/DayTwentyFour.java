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

	List<List<Instruction>> listOfInstructions = new ArrayList<List<Instruction>>();
	List<Integer> inputParam = new ArrayList<Integer>();

	public void taskOne(List<String> input) {
		processInput(input);
		String highest="99999999999999";
		StringBuilder finalNumber = new StringBuilder();
		for (int i = 0; i < 14; i++) {
			int w = 0;
			int x = 0;
			int y = 0;
			int z = 0;
			for (int num = 9; num > 0; num--) {
				String[] firstLineSplit = input.get(inputParam.get(i)).split("\\s");
				switch (firstLineSplit[1]) {
				case "w":
					w = num;
					break;
				case "x":
					x = num;
					break;
				case "y":
					y = num;
					break;
				default:
					z = num;
				}
				List<Instruction> instructions = listOfInstructions.get(i);
				for (int j = 0; j < instructions.size(); j++) {
					Instruction instruction = instructions.get(j);

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

				if(z==0) {
					finalNumber.append(num);
					break;
				}

			}
		}
		System.out.println("Maximum number is "+finalNumber.toString());
	}

	public void processInput(List<String> input) {
		int i = 0;
		while (i < input.size()) {
			if (input.get(i).contains("inp")) {
				inputParam.add(i);
				List<Instruction> instructionList = new ArrayList<Instruction>();
				int j = i + 1;
				while (j<input.size()&&!input.get(j).contains("inp")) {
					String[] line = input.get(j).split("\\s");
					Instruction instruction = new Instruction(line[0], line[1], line[2]);
					instructionList.add(instruction);
					j++;
				}
				listOfInstructions.add(instructionList);
				i = j;
			}
		}
	}

}
