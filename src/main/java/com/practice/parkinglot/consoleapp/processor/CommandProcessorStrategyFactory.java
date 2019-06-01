package com.practice.parkinglot.consoleapp.processor;

import com.practice.parkinglot.core.Preconditions;


public class CommandProcessorStrategyFactory {
	
	public static ICommandProcessorStrategy getStrategy(String... arg) {
		Preconditions.precondition(arg != null, "Arg must not be null");
		if (arg.length > 0) {
			return new TxtFileBasedCommandProcessorStrategy();
		} else {
			return new CliBasedCommandProcessorStrategy();
		}
	}
}
