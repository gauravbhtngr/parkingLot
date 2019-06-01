package com.practice.parkinglot.parkinglot.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import com.practice.parkinglot.consoleapp.exception.ApplicationException;
import com.practice.parkinglot.consoleapp.exception.CommandNotAllowedException;
import com.practice.parkinglot.core.Preconditions;
import com.practice.parkinglot.parkinglot.slot.FixedAreaSlot;
import com.practice.parkinglot.parkinglot.slot.Slot;
import com.practice.parkinglot.parkinglot.vehicle.Vehicle;


public class ParkingLotInMemoryStrategy implements IParkingStrategy {
	
	private final Map<Slot, Vehicle> carSlots = new HashMap<>();
	private final Map<String, List<Vehicle>> carColors = new HashMap<>();
	private final Map<String, Slot> registrationNumberSlots = new HashMap<>();
	private final PriorityQueue<Integer> availableSlots = new PriorityQueue<>();

	/**
	 * Create parking lot of given size
	 * @param size
	 * @throws ApplicationException
	 */
	@Override
	public void create(Integer size) {
		if (size == null || size == 0) {
			throw new ApplicationException("Defined slot no is not valid to create parking");
		}
		IntStream.range(1, size+1).forEach(this::initParking);
	}

	/**
	 * Park vehicle in parking lot.
	 * Check for provided vehicle is not null
	 * If Parking is not created throws ApplicationException
	 * If parking is full throws ApplicationException
	 * @param vehicle
	 * @return Occupied slot
	 * @throws CommandNotAllowedException
	 * @throws IllegalArgumentException
	 */
	@Override
	public Integer park(Vehicle vehicle) {
		Preconditions.precondition(vehicle != null, "Provided vehicle is not valid");
		checkParkingCreated();
		Integer slotId = Optional.of(availableSlots)
				.filter(s -> s.size() > 0).map(PriorityQueue::poll).orElse(null);
		if (slotId != null) {
			parkCar(slotId, vehicle);
		} else {
			throw new CommandNotAllowedException("Sorry, parking lot is full");
		}
		return slotId;
	}

	/**
	 * Leave parking from given slot
	 * if provided slot is null throw ApplicationException
	 * if provided slot is not occupied throw ApplicationException
	 * @param slotId
	 * @return Freed slot no
	 * @throws ApplicationException
	 */
	@Override
	public Integer leave(Integer slotId) {
		if (slotId == null) {
			throw new ApplicationException("Provided slot no is not valid");
		}
		Slot s = new FixedAreaSlot(slotId);
		if (carSlots.get(s) == null) {
			throw new ApplicationException("Not a valid slot no for leave command");
		}
		Vehicle c = carSlots.get(s);
		carSlots.put(s, null);
		removeFromColorCarListMap(c);
		removeFromRegistrationNumberSlotMap(c);
		availableSlots.add(slotId);
		return slotId;
	}

	/**
	 * Get status of parking
	 * @return current parking status
	 * @throws ApplicationException
	 */
	@Override
	public Map<Slot, Vehicle> status() {
		checkParkingCreated();
		return this.carSlots;
	}

	/**
	 * Get all parked vehicle with a given color
	 * @param color
	 * @return List of parked vehicle with given color 
	 * @throws ApplicationException
	 */
	@Override
	public List<Vehicle> findAllVehicleWithColor(String color) {
		checkParkingCreated();
		List<Vehicle> vehicles = carColors.get(color);
		return vehicles == null ? new ArrayList<>() : carColors.get(color);
	}

	/**
	 * Get all occupied slot with given Vehicle color
	 * @param color
	 * @return list of occupied slots
	 * @throws ApplicationException
	 */
	@Override
	public List<Slot> findAllSlotsForCarWithColor(String color) {
		checkParkingCreated();
		List<Vehicle> cars = carColors.get(color);
		List<Slot> slots = new ArrayList<Slot>();
		if (cars == null) {
			return slots;
		}
		for (Vehicle c : cars) {
			Slot slot = registrationNumberSlots.get(c.getRegistrationNo());
			slots.add(slot);
		}
		return slots;
	}

	/**
	 * Get occupied slot with Vehicle registration no
	 * @param registrationNo
	 * @return Occupied slot with registration no
	 * @throws ApplicationException
	 */
	@Override
	public Slot findSlotForRegistrationNo(String registrationNo) {
		checkParkingCreated();
		Slot slot = registrationNumberSlots.get(registrationNo);
		if (slot != null) {
			return registrationNumberSlots.get(registrationNo);
		}
		return null;
	}

	private void checkParkingCreated() {
		if (carSlots.isEmpty()) {
			throw new ApplicationException("Parking is not created yet");
		}
	}

	private void initParking(int i) {
		carSlots.put(new FixedAreaSlot(i), null);
		availableSlots.add(i);
	}

	private void parkCar(Integer slotId, Vehicle vehicle) {
		FixedAreaSlot s = new FixedAreaSlot(slotId);
		carSlots.put(s, vehicle);
		addToCarColorsMap(vehicle);
		addToRegistrationNumberSlotMap(vehicle, s);
	}

	PriorityQueue<Integer> availableSlots() {
		return this.availableSlots;
	}

	private void addToCarColorsMap(Vehicle car) {
		String color = car.getColor();
		List<Vehicle> cars = carColors.get(color);
		if (cars == null) {
			cars = new ArrayList<>();
			carColors.put(color, cars);
		}
		cars.add(car);
	}

	private void removeFromColorCarListMap(Vehicle car) {
		String color = car.getColor();
		List<Vehicle> cars = carColors.get(color);
		if (cars != null) {
			cars.remove(car);
		}
	}

	private void addToRegistrationNumberSlotMap(Vehicle car, Slot s) {
		registrationNumberSlots.put(car.getRegistrationNo(), s);
	}

	private void removeFromRegistrationNumberSlotMap(Vehicle car) {
		registrationNumberSlots.remove(car.getRegistrationNo());
	}
}
