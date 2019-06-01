package com.practice.parkinglot.parkinglot.applicationcommand;

import com.practice.parkinglot.consoleapp.exception.ApplicationException;
import com.practice.parkinglot.core.StringOperations;
import com.practice.parkinglot.parkinglot.constant.ParkingConstant;
import com.practice.parkinglot.parkinglot.parking.IParkingStrategy;

public class LeaveCommand extends AbstractCommand {

	public LeaveCommand(IParkingStrategy strategy) {
		super(strategy);
	}

	/**
	 * checks if command is supported
	 * Supported command is leave
	 *
	 * @param input
	 * @return true/false
	 */
	@Override
	public boolean support(String input) {
		return input != null && !StringOperations.isEmpty(input)
				&& input.trim().equalsIgnoreCase(ParkingConstant.LEAVE);
	}

	/**
	 * Leave parking for given slot number
	 *
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public String execute(String[] params) {
		validateParams(params, ParkingConstant.LEAVE, 1);
		Integer slotNo = null;
		try {
			slotNo = Integer.parseInt(params[0]);
		} catch (NumberFormatException e) {
			throw new ApplicationException("Please provide valid slot no");
		}
		getStrategy().leave(slotNo);
		return String.format(ParkingConstant.LEAVE_MESSAGE, slotNo);
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
