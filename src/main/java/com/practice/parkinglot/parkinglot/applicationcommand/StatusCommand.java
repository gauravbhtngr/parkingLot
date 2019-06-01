package com.practice.parkinglot.parkinglot.applicationcommand;

import java.util.Map;

import com.practice.parkinglot.core.StringOperations;
import com.practice.parkinglot.parkinglot.constant.ParkingConstant;
import com.practice.parkinglot.parkinglot.parking.IParkingStrategy;
import com.practice.parkinglot.parkinglot.slot.Slot;
import com.practice.parkinglot.parkinglot.vehicle.Vehicle;

public class StatusCommand extends AbstractCommand {

	public StatusCommand(IParkingStrategy strategy) {
		super(strategy);
	}

	/**
	 * checks if command is supported
	 * Supported command is status
	 *
	 * @param input
	 * @return true/false
	 */
	@Override
	public boolean support(String input) {
		return input != null && !StringOperations.isEmpty(input)
				&& input.trim().equalsIgnoreCase(ParkingConstant.STATUS);
	}

	/**
	 * Provides current status of parking lot
	 *
	 * @param params
	 * @return
	 */
	@Override
	public String execute(String[] params) {
		Map<Slot, Vehicle> map = getStrategy().status();
		if (map.isEmpty()) {
			return ParkingConstant.NOT_FOUND;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(ParkingConstant.STATUS_HEADER);
		builder.append("\n");
		map.entrySet().forEach(entry -> {
			if (entry.getValue() != null) {
				builder.append(entry.getKey().getSlotNo()
						+ "\t" + entry.getValue().getRegistrationNo()
						+ "\t" + entry.getValue().getColor());
				builder.append("\n");
			}
		});
		return builder.toString();
	}

	/**
	 * Pre process before execution of command
	 */
	@Override
	public void preProcess() {

	}

	/**
	 * Pre process before execution of command
	 */
	@Override
	public void postProcess() {

	}
}
