package com.practice.parkinglot.parkinglot.vehicle;


import org.testng.Assert;
import org.testng.annotations.Test;

public class CarTest {
	
	@Test
	public void test_getters() {
		Car car = new Car();
		car.setColor("white");
		Assert.assertTrue(car.getColor().equals("white"));
		car.setRegistrationNo("1234");
		Assert.assertTrue(car.getRegistrationNo().equals("1234"));
	}
}
