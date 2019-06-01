package com.practice.parkinglot.parkinglot.slot;


import org.testng.Assert;
import org.testng.annotations.Test;

public class FixedAreaSlotTest {
	
	@Test
	public void testGetSlotNo() {
		FixedAreaSlot slot = new FixedAreaSlot(1);
		Assert.assertTrue(slot.getSlotNo() == 1);
	}

	@Test
	public void testEquals() {
		FixedAreaSlot slot = new FixedAreaSlot(1);
		Assert.assertFalse(slot.equals(null));
		FixedAreaSlot newSlot = new FixedAreaSlot(1);
		Assert.assertTrue(slot.equals(newSlot));
	}
}
