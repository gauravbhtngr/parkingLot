package com.practice.parkinglot;


import com.practice.parkinglot.consoleapp.context.ConsoleAppContext;
import com.practice.parkinglot.parkinglot.parking.ParkingHelper;

public class Application {
	public static void main(String[] args) {
		// Build the console app framework's context with all the setup params
		ConsoleAppContext
				.Builder
				.<String>init()
				// set all the application supported commands
				.commands(ParkingHelper.getCommands())
				// set the consumer that will consume the command output
				.consumer(System.out::println)
				// set command delimiter 
				.commandDelimiter(" ")
				// set params delimiter
				.paramsDelimiter(" ")
				// set command and params separator delimiter
				.commandParamSeparator(" ")
				// feed the user input
				.cliArg(args)
				// build the context
				.build()
				// Run console app
				.run();
	}
}
