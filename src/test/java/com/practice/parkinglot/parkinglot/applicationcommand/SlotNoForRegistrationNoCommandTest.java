package com.practice.parkinglot.parkinglot.applicationcommand;

import org.testng.annotations.BeforeClass;

public class SlotNoForRegistrationNoCommandTest extends AbstractCommandTest {

	@BeforeClass
	public void init() {
		command = new SlotNoForRegistrationNoCommand(strategy);
		invalidStrCommand = "slot";
		validStrCommand = "slot_number_for_registration_number";
		invalidParams = new String[0];
		String[] param = new String[1];
		param[0] = "1";
	}
}
