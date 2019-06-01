package com.practice.parkinglot.consoleapp.processor;


import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import com.practice.parkinglot.consoleapp.exception.ApplicationException;
import com.practice.parkinglot.consoleapp.exception.CommandNotAllowedException;

public class CliBasedCommandProcessorStrategy<O> extends AbstractCommandProcessorStrategy<O> {

	/**
	 * No requirement for now but can be implemented in future
	 */
	@Override
	public void preProcess() {

	}

	/**
	 * Process the cli commands and invoke application command
	 * on the given command.
	 * Consume the execution result
	 *
	 * @param cliArg
	 * @param consumer
	 * @throws IllegalArgumentException
	 * @throws ApplicationException
	 * @throws CommandNotAllowedException
	 */
	@Override
	public void process(String[] cliArg, Consumer<O> consumer) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String command = scanner.nextLine();
			if (command.equalsIgnoreCase("exit")) {
				break;
			}
			Optional<O> object = invoke(command);
			
			if (object.isPresent()) {
				consumer.accept(object.get());
			}
		}
	}

	/**
	 * No requirement for now and can be implemented in future
	 */
	@Override
	public void postProcess() {

	}
}
