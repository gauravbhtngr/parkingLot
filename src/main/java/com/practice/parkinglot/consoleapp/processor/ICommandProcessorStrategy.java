package com.practice.parkinglot.consoleapp.processor;


import java.util.Map;
import java.util.function.Consumer;

import com.practice.parkinglot.consoleapp.command.ICommand;

public interface ICommandProcessorStrategy<O> {

	/**
	 * Used to load all the initial parameters
	 *
	 * @param applicationCommands   this is all the command that an application wants to support
	 * @param commandDelimiter
	 * @param paramsDelimiter
	 * @param commandParamSeparator
	 */
	void load(Map<String, ICommand<O>> applicationCommands,
	          String commandDelimiter,
	          String paramsDelimiter,
	          String commandParamSeparator);

	/**
	 * Any pre processing thing can be done here
	 */
	void preProcess();

	/**
	 * Process the provided command
	 * 
	 * @param cliArg
	 * @param consumer result of the command will be consumer by consumer
	 */
	void process(String[] cliArg, Consumer<O> consumer);

	/**
	 * Any post processing stuff can be done here
	 */
	void postProcess();
}
