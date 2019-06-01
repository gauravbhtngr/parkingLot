package com.practice.parkinglot.parkinglot.applicationcommand;


import org.testng.annotations.BeforeClass;

public class SlotNoWithColorCommandTest extends AbstractCommandTest {
	
	@BeforeClass
	public void init() {
		command = new SlotNoWithColorCommand(strategy);
		invalidStrCommand = "slot";
		validStrCommand = "slot_numbers_for_cars_with_colour";
		invalidParams = new String[0];
		String[] param = new String[1];
		param[0] = "white";
	}
}
