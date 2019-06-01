package com.practice.parkinglot.core;


import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
}
