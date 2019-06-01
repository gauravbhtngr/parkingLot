package com.practice.parkinglot.consoleapp.processor;

import java.util.Map;
import java.util.Optional;

import com.practice.parkinglot.consoleapp.command.ICommand;
import com.practice.parkinglot.consoleapp.exception.ApplicationException;
import com.practice.parkinglot.consoleapp.exception.CommandNotAllowedException;
import com.practice.parkinglot.core.Preconditions;
import com.practice.parkinglot.core.StringOperations;

public abstract class AbstractCommandProcessorStrategy<O> implements ICommandProcessorStrategy<O> {

	protected Map<String, ICommand<O>> applicationCommands;
	protected String commandDelimiter;
	protected String paramsDelimiter;
	protected String commandParamSeparator;

	/**
	 * Loads all the initial parameters that are required to process a command
	 *
	 * @param applicationCommands
	 * @param commandDelimiter
	 * @param paramsDelimiter
	 * @param commandParamSeparator
	 * @throws IllegalArgumentException is any of the param null
	 */
	@Override
	public void load(Map<String, ICommand<O>> applicationCommands,
	                 String commandDelimiter,
	                 String paramsDelimiter,
	                 String commandParamSeparator) {
		validateLoadParam(applicationCommands, commandDelimiter, paramsDelimiter, commandParamSeparator);
		this.applicationCommands = applicationCommands;
		this.commandDelimiter = commandDelimiter;
		this.paramsDelimiter = paramsDelimiter;
		this.commandParamSeparator = commandParamSeparator;
	}

	private void validateLoadParam(Map<String, ICommand<O>> applicationCommands,
	                               String commandDelimiter,
	                               String paramsDelimiter,
	                               String commandParamSeparator) {
		Preconditions.precondition(applicationCommands != null, "applicationCommands should not be null");
		Preconditions.precondition(!applicationCommands.isEmpty(), "applicationCommands should not be empty");
		Preconditions.precondition(commandDelimiter != null, "commandDelimiter should not be null");
		Preconditions.precondition(paramsDelimiter != null, "paramsDelimiter should not be null");
		Preconditions.precondition(commandParamSeparator != null, "commandParamSeparator should not be null");
	}

	/**
	 * takes whole string command as argument
	 * and extract application command from the string with use of
	 * provided commandDelimiter
	 * For ex: if provided string is create_parking_lot 6 and given
	 * commandDelimiter is " " then this method will return create_parking_lot
	 *
	 * @param command
	 * @param commandDelimiter
	 * @return application command string
	 * @throws IllegalArgumentException
	 */
	protected String extractCommand(String command, String commandDelimiter) {
		Preconditions.precondition(!StringOperations.isEmpty(command), "");
		String[] arg = command.trim().split(commandDelimiter);
		return arg[0];
	}

	/**
	 * takes whole string command as argument
	 * and extract application command params from the string with use of
	 * provided paramsDelimiter and commandParamSeparator
	 * For ex: if provided string is create_parking_lot 6 and given
	 * commandParamSeparator is " " then this method will return 6
	 *
	 * @param command
	 * @param paramsDelimiter
	 * @param commandParamSeparator
	 * @return application command params array
	 * @throws IllegalArgumentException
	 */
	protected String[] extractParams(String command, String paramsDelimiter, String commandParamSeparator) {
		Preconditions.precondition(!StringOperations.isEmpty(command), "");
		String[] arg;
		if ((arg = command.trim().split(commandParamSeparator)).length < 2) {
			return null;
		}
		String[] params = new String[arg.length - 1];
		for (int i = 1; i < arg.length; i++) {
			if (!StringOperations.isEmpty(arg[i])) {
				params[i - 1] = arg[i];
			}
		}
		return params;
	}

	/**
	 * Invoke application command for provided command
	 * and return the result of execution
	 *
	 * @param command
	 * @return execution result
	 * @throws IllegalArgumentException
	 * @throws ApplicationException
	 * @throws CommandNotAllowedException
	 */
	protected Optional<O> invoke(String command) {
		O returnObject = null;
		try {
			// extract application command from user input
			String userInput = extractCommand(command, commandDelimiter).trim();
			
			// get the command from applicationCommands map that was provided from ConsoleAppContext
			ICommand<O> applicationCommand = applicationCommands.get(userInput);
			
			// check if application command found and supported
			if (applicationCommand == null || !applicationCommand.support(userInput)) {
				return Optional.empty();
			}
			
			// execute pre process, notification, webhook such things can be done 
			applicationCommand.preProcess();

			// execute the command and get the result
			returnObject = applicationCommand.execute(extractParams(command, paramsDelimiter, commandParamSeparator));

			// post process 
			applicationCommand.postProcess();

		} catch (IllegalArgumentException e) {

		} catch (ApplicationException e) {

		} catch (CommandNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		return Optional.ofNullable(returnObject);
	}
}
