package com.practice.parkinglot.consoleapp.context;


import java.util.Map;
import java.util.function.Consumer;

import com.practice.parkinglot.consoleapp.command.ICommand;
import com.practice.parkinglot.consoleapp.processor.CommandProcessorStrategyFactory;
import com.practice.parkinglot.consoleapp.processor.ICommandProcessorStrategy;
import com.practice.parkinglot.core.Preconditions;

/**
 * Console app context to prepare the setup
 * and invoke the application with provided setup
 * Generic implementation to provide flexibility 
 * This framework can adapt any type of command for any future implementation 
 * Like purchase orders commands, report generation commands etc.
 * This class can also be improved by providing chunk and delay processing features
 *
 * @param <O>
 */
public class ConsoleAppContext<O> {
	private Map<String, ICommand<O>> commands;
	private Consumer<O> consumer;
	private String[] cliArg;
	private String commandDelimiter;
	private String paramsDelimiter;
	private String commandParamSeparator;

	private ConsoleAppContext(Builder<O> builder) {
		this.commands = builder.commands;
		this.consumer = builder.consumer;
		this.cliArg = builder.cliArg;
		this.commandDelimiter = builder.commandDelimiter;
		this.paramsDelimiter = builder.paramsDelimiter;
		this.commandParamSeparator = builder.commandParamSeparator;
	}

	/**
	 * Load, pre process, process and post process the strategy
	 */
	public void run() {
		ICommandProcessorStrategy strategy = CommandProcessorStrategyFactory.getStrategy(cliArg);
		
		strategy.load(commands, commandDelimiter, paramsDelimiter, commandParamSeparator);
		
		strategy.preProcess();
		
		strategy.process(cliArg, consumer);
		
		strategy.postProcess();
	}

	
	
	/**
	 * Simple builder for ConsoleAppContext
	 * @param <O1>
	 */
	public static class Builder<O1> {
		private Map<String, ICommand<O1>> commands;
		private Consumer<O1> consumer;
		private String[] cliArg;
		private String commandDelimiter;
		private String paramsDelimiter;
		private String commandParamSeparator;

		public static <O1> Builder<O1> init() {
			return new Builder<O1>();
		}

		public Builder<O1> commands(Map<String, ICommand<O1>> commands) {
			this.commands = commands;
			return this;
		}

		public Builder<O1> consumer(Consumer<O1> consumer) {
			this.consumer = consumer;
			return this;
		}

		public Builder<O1> commandParamSeparator(String commandParamSeparator) {
			this.commandParamSeparator = commandParamSeparator;
			return this;
		}

		public Builder<O1> commandDelimiter(String commandDelimiter) {
			this.commandDelimiter = commandDelimiter;
			return this;
		}

		public Builder<O1> paramsDelimiter(String paramsDelimiter) {
			this.paramsDelimiter = paramsDelimiter;
			return this;
		}

		public Builder<O1> cliArg(String... cliArg) {
			this.cliArg = cliArg;
			return this;
		}

		public ConsoleAppContext build() {
			Preconditions.precondition(commandDelimiter != null, "commandDelimiter must be provided");
			Preconditions.precondition(paramsDelimiter != null, "paramsDelimiter must be provided");
			Preconditions.precondition(commandParamSeparator != null, "commandParamSeparator must be provided");
			Preconditions.precondition(cliArg != null, "cliArg must be provided");
			Preconditions.precondition(commands != null && !commands.isEmpty(), "commands must be provided");
			Preconditions.precondition(consumer != null , "consumer must be provided");
			return new ConsoleAppContext(this);
		}
	}
}
