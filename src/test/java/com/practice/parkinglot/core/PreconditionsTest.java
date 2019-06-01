package com.practice.parkinglot.core;


import org.testng.Assert;
import org.testng.annotations.Test;

public class PreconditionsTest {
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_procondition_withConditionFalse() {
		Preconditions.precondition(false, "Testing exception");
	}

	@Test
	public void test_procondition_withConditionTrue() {
		try {
			Preconditions.precondition(true, "Testing exception");
		} catch (IllegalArgumentException e) {
			Assert.fail("Should not come here");
		}
	}
}
