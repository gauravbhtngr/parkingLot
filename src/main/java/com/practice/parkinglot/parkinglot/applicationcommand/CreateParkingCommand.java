package com.practice.parkinglot.parkinglot.applicationcommand;

import com.practice.parkinglot.consoleapp.exception.ApplicationException;
import com.practice.parkinglot.core.StringOperations;
import com.practice.parkinglot.parkinglot.constant.ParkingConstant;
import com.practice.parkinglot.parkinglot.parking.IParkingStrategy;

public class CreateParkingCommand extends AbstractCommand {

	public CreateParkingCommand(IParkingStrategy strategy) {
		super(strategy);
	}

	/**
	 * checks if command is supported
	 * Supported command is create_parking_lot
	 *
	 * @param input
	 * @return true/false
	 */
	@Override
	public boolean support(String input) {
		return input != null && !StringOperations.isEmpty(input)
				&& input.trim().equalsIgnoreCase(ParkingConstant.CREATE_PARKING);
	}

	/**
	 * create parking lot with given no of slots
	 *
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public String execute(String[] params) {
		validateParams(params, ParkingConstant.CREATE_PARKING, 1);
		Integer noOfSlots = null;
		try {
			noOfSlots = Integer.parseInt(params[0]);
		} catch (NumberFormatException e) {
			throw new ApplicationException("Please provide valid no to create slots");
		}
		getStrategy().create(noOfSlots);
		return String.format(ParkingConstant.CREATE_MESSAGE, noOfSlots);
	}

	/**
	 * Pre process before execution of command
	 */
	@Override
	public void preProcess() {

	}

	/**
	 * Post process after execution of command
	 */
	@Override
	public void postProcess() {

	}
}
