package com.practice.parkinglot.parkinglot.parking;

import java.util.HashMap;
import java.util.Map;

import com.practice.parkinglot.consoleapp.command.ICommand;
import com.practice.parkinglot.parkinglot.applicationcommand.CreateParkingCommand;
import com.practice.parkinglot.parkinglot.applicationcommand.LeaveCommand;
import com.practice.parkinglot.parkinglot.applicationcommand.ParkCommand;
import com.practice.parkinglot.parkinglot.applicationcommand.RegistrationNoWithColorCommand;
import com.practice.parkinglot.parkinglot.applicationcommand.SlotNoForRegistrationNoCommand;
import com.practice.parkinglot.parkinglot.applicationcommand.SlotNoWithColorCommand;
import com.practice.parkinglot.parkinglot.applicationcommand.StatusCommand;
import com.practice.parkinglot.parkinglot.constant.ParkingConstant;


public class ParkingHelper {
	public static Map<String, ICommand<String>> getCommands() {
		IParkingStrategy strategy = new ParkingLotInMemoryStrategy();
		
		Map<String, ICommand<String>> map = new HashMap<>();
		map.put(ParkingConstant.CREATE_PARKING, new CreateParkingCommand(strategy));
		map.put(ParkingConstant.LEAVE, new LeaveCommand(strategy));
		map.put(ParkingConstant.PARK, new ParkCommand(strategy));
		map.put(ParkingConstant.REGISTRATION_NO_WITH_COLOR, new RegistrationNoWithColorCommand(strategy));
		map.put(ParkingConstant.SLOT_NO_FOR_REGISTRATION_NO, new SlotNoForRegistrationNoCommand(strategy));
		map.put(ParkingConstant.SLOT_NO_WITH_COLOR, new SlotNoWithColorCommand(strategy));
		map.put(ParkingConstant.STATUS, new StatusCommand(strategy));
		return map;
	}
}
