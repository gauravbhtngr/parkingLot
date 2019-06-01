package com.practice.parkinglot.parkinglot.parking;

import java.util.List;
import java.util.Map;

import com.practice.parkinglot.parkinglot.slot.Slot;
import com.practice.parkinglot.parkinglot.vehicle.Vehicle;


public interface IParkingStrategy {
	/**
	 * Create parking lot of given size
	 * @param size
	 */
	void create(Integer size);

	/**
	 * Park vehicle in parking lot
	 * @param vehicle
	 * @return Occupied slot
	 */
	Integer park(Vehicle vehicle);

	/**
	 * Leave parking from given slot
	 * @param slotId
	 * @return Freed slot
	 */
	Integer leave(Integer slotId);

	/**
	 * Get status of parking
	 * @return current parking status
	 */
	Map<Slot, Vehicle> status();

	/**
	 * Get all parked vehicle with a given color
	 * @param color
	 * @return List of parked vehicle with given color 
	 */
	List<Vehicle> findAllVehicleWithColor(String color);

	/**
	 * Get all occupied slot with given Vehicle color
	 * @param color
	 * @return list of occupied slots
	 */
	List<Slot> findAllSlotsForCarWithColor(String color);

	/**
	 * Get occupied slot with Vehicle registration no
	 * @param registrationNo
	 * @return Occupied slot with registration no
	 */
	Slot findSlotForRegistrationNo(String registrationNo);
	
}
