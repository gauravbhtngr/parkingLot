package com.practice.parkinglot.parkinglot.applicationcommand;

import org.testng.annotations.BeforeClass;


public class CreateParkingCommandTest extends AbstractCommandTest {
	
	@BeforeClass
	public void init() {
		command = new CreateParkingCommand(strategy);
		invalidStrCommand = "create";
		validStrCommand = "create_parking_lot";
		invalidParams = new String[0];
		String[] param = new String[1];
		param[0] = "6";
	}
}
