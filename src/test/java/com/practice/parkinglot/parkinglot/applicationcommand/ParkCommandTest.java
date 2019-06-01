package com.practice.parkinglot.parkinglot.applicationcommand;


import org.testng.annotations.BeforeClass;

public class ParkCommandTest extends AbstractCommandTest {

	@BeforeClass
	public void init() {
		command = new ParkCommand(strategy);
		invalidStrCommand = "parking";
		validStrCommand = "park";
		invalidParams = new String[0];
		String[] param = new String[2];
		param[0] = "white";
		param[1] = "1234";
	}
}
