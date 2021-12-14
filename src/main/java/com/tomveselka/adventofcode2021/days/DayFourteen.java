package com.tomveselka.adventofcode2021.days;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.Templates;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayFourteen {

	public static void main(String[] args) {
		DayFourteen dayFourteen = new DayFourteen();
		FileReaderCustom rdr = new FileReaderCustom();
		// String path = "resources/Day14InputFull.txt";
		String path = "resources/Day14InputTest.txt";
		List<String> input = rdr.readFileString(path);
		// dayFourteen.taskOne(input);
		dayFourteen.taskTwo(input);
	}

	public void taskOne(List<String> input) {
		String template = input.get(0);
		System.out.println("Template: " + template);
		HashMap<String, String> rules = new HashMap<String, String>();
		for (int i = 2; i < input.size(); i++) {
			String[] splitLine = input.get(i).split(" -> ");
			rules.put(splitLine[0], splitLine[1]);
		}
		int step = 0;
		int numberOfSteps = 10;
		insertElements(template, rules, step, numberOfSteps);
	}

	public void insertElements(String template, HashMap<String, String> rules, int step, int numberOfSteps) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < template.length() - 1; i++) {
			if (rules.containsKey(template.substring(i, i + 2))) {
				str.append(template.substring(i, i + 1));
				str.append(rules.get(template.substring(i, i + 2)));
			} else {
				str.append(template.substring(i, i + 1));
			}
			if (i == template.length() - 2) {
				str.append(template.substring(i + 1, i + 2));
			}
		}
		// System.out.println("After step "+(step+1)+": "+str.toString());
		step++;
		System.out.println(step);
		if (step < numberOfSteps) {
			insertElements(str.toString(), rules, step, numberOfSteps);
		} else {
			calculateResult(str.toString());
		}

	}

	public void calculateResult(String template) {
		HashMap<String, Long> rules = new HashMap<String, Long>();
		for (int i = 0; i < template.length(); i++) {
			String letter = template.substring(i, i + 1);
			if (rules.containsKey(letter)) {
				Long value = rules.get(letter) + 1;
				rules.replace(letter, value);
			} else {
				rules.put(letter, (long) 1);
			}
		}
		long max = Collections.max(rules.values());
		long min = Collections.min(rules.values());
		System.out.println("Max is " + max + ", Min is " + min + " and difference is " + (max - min));
	}

	public void taskTwo(List<String> input) {
		String template = input.get(0);
		System.out.println("Template: " + template);
		HashMap<String, String> rules = new HashMap<String, String>();
		HashMap<String, BigInteger> numberOfRules = new HashMap<String, BigInteger>();
		HashMap<String, BigInteger> results = new HashMap<String, BigInteger>();
		for (int i = 2; i < input.size(); i++) {
			String[] splitLine = input.get(i).split(" -> ");
			rules.put(splitLine[0], splitLine[1]);
			numberOfRules.put(splitLine[0], BigInteger.valueOf(0));
		}
		int numberOfSteps = 10;
		for (int i = 0; i < template.length(); i++) {
			String letter = template.substring(i, i + 1);
			if (results.containsKey(letter)) {
				BigInteger value = results.get(letter).add(BigInteger.valueOf(1));
				results.replace(letter, value);
			} else {
				results.put(letter, BigInteger.valueOf(1));
			}
		}
		//printHashMap(results, numberOfRules);
		for (int i = 0; i < template.length() - 1; i++) {
			if (rules.containsKey(template.substring(i, i + 2))) {
				numberOfRules.replace(template.substring(i, i + 2), numberOfRules.get(template.substring(i, i + 2)).subtract(BigInteger.valueOf(1)));
				String letter = rules.get(template.substring(i, i + 2));
				String left = template.substring(i, i + 2).substring(0, 1).concat(letter);
				String right = letter.concat(template.substring(i, i + 2).substring(1));
				if (numberOfRules.containsKey(left)) {
					BigInteger value = numberOfRules.get(left).add(BigInteger.valueOf(1));
					numberOfRules.replace(left, value);
				}
				if (numberOfRules.containsKey(right)) {
					BigInteger value = numberOfRules.get(right).add(BigInteger.valueOf(1));
					numberOfRules.replace(right, value);
				}
				if (results.containsKey(letter)) {
					BigInteger value = results.get(letter).add(BigInteger.valueOf(1));
					results.replace(letter, value);
				} else {
					results.put(letter, BigInteger.valueOf(1));
				}
			}
		}
		printHashMap(results, numberOfRules);
		int step = 1;
		iterateHashMap(numberOfRules, rules, results, step, numberOfSteps);

	}

	public void iterateHashMap(HashMap<String, BigInteger> numberOfRules, HashMap<String, String> rules,
			HashMap<String, BigInteger> results, int step, int numberOfSteps) {
		HashMap<String, BigInteger> numberOfRulesNew = new HashMap<String, BigInteger>();
		for (HashMap.Entry<String, BigInteger> rule : numberOfRules.entrySet()) {
			numberOfRulesNew.put(rule.getKey(), rule.getValue());
		}
		for (HashMap.Entry<String, BigInteger> rule : numberOfRules.entrySet()) {
			if (rule.getValue().compareTo(BigInteger.valueOf(0))==1) {
					numberOfRulesNew.replace(rule.getKey(), BigInteger.valueOf(0));
					String middle = rules.get(rule.getKey());
					String key = rule.getKey();
					String left = key.substring(0, 1).concat(middle);
					String right = middle.concat(key.substring(1));

					if (numberOfRulesNew.containsKey(left)) {
						BigInteger value = numberOfRulesNew.get(left).add(rule.getValue());
						numberOfRulesNew.replace(left, value);
					}
					if (numberOfRulesNew.containsKey(right)) {
						BigInteger value = numberOfRulesNew.get(right).add(rule.getValue());
						numberOfRulesNew.replace(right, value);
					}

					if (results.containsKey(middle)) {
						BigInteger value = results.get(middle).add(rule.getValue());
						results.replace(middle, value);
					} else {
						results.put(middle, BigInteger.valueOf(1));
					}
				
			}
		}
		step++;
		if (step < numberOfSteps) {
			System.out.println(step);
			printHashMap(results, numberOfRulesNew);
			iterateHashMap(numberOfRulesNew, rules, results, step, numberOfSteps);
		} else {
			System.out.println(step);
			printHashMap(results, numberOfRulesNew);
		}
	}
	
	public void printHashMap(HashMap<String, BigInteger> results, HashMap<String, BigInteger> numberOfRules) {
		/*
		for (HashMap.Entry<String, BigInteger> rule : numberOfRules.entrySet()) {
			System.out.println(rule.getKey()+"="+rule.getValue());
		}*/
		for (HashMap.Entry<String, BigInteger> letter : results.entrySet()) {
			System.out.println(letter.getKey()+"="+letter.getValue());
		}
		BigInteger max = Collections.max(results.values());
		BigInteger min = Collections.min(results.values());
		System.out.println("Max is " + max + ", Min is " + min + " and difference is " + (max.subtract(min)));
	}

}
