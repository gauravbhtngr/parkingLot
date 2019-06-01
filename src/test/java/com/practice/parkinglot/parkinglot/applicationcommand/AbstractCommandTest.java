package com.practice.parkinglot.parkinglot.applicationcommand;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.parkinglot.consoleapp.command.ICommand;
import com.practice.parkinglot.core.BaseTest;
import com.practice.parkinglot.parkinglot.parking.ParkingLotInMemoryStrategy;

/**
 * Created by gaurav.bhatnagar on 6/17/18.
 */
public abstract class AbstractCommandTest extends BaseTest {

	@Mock
	protected ParkingLotInMemoryStrategy strategy;
	
	@Spy @InjectMocks
	protected ICommand<String> command;
	protected String invalidStrCommand;
	protected String validStrCommand;
	protected String[] invalidParams;
	
	@Test
	public void test_supportMethodWithInvalidArg() {
		Assert.assertFalse(command.support(null));
		Assert.assertFalse(command.support(" "));
		Assert.assertFalse(command.support(invalidStrCommand));
	}

	@Test
	public void test_supportMethodWithValidArg() {
		Assert.assertTrue(command.support(validStrCommand));
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testExecute_withInvalidParams() {
		command.execute(invalidParams);
	}
}
