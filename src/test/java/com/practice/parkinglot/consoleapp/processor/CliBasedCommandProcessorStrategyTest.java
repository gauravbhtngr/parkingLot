package com.practice.parkinglot.consoleapp.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.practice.parkinglot.consoleapp.command.ICommand;
import com.practice.parkinglot.core.BaseTest;

public class CliBasedCommandProcessorStrategyTest extends BaseTest {
	@Spy @InjectMocks
	protected CliBasedCommandProcessorStrategy<String> cliBasedCommandProcessorStrategy;
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
		cliBasedCommandProcessorStrategy = new CliBasedCommandProcessorStrategy();
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_loadMethod_withApplicationCommandNull() {
		cliBasedCommandProcessorStrategy.load(null, commandDelimiter, paramsDelimiter, commandParamSeparator);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_loadMethod_withApplicationCommandEmpty() {
		cliBasedCommandProcessorStrategy.load(new HashMap<>(), commandDelimiter, paramsDelimiter, commandParamSeparator);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_loadMethod_withCommandDelimiterNull() {
		cliBasedCommandProcessorStrategy.load(applicationCommands, null, paramsDelimiter, commandParamSeparator);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_loadMethod_withParamsDelimiterNull() {
		cliBasedCommandProcessorStrategy.load(applicationCommands, commandDelimiter, null, commandParamSeparator);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_loadMethod_withCommandParamSeparatorNull() {
		cliBasedCommandProcessorStrategy.load(applicationCommands, commandDelimiter, paramsDelimiter, null);
	}

	@Test
	public void test_loadMethod_withAllValidArg() {
		try {
			cliBasedCommandProcessorStrategy.load(applicationCommands, commandDelimiter, paramsDelimiter, commandParamSeparator);
		} catch (IllegalArgumentException e) {
			Assert.fail("Should not come here if all arguments are valid");
		}
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_extractCommand_withCommandEmpty() {
		cliBasedCommandProcessorStrategy.extractCommand("", commandDelimiter);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_extractCommand_withCommandNull() {
		cliBasedCommandProcessorStrategy.extractCommand(null, commandDelimiter);
	}

	@Test
	public void test_extractCommand_withCommandNotNull() {
		try {
			Assert.assertTrue(cliBasedCommandProcessorStrategy.extractCommand("create 6", commandDelimiter).equals("create"));
		} catch (IllegalArgumentException e) {
			Assert.fail("Should not come here");
		}
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_extractParams_withCommandEmpty() {
		cliBasedCommandProcessorStrategy.extractParams("", paramsDelimiter, commandParamSeparator);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_extractParams_withCommandNull() {
		cliBasedCommandProcessorStrategy.extractParams(null, paramsDelimiter, commandParamSeparator);
	}

	@Test
	public void test_extractParams_withNoParam() {
		try {
			Assert.assertTrue(cliBasedCommandProcessorStrategy.extractParams("create", paramsDelimiter, commandParamSeparator) == null);
		} catch (IllegalArgumentException e) {
			Assert.fail("Should not come here");
		}
	}

	@Test
	public void test_extractParams_withValidParams() {
		try {
			String[] params = cliBasedCommandProcessorStrategy.extractParams("create 6", paramsDelimiter, commandParamSeparator);
			Assert.assertTrue(params.length == 1);
			Assert.assertTrue(params[0].equals("6"));
		} catch (IllegalArgumentException e) {
			Assert.fail("Should not come here");
		}
	}

	@Test
	public void test_invokeMethod_withValidCommand() {
		String[] params = new String[1];
		params[0] = "6";
		Map<String, ICommand<String>> applicationCommands = Mockito.mock(Map.class);
		cliBasedCommandProcessorStrategy.load(applicationCommands, commandDelimiter, paramsDelimiter, commandParamSeparator);
		Mockito.when(applicationCommands.get(Matchers.anyString())).thenReturn(createCommand);
		Mockito.when(createCommand.support(Matchers.any())).thenReturn(true);
		Mockito.doNothing().when(createCommand).preProcess();
		Mockito.when(createCommand.execute(Matchers.any())).thenReturn("5");
		Mockito.doNothing().when(createCommand).postProcess();
		Mockito.when(applicationCommands.get(Matchers.anyString())).thenReturn(createCommand);
		Mockito.when(applicationCommands.get(Matchers.anyString())).thenReturn(createCommand);
		cliBasedCommandProcessorStrategy.invoke("create_parking_lot 6");
		Mockito.verify(createCommand).preProcess();
		Mockito.verify(createCommand).execute(Matchers.any());
		Mockito.verify(createCommand).postProcess();
	}
}
