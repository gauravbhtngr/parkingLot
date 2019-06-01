package com.practice.parkinglot.consoleapp.processor;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.practice.parkinglot.consoleapp.command.ICommand;
import com.practice.parkinglot.core.BaseTest;

public class TxtFileBasedCommandProcessorStrategyTest extends BaseTest {
	protected TxtFileBasedCommandProcessorStrategy<String> txtFileBasedCommandProcessorStrategy;
	protected Map<String, ICommand<String>> applicationCommands;
	protected String commandDelimiter;
	protected String paramsDelimiter;
	protected String commandParamSeparator;

	@Mock
	protected ICommand<String> createCommand;
	@Mock
	protected Consumer<String> consumer;

	@BeforeMethod
	public void init() {
		applicationCommands = new HashMap<>();
		applicationCommands.put("create", createCommand);
		commandDelimiter = " ";
		paramsDelimiter = " ";
		commandParamSeparator = " ";
		txtFileBasedCommandProcessorStrategy = new TxtFileBasedCommandProcessorStrategy();
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_Execute_withCliArgNull() {
		txtFileBasedCommandProcessorStrategy.load(applicationCommands, commandDelimiter, paramsDelimiter, commandParamSeparator);
		txtFileBasedCommandProcessorStrategy.process(null, consumer);
	}
}
