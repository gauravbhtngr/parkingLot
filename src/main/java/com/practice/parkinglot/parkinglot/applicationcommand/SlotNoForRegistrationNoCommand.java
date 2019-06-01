package com.practice.parkinglot.parkinglot.applicationcommand;

import com.practice.parkinglot.core.StringOperations;
import com.practice.parkinglot.parkinglot.constant.ParkingConstant;
import com.practice.parkinglot.parkinglot.parking.IParkingStrategy;
import com.practice.parkinglot.parkinglot.slot.Slot;

public class SlotNoForRegistrationNoCommand extends AbstractCommand {

	public SlotNoForRegistrationNoCommand(IParkingStrategy strategy) {
		super(strategy);
	}

	/**
	 * checks if command is supported
	 * Supported command is slot_number_for_registration_number
	 *
	 * @param input
	 * @return true/false
	 */
	@Override
	public boolean support(String input) {
		return input != null && !StringOperations.isEmpty(input)
				&& input.trim().equalsIgnoreCase(ParkingConstant.SLOT_NO_FOR_REGISTRATION_NO);
	}

	/**
	 * Provides slot no for a given car registration no
	 * which matches given color
	 *
	 * @param params
	 * @return
	 */
	@Override
	public String execute(String[] params) {
		validateParams(params, ParkingConstant.SLOT_NO_FOR_REGISTRATION_NO, 1);
		Slot slot = getStrategy().findSlotForRegistrationNo(params[0]);
		return slot != null ? String.valueOf(slot.getSlotNo()) : ParkingConstant.NOT_FOUND;
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
