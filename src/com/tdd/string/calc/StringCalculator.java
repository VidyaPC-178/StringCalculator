package com.tdd.string.calc;

import java.util.ArrayList;
import java.util.List;

/***
 * String Calculator class to print the sum of given string with special
 * delimiters based on TDD approach
 * 
 * @author: Vidya
 * @version: 1.0 
 * Date: 24/06/2020
 *
 */

public class StringCalculator {
	public static String EMPTY_STRING = "";
	public static String SPCL_STRING = "//";
	public static int count = 0;
	public static String numWithDelimiter = EMPTY_STRING;
	public static String delimiter = EMPTY_STRING;
	public static String PIPE_LINE = "|";
	public static String NEW_LINE = "\n";

	public static int add(String numbers) {
		int sum = 0;

		// to count the number of times method called
		count = getCalledCount();

		if (numbers.startsWith(SPCL_STRING)) {
			getNumDelimiter(numbers);
		} else {
			delimiter = ","+PIPE_LINE+NEW_LINE;
			numWithDelimiter = numbers;
		}

		String[] strArr = numWithDelimiter.split(delimiter);

		// Requirement 2: removed input constraint
		// if(strArr.length<=2) {
		List<Integer> negativeList = new ArrayList<Integer>();
		for (String num : strArr) {
			if (!num.isEmpty()) {
				int number = Integer.parseInt(num);
				if (number < 0) { // Requirement 5: to avoid negative numbers
					negativeList.add(number);
				} else if (number <= 1000) { // Requirement 9: to avoid bigger numbers
					sum += number;
				}
			}
		}
		if (negativeList.size() > 0) {
			throw new RuntimeException("Negative numbers not allowed: " + negativeList.toString());
		}
		/*
		 * } else { throw new
		 * RuntimeException("Input cannot exceed more than 2 numbers!!"); }
		 */
		return sum;
	}

	private static void getNumDelimiter(String numbers) {
		int startIndex = numbers.indexOf(SPCL_STRING);
		int endIndex = numbers.indexOf(NEW_LINE);
		String delString = numbers.substring(startIndex + 2, endIndex + 1);
		delimiter = getDelimiters(delString);
		numWithDelimiter = numbers.substring(endIndex + 1);
	}

	public static int getCalledCount() {
		count++;
		return count;
	}

	private static String getDelimiters(String delString) {
		List<String> delimiterList = new ArrayList<String>();
		if (delString.contains("[") && delString.contains("]")) {
			int startIndex = 0, endIndex = 0;
			int length = delString.indexOf(NEW_LINE);
			for (int i = 0; i < length; i++) {
				if (delString.charAt(i) == ('[')) {
					startIndex = i + 1;
				} else if (delString.charAt(i) == (']')) {
					endIndex = i;
				}
				if (startIndex != 0 && endIndex != 0) {
					delimiterList.add(delString.substring(startIndex, endIndex));
					startIndex = 0;
					endIndex = 0;
				}
			}
			delimiter = String.join(PIPE_LINE, delimiterList);
		} else {
			delimiter = delString.substring(0, 1) + PIPE_LINE + delString.substring(1);
		}
		return delimiter;
	}

}