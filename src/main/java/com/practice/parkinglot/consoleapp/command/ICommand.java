package com.practice.parkinglot.consoleapp.command;

/**
 * Application commands that need to be supported by this freamwork
 * The generic param tell the type of output it will return
 *
 * @param <O>
 */
public interface ICommand<O> {
	/**
	 * Decides if command is supported or not
	 *
	 * @param input
	 * @return true/false
	 */
	boolean support(String input);

	/**
	 * Pre processing purpose
	 */
	void preProcess();

	/**
	 * Takes the params and execute the commend
	 *
	 * @param params
	 * @return
	 */
	O execute(String[] params);

	/**
	 * Post processing purpose
	 */
	void postProcess();
}
