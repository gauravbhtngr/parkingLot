package com.practice.parkinglot.parkinglot.applicationcommand;

import com.practice.parkinglot.consoleapp.command.ICommand;
import com.practice.parkinglot.core.Preconditions;
import com.practice.parkinglot.parkinglot.parking.IParkingStrategy;


public abstract class AbstractCommand implements ICommand<String> {
	private final IParkingStrategy strategy;

	public AbstractCommand(IParkingStrategy strategy) {
		this.strategy = strategy;
	}

	protected IParkingStrategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Validate the given params
	 *
	 * @param params
	 * @param command
	 * @param noOfParams
	 */
	protected void validateParams(String[] params, String command, int noOfParams) {
		Preconditions.precondition(params != null, "Invalid no of params for " + command + "command");
		Preconditions.precondition(params.length == noOfParams, "Invalid no of params for " + command + "command");
	}
}
