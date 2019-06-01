package com.practice.parkinglot.core;


import org.testng.Assert;
import org.testng.annotations.Test;

public class StringOperationTest {
	@Test
	public void test_isEmpty_withStringNull() {
		Assert.assertTrue(StringOperations.isEmpty(null));
	}

	@Test
	public void test_isEmpty_withEmptyString() {
		Assert.assertTrue(StringOperations.isEmpty(" "));
	}

	@Test
	public void test_isEmpty_withValidString() {
		Assert.assertFalse(StringOperations.isEmpty("Test string"));
	}
}
