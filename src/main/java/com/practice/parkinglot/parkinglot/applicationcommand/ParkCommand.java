package com.practice.parkinglot.parkinglot.applicationcommand;

import com.practice.parkinglot.core.StringOperations;
import com.practice.parkinglot.parkinglot.constant.ParkingConstant;
import com.practice.parkinglot.parkinglot.parking.IParkingStrategy;
import com.practice.parkinglot.parkinglot.vehicle.Car;

public class ParkCommand extends AbstractCommand {

	public ParkCommand(IParkingStrategy strategy) {
		super(strategy);
	}

	/**
	 * checks if command is supported
	 * Supported command is park
	 *
	 * @param input
	 * @return true/false
	 */
	@Override
	public boolean support(String input) {
		return input != null && !StringOperations.isEmpty(input)
				&& input.trim().equalsIgnoreCase(ParkingConstant.PARK);
	}

	/**
	 * Park car with given color and registration no
	 *
	 * @param params
	 * @return
	 */
	@Override
	public String execute(String[] params) {
		validateParams(params, ParkingConstant.PARK, 2);
		Car car = new Car();
		car.setRegistrationNo(params[0]);
		car.setColor(params[1]);
		Integer slotNo = getStrategy().park(car);
		return String.format(ParkingConstant.PARK_MESSAGE, slotNo);
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
