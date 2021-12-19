package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayEighteen {

	public static void main(String[] args) {
		DayEighteen dayEighteen = new DayEighteen();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day18InputFull.txt";
		//String path = "resources/Day18InputTest.txt";
		List<String> input = rdr.readFileString(path);
		// List<String> input = new ArrayList<String>();
		// input.add("[[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]],[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]]");
		// dayEighteen.taskOne(input);
		dayEighteen.taskTwo(input);

		/*
		 * StringBuilder str = new StringBuilder(); str.append("1234");
		 * System.out.println(str.reverse());
		 */
	}

	public void taskOne(List<String> input) {

		String expression = processExpression(input.get(0));
		if (input.size() > 1) {
			for (int i = 1; i < input.size(); i++) {
				expression = "[" + expression + "," + input.get(i) + "]";
				// System.out.println(expression);
				expression = processExpression(expression);
			}
		}
		System.out.println("Final expression is:" + expression);

		// String
		// result="[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]";
		System.out.println("Magnitude of final sum is " + countResult(expression));
	}

	public void taskTwo(List<String> input) {
		// String expression = processExpression(input.get(0));

		List<Integer> listOfMagnitudes = new ArrayList<Integer>();
		for (int i = 0; i < input.size(); i++) {
			String firstExpression = input.get(i);
			for (int j = 0; j < input.size(); j++) {
				if (i != j) {
					String newExpression = "[" + firstExpression + "," + input.get(j) + "]";
					newExpression = processExpression(newExpression);
					int magnitude = countResult(newExpression);
					/*System.out.println("First expression is " + firstExpression + " second expression is "
						+ input.get(j) + "new Expression is " + newExpression + " and magnitude is " + magnitude);
						*/	
					listOfMagnitudes.add(magnitude);
				}
			}
		}
		System.out.println(listOfMagnitudes.toString());
		System.out.println("Maximum magnitude is " + Collections.max(listOfMagnitudes));

	}

	public String processExpression(String expression) {
		// System.out.println("Input:" + expression);
		int nestLevel = 0;
		int newEvents = 1;
		StringBuilder currentNumber = new StringBuilder();
		while (newEvents > 0) {
			newEvents = 0;
			// Explosions
			nestLevel = 0;
			for (int i = 0; i < expression.length(); i++) {
				String currentChar = expression.substring(i, i + 1);
				if ("[".equals(expression.substring(i, i + 1))) {
					nestLevel++;
					currentNumber.setLength(0);
				} else if ("]".equals(expression.substring(i, i + 1))) {
					nestLevel--;
					currentNumber.setLength(0);
				} else if (StringUtils.isNumeric(expression.substring(i, i + 1))) {
					currentNumber.append(expression.substring(i, i + 1));
				} else {
					currentNumber.setLength(0);
				}
				if (nestLevel > 4) {
					expression = explode(expression, i);
					currentNumber.setLength(0);
					nestLevel--;
					newEvents++;
				}
			}
			// Splits
			for (int i = 0; i < expression.length(); i++) {
				String currentChar = expression.substring(i, i + 1);
				if ("[".equals(expression.substring(i, i + 1))) {
					currentNumber.setLength(0);
				} else if ("]".equals(expression.substring(i, i + 1))) {
					currentNumber.setLength(0);
				} else if (StringUtils.isNumeric(expression.substring(i, i + 1))) {
					currentNumber.append(expression.substring(i, i + 1));
				} else {
					currentNumber.setLength(0);
				}
				if (currentNumber.length() > 0 && Integer.parseInt(currentNumber.toString()) >= 10) {
					expression = split(expression, i, Integer.parseInt(currentNumber.toString()));
					currentNumber.setLength(0);
					newEvents++;
					break;
				}
			}
		}

		return expression;
	}

	public String explode(String expression, int pos) {
		int leftNumber = 0;
		int rightNumber = 0;
		int commaPos = 0;
		int endPos = 0;
		StringBuilder str = new StringBuilder();
		str.append(expression);
		for (int i = pos + 1; i < str.length(); i++) {
			if (",".equals(str.substring(i, i + 1))) {
				leftNumber = Integer.parseInt(str.substring(pos + 1, i));
				commaPos = i;
			}
			if ("]".equals(str.substring(i, i + 1))) {
				rightNumber = Integer.parseInt(str.substring(commaPos + 1, i));
				endPos = i;
				break;
			}
		}
		StringBuilder strNext = new StringBuilder();
		for (int i = endPos + 1; i < str.length(); i++) {
			if (StringUtils.isNumeric(str.substring(i, i + 1))) {
				strNext.append(str.substring(i, i + 1));
			} else if (strNext.length() > 0 && !StringUtils.isNumeric(str.substring(i, i + 1))) {
				String newNumber = String.valueOf(rightNumber + Integer.parseInt(strNext.toString()));
				str.replace(i - strNext.length(), i, newNumber);
				break;
			}
		}
		strNext.setLength(0);
		str.replace(pos, endPos + 1, "0");
		for (int i = pos - 1; i > 0; i--) {
			if (StringUtils.isNumeric(str.substring(i, i + 1))) {
				strNext.append(str.substring(i, i + 1));
			} else if (strNext.length() > 0 && !StringUtils.isNumeric(str.substring(i, i + 1))) {
				strNext.reverse();
				String newNumber = String.valueOf(leftNumber + Integer.parseInt(strNext.toString()));
				str.replace(i + 1, i + 1 + strNext.length(), newNumber);
				break;
			}
		}
		// System.out.println("After explosion:" + str.toString());

		return str.toString();
	}

	public String split(String expression, int pos, int number) {
		int leftNumber = (int) Math.floor((double) number / 2);
		int rightNumber = (int) Math.ceil((double) number / 2);
		String replacement = "[" + leftNumber + "," + rightNumber + "]";
		StringBuilder str = new StringBuilder();
		str.append(expression);
		str.replace(pos - String.valueOf(number).length() + 1, pos + 1, replacement);
		// System.out.println("After split:" + str.toString());
		return str.toString();
	}

	public int countResult(String expression) {
		int result = 0;
		StringBuilder number = new StringBuilder();
		StringBuilder exprBuilder = new StringBuilder();
		int numberOfBrackets = 3;

		while (numberOfBrackets > 1) {
			numberOfBrackets = 0;
			exprBuilder.setLength(0);
			for (int i = 0; i < expression.length(); i++) {
				exprBuilder.append(expression.substring(i, i + 1));
				// System.out.println("Expr:" + exprBuilder.toString());
				if ("]".equals(expression.substring(i, i + 1))
						&& StringUtils.isNumeric(expression.substring(i - 1, i))) {
					number.append(expression.substring(i, i + 1));
					for (int j = i - 1; j > 0; j--) {
						number.append(expression.substring(j, j + 1));
						// System.out.println("Number:" + number);
						if ("[".equals(expression.substring(j, j + 1))) {
							String calcRes = calculate(number.reverse().toString());
							exprBuilder.replace(exprBuilder.length() - number.length(), exprBuilder.length(), calcRes);
							number.setLength(0);
							break;
						} else if ("]".equals(expression.substring(j, j + 1))) {
							number.setLength(0);
							break;
						}
					}

				}

			}
			for (int i = 0; i < exprBuilder.toString().length(); i++) {
				if ("]".equals(exprBuilder.substring(i, i + 1))) {
					numberOfBrackets++;
				}
			}
			expression = exprBuilder.toString();
		}

		return Integer.parseInt(calculate(exprBuilder.toString()));
	}

	public String calculate(String bracket) {
		int pos = 0;
		bracket = bracket.substring(1, bracket.length() - 1);
		StringBuilder str = new StringBuilder();
		int firstNumber = 0;
		int secondNumber = 0;
		while (!",".equals(bracket.substring(pos, pos + 1))) {
			str.append(bracket.substring(pos, pos + 1));
			pos++;
		}
		firstNumber = Integer.parseInt(str.toString());
		str.setLength(0);
		for (int i = pos + 1; i < bracket.length(); i++) {
			str.append(bracket.substring(i, i + 1));
		}
		secondNumber = Integer.parseInt(str.toString());
		str.setLength(0);
		int result = firstNumber * 3 + secondNumber * 2;
		return String.valueOf(result);
	}

}
