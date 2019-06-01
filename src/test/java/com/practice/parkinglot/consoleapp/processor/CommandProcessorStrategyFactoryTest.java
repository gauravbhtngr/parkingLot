package com.practice.parkinglot.consoleapp.processor;


import org.junit.Assert;
import org.testng.annotations.Test;

public class CommandProcessorStrategyFactoryTest {
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_getStrategy_withNoArg() {
		CommandProcessorStrategyFactory.getStrategy(null);
	}

	@Test
	public void test_getStrategy_with_oneArg() {
		String[] params = new String[1];
		params[0] = "filename.txt";
		Assert.assertTrue(CommandProcessorStrategyFactory
				.getStrategy(params) instanceof TxtFileBasedCommandProcessorStrategy);
	}

	@Test
	public void test_getStrategy_with_0Arg() {
		Assert.assertTrue(CommandProcessorStrategyFactory
				.getStrategy(new String[0]) instanceof CliBasedCommandProcessorStrategy);
	}
}
