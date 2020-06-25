package com.tdd.string.calc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/***
 * String Calculator class to print the sum of given string with special
 * delimiters based on TDD approach
 * 
 * @author: Vidya
 * @version: 1.0 Date: 24/06/2020
 *
 */
public class StringCalculator {
	public static String EMPTY_STRING = "";
	public static String SPCL_STRING = "//";
	public static int count = 0;

	public static int add(String numbers) {
		// to count the number of times method called
		count = getCalledCount();
		System.out.println(count);

		String delimiter = EMPTY_STRING;
		String numWithDelimiter = EMPTY_STRING;
		if (numbers.startsWith(SPCL_STRING)) {
			String[] numWithDelimiterArr = getNumDelimiter(numbers);
			delimiter = (String) Array.get(numWithDelimiterArr, 0);
			numWithDelimiter = (String) Array.get(numWithDelimiterArr, 1);
		} else {
			delimiter = ",|\n";
			numWithDelimiter = numbers;
		}
		String[] strArr = numWithDelimiter.split(delimiter);

		// Requirement 2: removed input constraint
		// if(strArr.length<=2) {
		int sum = 0;
		List<Integer> negativeList = new ArrayList<Integer>();
		for (String num : strArr) {
			if (!num.isEmpty()) {
				int number = Integer.parseInt(num);
				if (number < 0) { //Requirement 5: to avoid negative numbers
					negativeList.add(number);
				} else if (number <= 1000) { //Requirement 9: to avoid bigger numbers
					sum += number;
				}

			}

		}
		if (negativeList.size() > 0) {
			throw new RuntimeException("Negatives not allowed: " + negativeList.toString());
		}
		return sum;
		/*
		 * } else { throw new
		 * RuntimeException("Input cannot exceed more than 2 numbers!!"); }
		 */

	}

	private static String[] getNumDelimiter(String numbers) {
		String delimiters = EMPTY_STRING;
		int startIndex = numbers.indexOf(SPCL_STRING);
		int endIndex = numbers.indexOf("\n");
		String delString = numbers.substring(startIndex + 2, endIndex + 1);
         if(delString.contains("[") && delString.contains("]")) {
        	 delimiters = delString.substring(delString.indexOf("[") + 1,delString.indexOf("]"));
		}else {
			delimiters =  delString.substring(0, 1) + "|" + delString.substring(1);
		}
		
		String numWithoutDelimiters = numbers.substring(endIndex + 1);
		String[] numDelimiterArr = { delimiters, numWithoutDelimiters };
		return numDelimiterArr;
	}

	public static int getCalledCount() {
		count++;
		return count;
	}

}
