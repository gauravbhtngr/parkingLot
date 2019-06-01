package com.practice.parkinglot.parkinglot.applicationcommand;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.parkinglot.core.StringOperations;
import com.practice.parkinglot.parkinglot.constant.ParkingConstant;
import com.practice.parkinglot.parkinglot.parking.IParkingStrategy;
import com.practice.parkinglot.parkinglot.slot.Slot;

public class SlotNoWithColorCommand extends AbstractCommand {


	public SlotNoWithColorCommand(IParkingStrategy strategy) {
		super(strategy);
	}

	/**
	 * checks if command is supported
	 * Supported command is slot_numbers_for_cars_with_colour
	 *
	 * @param input
	 * @return true/false
	 */
	@Override
	public boolean support(String input) {
		return input != null && !StringOperations.isEmpty(input)
				&& input.trim().equalsIgnoreCase(ParkingConstant.SLOT_NO_WITH_COLOR);
	}

	/**
	 * Provides all comma separated slot no for given car color
	 *
	 * @param params
	 * @return
	 */
	@Override
	public String execute(String[] params) {
		validateParams(params, ParkingConstant.SLOT_NO_WITH_COLOR, 1);
		List<Slot> slots = getStrategy().findAllSlotsForCarWithColor(params[0]);
		return !slots.isEmpty() ? String.join(", ", slots.stream()
				.map(o -> String.valueOf(o.getSlotNo())).collect(Collectors.toList()))
				: ParkingConstant.NOT_FOUND;
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
