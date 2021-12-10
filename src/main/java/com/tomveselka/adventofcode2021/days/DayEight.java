package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayEight {

	public static void main(String[] args) {
		DayEight dayEight = new DayEight();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day8InputFull.txt";
		//String path = "resources/Day8InputTest2.txt";
		List<String> input = rdr.readFileString(path);
		
		Set<String> one = new HashSet<String>();
		Set<String> two = new HashSet<String>();
		one.add("a");
		one.add("b");
		two.add("b");
		//System.out.println(one.containsAll(two));
		//System.out.println(two.containsAll(one));
		// dayEight.taskOne(input);
		dayEight.taskTwo(input);

	}

	public void taskOne(List<String> input) {
		int result = 0;
		for (int i = 0; i < input.size(); i++) {
			String[] split = input.get(i).split("\\|");
			// System.out.println(split[1].split(" "));
			String[] splitSecondHalf = split[1].split(" ");
			List<String> splitList = new java.util.ArrayList<>(Arrays.asList(splitSecondHalf));
			splitList.removeIf(String::isEmpty);
			System.out.println(splitList.toString());
			for (int j = 0; j < splitList.size(); j++) {
				int stringLength = splitList.get(j).replaceAll("\\s+", "").length();
				if (stringLength == 2 || stringLength == 3 || stringLength == 4 || stringLength == 7) {
					result++;
				}
			}

		}
		System.out.println("Result is " + result);
	}

	public void taskTwo(List<String> input) {
		int result = 0;
		for (int i = 0; i < input.size(); i++) {
			String[] split = input.get(i).split("\\|");
			// System.out.println(split[1].split(" "));
			String[] splitFirstHalf = split[0].split(" ");
			List<String> splitListFirst = new java.util.ArrayList<>(Arrays.asList(splitFirstHalf));
			splitListFirst.removeIf(String::isEmpty);

			String[] splitSecondHalf = split[1].split(" ");
			List<String> splitListSecond = new java.util.ArrayList<>(Arrays.asList(splitSecondHalf));
			splitListSecond.removeIf(String::isEmpty);

			//System.out.println("Processing row nr. " + i);
			//System.out.println("First part:" + splitListFirst.toString());
			//System.out.println("Second part:" + splitListSecond.toString());
			result = result + findNumbers(splitListFirst, splitListSecond);

		}
		System.out.println("Result is " + result);
	}

	public int findNumbers(List<String> input, List<String> numbers) {
		//System.out.println("Start" + input.toString());
		Set<Character> one = new HashSet<Character>();
		Set<Character> two = new HashSet<Character>();
		Set<Character> three = new HashSet<Character>();
		Set<Character> four = new HashSet<Character>();
		Set<Character> five = new HashSet<Character>();
		Set<Character> six = new HashSet<Character>();
		Set<Character> seven = new HashSet<Character>();
		Set<Character> eight = new HashSet<Character>();
		Set<Character> nine = new HashSet<Character>();
		Set<Character> zero = new HashSet<Character>();
		List<Character> oneList = new ArrayList<Character>();
		List<Character> fourList = new ArrayList<Character>();
		List<Character> sevenList = new ArrayList<Character>();
		List<Character> eightList = new ArrayList<Character>();
		boolean breakLoop = false;
		while (input.size() > 0 && !input.isEmpty() && !breakLoop) {

			int inputSize = input.size();
			for (int i = 0; i < inputSize; i++) {
				int stringLength = input.get(i).replaceAll("\\s+", "").length();
				// System.out.println("String length=" + stringLength);
				if (stringLength == 2&&one.isEmpty()) {
					oneList = getListFromArray(input.get(i).toCharArray());
					for (Character element : oneList) {
						one.add(element);
					}
					// System.out.println("One: " + one.toString());
					input.remove(i);
					// System.out.println(input.toString());
					break;
				}
				if (stringLength == 3&&seven.isEmpty()) {
					sevenList = getListFromArray(input.get(i).toCharArray());
					for (Character element : sevenList) {
						seven.add(element);
					}
					// System.out.println("Seven: " + seven.toString());
					input.remove(i);
					// System.out.println(input.toString());
					break;
				}
				if (stringLength == 4&&four.isEmpty()) {
					fourList = getListFromArray(input.get(i).toCharArray());
					for (Character element : fourList) {
						four.add(element);
					}
					// System.out.println("Four: " + four.toString());
					input.remove(i);
					// System.out.println(input.toString());
					break;
				}
				if (stringLength == 7&&eight.isEmpty()) {
					eightList = getListFromArray(input.get(i).toCharArray());
					for (Character element : eightList) {
						eight.add(element);
					}
					// System.out.println("Eight: " + eight.toString());
					input.remove(i);
					// System.out.println(input.toString());
					break;
				}
				if (zero.isEmpty()&&!eight.isEmpty()&&!four.isEmpty()&&!nine.isEmpty()&&!one.isEmpty()&&stringLength==6) {
					List<Character> unknownList = getListFromArray(input.get(i).toCharArray());
					Set<Character> unknownSet = new HashSet<Character>();
					for (Character element : unknownList) {
						unknownSet.add(element);
					}
					Set<Character> tempEight = new HashSet<Character>();
					tempEight.addAll(eight);
					char remaining='0';
					for (Iterator<Character> iterator = tempEight.iterator(); iterator.hasNext();) {
						char s = iterator.next();
						if (unknownSet.contains(s)) {
							iterator.remove();
						}else {
							remaining=s;
						}
					}
					if (!one.contains(remaining)) {
						zero.addAll(unknownSet);
						// System.out.println("Five: " + five);
						input.remove(i);
						// System.out.println(input.toString());
						break;
					}
				}
				if (three.isEmpty()&&!eight.isEmpty()&&!one.isEmpty()&&stringLength==5) {
					List<Character> unknownList = getListFromArray(input.get(i).toCharArray());
					Set<Character> unknownSet = new HashSet<Character>();
					for (Character element : unknownList) {
						unknownSet.add(element);
					}
					Set<Character> tempEight = new HashSet<Character>();
					tempEight.addAll(eight);

					for (Iterator<Character> iterator = tempEight.iterator(); iterator.hasNext();) {
						char s = iterator.next();
						if (unknownSet.contains(s)) {
							iterator.remove();
						}
					}
					int nrOfSameChars=0;
					for (Iterator<Character> iterator = tempEight.iterator(); iterator.hasNext();) {
						char s = iterator.next();
						if (one.contains(s)) {
							nrOfSameChars++;
						}
					}
					if (nrOfSameChars==0&&tempEight.size()==2) {
						three.addAll(unknownSet);
						// System.out.println("Five: " + five);
						input.remove(i);
						// System.out.println(input.toString());
						break;
					} 
				}
				if (nine.isEmpty()&&!four.isEmpty()&&stringLength==6) {
					List<Character> unknownList = getListFromArray(input.get(i).toCharArray());
					Set<Character> unknownSet = new HashSet<Character>();
					for (Character element : unknownList) {
						unknownSet.add(element);
					}
					Set<Character> tempFour = new HashSet<Character>();
					tempFour.addAll(four);
					for (Iterator<Character> iterator = tempFour.iterator(); iterator.hasNext();) {
						char s = iterator.next();
						if (unknownSet.contains(s)) {
							iterator.remove();
						}
					}
					if (tempFour.size()==0||tempFour.isEmpty()) {
						nine.addAll(unknownSet);
						// System.out.println("Five: " + five);
						input.remove(i);
						// System.out.println(input.toString());
						break;
					} 
				}
				if(six.isEmpty()&&!nine.isEmpty()&&!zero.isEmpty()&&stringLength==6) {
					List<Character> unknownList = getListFromArray(input.get(i).toCharArray());
					Set<Character> unknownSet = new HashSet<Character>();
					for (Character element : unknownList) {
						unknownSet.add(element);
					}
					six.addAll(unknownSet);
					// System.out.println("Five: " + five);
					input.remove(i);
					// System.out.println(input.toString());
					break;
				}
				if(two.isEmpty()&&!four.isEmpty()&&!three.isEmpty()&&stringLength==5) {
					List<Character> unknownList = getListFromArray(input.get(i).toCharArray());
					Set<Character> unknownSet = new HashSet<Character>();
					for (Character element : unknownList) {
						unknownSet.add(element);
					}
					Set<Character> tempUnkown = new HashSet<Character>();
					tempUnkown.addAll(unknownSet);
					char remaining='0';
					for (Iterator<Character> iterator = tempUnkown.iterator(); iterator.hasNext();) {
						char s = iterator.next();
						if (three.contains(s)) {
							iterator.remove();
						}else {
							remaining=s;
						}
					}
					if(!four.contains(remaining)) {
						two.addAll(unknownSet);
						// System.out.println("Five: " + five);
						input.remove(i);
						// System.out.println(input.toString());
						break;
					}
				}
				if(five.isEmpty()&&!two.isEmpty()&&!three.isEmpty()&&stringLength==5) {
					List<Character> unknownList = getListFromArray(input.get(i).toCharArray());
					Set<Character> unknownSet = new HashSet<Character>();
					for (Character element : unknownList) {
						unknownSet.add(element);
					}
					five.addAll(unknownSet);
					// System.out.println("Five: " + five);
					input.remove(i);
					// System.out.println(input.toString());
					break;
				}

			}
			//System.out.println(input.toString());
		}
		
		System.out.println("One: " + one);
		System.out.println("Two: " + two);
		System.out.println("Three: " + three);
		System.out.println("Four: " + four);
		System.out.println("Five: " + five);
		System.out.println("Six: " + six);
		System.out.println("Seven: " + seven);
		System.out.println("Eight: " + eight);
		System.out.println("Nine: " + nine);
		System.out.println("Zero: " + zero);
		
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < numbers.size(); i++) {
			String number = numbers.get(i).replaceAll("\\s+", "");
			if (number.length() == 2) {
				str.append("1");
			} else if (number.length() == 3) {
				str.append("7");
			} else if (number.length() == 4) {
				str.append("4");
			} else if (number.length() == 7) {
				str.append("8");
			} else {
				List<Character> list = getListFromArray(number.toCharArray());
				Set<Character> set = new HashSet<Character>();
				for (Character element : list) {
					set.add(element);
				}
				//System.out.println(set.toString());
				if (number.length() == 6 && set.containsAll(six) && six.containsAll(set)) {
					str.append("6");
				} else if (number.length() == 6 && set.containsAll(zero) && zero.containsAll(set)) {
					str.append("0");
				} else if (number.length() == 6 && set.containsAll(nine) && nine.containsAll(set)) {
					str.append("9");
				} else if (number.length() == 5 && set.containsAll(two) && two.containsAll(set)) {
					str.append("2");
				} else if (number.length() == 5 && set.containsAll(three) && three.containsAll(set)) {
					str.append("3");
				} else if (number.length() == 5 && set.containsAll(five) && five.containsAll(set)) {
					str.append("5");
				}
			}
		}
		System.out.println(str.toString());
		return Integer.parseInt(str.toString());
	}

	public List<Character> getListFromArray(char[] input) {
		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < input.length; i++) {
			list.add(input[i]);
		}
		return list;
	}

}
