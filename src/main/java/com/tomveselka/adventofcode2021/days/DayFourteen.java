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
		String path = "resources/Day14InputFull.txt";
		//String path = "resources/Day14InputTest.txt";
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
		int numberOfSteps = 40;
		for (int i = 0; i < template.length(); i++) {
			String letter = template.substring(i, i + 1);
			if (results.containsKey(letter)) {
				BigInteger value = results.get(letter).add(BigInteger.valueOf(1));
				results.replace(letter, value);
			} else {
				results.put(letter, BigInteger.valueOf(1));
			}
		}
		// printHashMap(results, numberOfRules);
		for (int i = 0; i < template.length() - 1; i++) {
			if (rules.containsKey(template.substring(i, i + 2))) {

				String letter = rules.get(template.substring(i, i + 2));
				// System.out.println("Letter:"+letter);
				String left = template.substring(i, i + 2).substring(0, 1).concat(letter);
				// System.out.println("Left="+left);
				String right = letter.concat(template.substring(i, i + 2).substring(1));
				// System.out.println("Right="+right);
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
		for (HashMap.Entry<String, BigInteger> entry : numberOfRules.entrySet()) {
			numberOfRules.put(entry.getKey(), entry.getValue());
			// System.out.println(entry.getKey()+":"+numberOfRules.get(entry.getKey()));
		}
		printHashMap(results, numberOfRules);
		int step = 1;
		iterateHashMap(numberOfRules, rules, results, step, numberOfSteps);

	}

	public void iterateHashMap(HashMap<String, BigInteger> numberOfRules, HashMap<String, String> rules,
			HashMap<String, BigInteger> results, int step, int numberOfSteps) {
		HashMap<String, BigInteger> numberOfRulesNew = new HashMap<String, BigInteger>();
		for (HashMap.Entry<String, BigInteger> entry : numberOfRules.entrySet()) {
			numberOfRulesNew.put(entry.getKey(), entry.getValue());
			// System.out.println(entry.getKey()+":"+numberOfRulesNew.get(entry.getKey()));
		}

		for (HashMap.Entry<String, BigInteger> entry : numberOfRules.entrySet()) {
			if (entry.getValue().compareTo(BigInteger.valueOf(0)) == 1) {
				// System.out.println("Original:"+entry.getKey());
				String middle = rules.get(entry.getKey());
				// System.out.println("Letter:"+middle);
				String key = entry.getKey();
				String left = key.substring(0, 1).concat(middle);
				String right = middle.concat(key.substring(1));
				// System.out.println("Left="+left);
				// System.out.println("Right="+right);

				BigInteger leftValue = numberOfRulesNew.get(left).add(entry.getValue());
				numberOfRulesNew.replace(left, leftValue);

				BigInteger rightValue = numberOfRulesNew.get(right).add(entry.getValue());
				numberOfRulesNew.replace(right, rightValue);

				if (results.containsKey(middle)) {
					BigInteger value = results.get(middle).add(entry.getValue());
					results.replace(middle, value);
				} else {
					results.put(middle, BigInteger.valueOf(1));
				}
				numberOfRulesNew.replace(entry.getKey(), numberOfRulesNew.get(key).subtract(entry.getValue()));

			}
		}
		step++;
		if (step < numberOfSteps) {
			System.out.println("Step nr. "+step);
			printHashMap(results, numberOfRulesNew);
			iterateHashMap(numberOfRulesNew, rules, results, step, numberOfSteps);
		} else {
			System.out.println("Step nr. "+step);
			printHashMap(results, numberOfRulesNew);
		}
	}

	public void printHashMap(HashMap<String, BigInteger> results, HashMap<String, BigInteger> numberOfRules) {
		/*
		 * for (HashMap.Entry<String, BigInteger> rule : numberOfRules.entrySet()) {
		 * System.out.println(rule.getKey()+"="+rule.getValue()); }
		 */
		for (HashMap.Entry<String, BigInteger> letter : results.entrySet()) {
			System.out.println(letter.getKey() + "=" + letter.getValue());
		}
		BigInteger max = Collections.max(results.values());
		BigInteger min = Collections.min(results.values());
		System.out.println("Max is " + max + ", Min is " + min + " and difference is " + (max.subtract(min)));
	}

}
