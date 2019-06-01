package com.practice.parkinglot.consoleapp.processor;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.practice.parkinglot.consoleapp.exception.ApplicationException;
import com.practice.parkinglot.consoleapp.exception.CommandNotAllowedException;
import com.practice.parkinglot.core.Preconditions;

public class TxtFileBasedCommandProcessorStrategy<O> extends AbstractCommandProcessorStrategy<O> {

	/**
	 * No requirement for now but can be implemented in future
	 */
	@Override
	public void preProcess() {

	}

	/**
	 * Will take whole cli arg, read the file name provided
	 * read all the file commands and invoke application command
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
		Preconditions.precondition(cliArg != null, "cliArg must be provided having valid file names");
		for (String command : cliArg) {
			if (command.endsWith(".txt")) {
				try (Stream<String> lines = Files.lines(Paths.get(command))) {
					lines.forEach(applicationCommand -> {
						
						Optional<O> object = invoke(applicationCommand);
						
						if (object.isPresent()) {
							consumer.accept(object.get());
						}
					});
				} catch (IOException e) {
					System.out.println("Not a valid file");
				}
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
