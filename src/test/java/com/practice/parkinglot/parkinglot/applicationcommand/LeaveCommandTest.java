package com.practice.parkinglot.parkinglot.applicationcommand;


import org.testng.annotations.BeforeClass;

public class LeaveCommandTest extends AbstractCommandTest {

	@BeforeClass
	public void init() {
		command = new LeaveCommand(strategy);
		invalidStrCommand = "left";
		validStrCommand = "leave";
		invalidParams = new String[0];
		String[] param = new String[1];
		param[0] = "6";
	}
}
