package com.practice.parkinglot.parkinglot.applicationcommand;


import org.testng.annotations.BeforeClass;

public class RegistrationNoWithColorCommandTest extends AbstractCommandTest {
	
	@BeforeClass
	public void init() {
		command = new RegistrationNoWithColorCommand(strategy);
		invalidStrCommand = "number";
		validStrCommand = "registration_numbers_for_cars_with_colour";
		invalidParams = new String[0];
		String[] param = new String[1];
		param[0] = "white";
	}
}
