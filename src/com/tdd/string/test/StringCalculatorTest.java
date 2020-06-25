package com.tdd.string.test;

import org.junit.Assert;
import org.junit.Test;

import com.tdd.string.calc.StringCalculator;

public class StringCalculatorTest {

	// Requirement 1 : input to be within 2 numbers
	@Test
	public final void isWithinTwoInputs() {
		Assert.assertEquals(3, StringCalculator.add("1,2"));
	}

	// Requirement 1 - added to test the failed scenario
	// @Test(expected=RuntimeException.class)
	public final void isMoreThanTwoInputs() {
		StringCalculator.add("1,2,3");
	}

	// Requirement 2 : no input constraint
	@Test
	public final void isAnyNumInputs() {
		Assert.assertEquals(15, StringCalculator.add("1,2,3,4,5"));
	}

	// Requirement 3 : to consider , and /n as delimiter
	@Test
	public final void isNewLineAsDelimeter() {
		Assert.assertEquals(6, StringCalculator.add("1\n2,3"));
	}

	// Requirement 4 : to support various delimiters
	@Test
	public final void isOtherDelimiters() {
		Assert.assertEquals(3, StringCalculator.add("//;\n1;2"));
	}

	// Requirement 5,6 : display negative number{s} with error message
	@Test(expected = RuntimeException.class)
	public final void isNegativeNumbers() {
		StringCalculator.add("1,2,-3");
	}

	// Requirement 7: number of times method has been invoked
	@Test
	public final void isMethodInvoked() {
		// TODO to write test case
	}

	// Requirement 9: ignore bigger numbers
	@Test
	public final void isNegativeNumber() {
		Assert.assertEquals(1006, StringCalculator.add("1,2,1001,3,1000"));
	}

	// Requirement 10: delimiter of any length
	@Test
	public final void isDelimeterAnyLength() {
		Assert.assertEquals(6, StringCalculator.add("//[---]\n1---2---3"));
	}

}
