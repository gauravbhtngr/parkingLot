package com.practice.parkinglot.parkinglot.applicationcommand;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.parkinglot.core.StringOperations;
import com.practice.parkinglot.parkinglot.constant.ParkingConstant;
import com.practice.parkinglot.parkinglot.parking.IParkingStrategy;
import com.practice.parkinglot.parkinglot.vehicle.Vehicle;

public class RegistrationNoWithColorCommand extends AbstractCommand {

	public RegistrationNoWithColorCommand(IParkingStrategy strategy) {
		super(strategy);
	}

	/**
	 * checks if command is supported
	 * Supported command is registration_numbers_for_cars_with_colour
	 *
	 * @param input
	 * @return true/false
	 */
	@Override
	public boolean support(String input) {
		return input != null && !StringOperations.isEmpty(input)
				&& input.trim().equalsIgnoreCase(ParkingConstant.REGISTRATION_NO_WITH_COLOR);
	}

	/**
	 * Provides all comma separated car's registration no
	 * which matches given color
	 *
	 * @param params
	 * @return
	 */
	@Override
	public String execute(String[] params) {
		validateParams(params, ParkingConstant.REGISTRATION_NO_WITH_COLOR, 1);
		List<Vehicle> vehicles = getStrategy().findAllVehicleWithColor(params[0]);
		return !vehicles.isEmpty() ? String.join(", ", vehicles.stream()
				.map(o -> o.getRegistrationNo()).collect(Collectors.toList())) : ParkingConstant.NOT_FOUND;
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
