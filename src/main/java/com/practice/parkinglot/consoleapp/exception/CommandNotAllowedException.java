package com.practice.parkinglot.consoleapp.exception;


/**
 * In case a command is not allow
 */
public class CommandNotAllowedException extends RuntimeException {
	public CommandNotAllowedException(String message) {
		super(message);
	}
}
