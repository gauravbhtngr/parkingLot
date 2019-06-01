package com.practice.parkinglot.parkinglot.parking;


import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.practice.parkinglot.consoleapp.exception.ApplicationException;
import com.practice.parkinglot.consoleapp.exception.CommandNotAllowedException;
import com.practice.parkinglot.parkinglot.slot.FixedAreaSlot;
import com.practice.parkinglot.parkinglot.slot.Slot;
import com.practice.parkinglot.parkinglot.vehicle.Car;
import com.practice.parkinglot.parkinglot.vehicle.Vehicle;

public class ParkingLotInMemoryStrategyTest {
	ParkingLotInMemoryStrategy strategy;

	@BeforeMethod
	public void init() {
		strategy = new ParkingLotInMemoryStrategy();
	}

	@Test(expectedExceptions = ApplicationException.class)
	public void test_create_withNullSlot() {
		strategy.create(null);
	}

	@Test(expectedExceptions = ApplicationException.class)
	public void test_create_withZeroSlot() {
		strategy.create(0);
	}

	@Test
	public void test_create_withValidSlot() {
		strategy.create(6);
		Assert.assertTrue(strategy.status().size() == 6);
		Assert.assertTrue(strategy.availableSlots().size() == 6);
	}

	@Test
	public void test_park_withLeaveAndPark() {
		strategy.create(3);
		Assert.assertTrue(strategy.park(createCar("KA-01-HH-1234", "white")) == 1);
		Assert.assertTrue(strategy.park(createCar("KA-01-HH-9999", "white")) == 2);
		Assert.assertTrue(strategy.park(createCar("KA-01-BB-0001", "black")) == 3);
		strategy.leave(1);
		strategy.leave(2);
		Assert.assertTrue(strategy.park(createCar("KA-01-HH-9999", "white")) == 1);
		Assert.assertTrue(strategy.park(createCar("KA-01-HH-1234", "white")) == 2);
	}

	@Test(expectedExceptions = ApplicationException.class)
	public void test_leave_withNullSlotId() {
		strategy.leave(null);
	}

	@Test(expectedExceptions = ApplicationException.class)
	public void test_leave_withInvalidSlotId() {
		strategy.create(1);
		strategy.leave(1);
	}

	@Test
	public void test_leave_withValidSlotId() {
		strategy.create(2);
		strategy.park(createCar("KA-01-HH-9999", "white"));
		strategy.park(createCar("KA-01-HH-1234", "white"));
		strategy.leave(1);
		Assert.assertTrue(strategy.status().get(new FixedAreaSlot(1)) == null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test_park_withNoVehicle() {
		strategy.park(null);
	}

	@Test(expectedExceptions = ApplicationException.class)
	public void test_park_withoutParkingCreated() {
		strategy.park(createCar("KA-01-HH-1234", "white"));
	}

	@Test(description = "Test car parked successfully, also check that nearest parking slot is assigned to car")
	public void test_park_withParkingCreated() {
		strategy.create(2);
		Assert.assertTrue(strategy.park(createCar("KA-01-HH-1234", "white")) == 1);
	}

	@Test(expectedExceptions = CommandNotAllowedException.class)
	public void test_park_withParkingFull() {
		strategy.create(1);
		strategy.park(createCar("KA-01-HH-1234", "white"));
		strategy.park(createCar("KA-01-HH-9999", "white"));
	}

	@Test(expectedExceptions = ApplicationException.class)
	public void test_status_withNoParkingCreated() {
		strategy.status();
	}

	@Test
	public void test_status_withParkingCreated() {
		strategy.create(1);
		strategy.park(createCar("KA-01-HH-9999", "white"));
		Map<Slot, Vehicle> status =  strategy.status();
		Assert.assertFalse(status.isEmpty());
		Assert.assertTrue(status.get(new FixedAreaSlot(1)).getColor().equals("white"));
		Assert.assertTrue(status.get(new FixedAreaSlot(1)).getRegistrationNo().equals("KA-01-HH-9999"));
	}

	@Test(expectedExceptions = ApplicationException.class)
	public void test_findAllVehicleWithColor_withNoParkingCreated() {
		strategy.findAllVehicleWithColor("white");
	}

	@Test
	public void test_findAllVehicleWithColor_withNoParkedCar() {
		strategy.create(1);
		Assert.assertTrue(strategy.findAllVehicleWithColor("white").isEmpty());
	}

	@Test
	public void test_findAllVehicleWithColor_withNoCarOfProvidedColor() {
		strategy.create(1);
		strategy.park(createCar("KA-01-HH-9999", "white"));
		Assert.assertTrue(strategy.findAllVehicleWithColor("black").isEmpty());
	}

	@Test
	public void test_findAllVehicleWithColor_withValidColor() {
		strategy.create(1);
		strategy.park(createCar("KA-01-HH-9999", "white"));
		List<Vehicle> vehicleList = strategy.findAllVehicleWithColor("white");
		Assert.assertFalse(vehicleList.isEmpty());
		Assert.assertTrue(vehicleList.size() == 1);
		Assert.assertTrue(vehicleList.get(0).getColor().equals("white"));
		Assert.assertTrue(vehicleList.get(0).getRegistrationNo().equals("KA-01-HH-9999"));
	}

	@Test(expectedExceptions = ApplicationException.class)
	public void test_findAllSlotsForCarWithColor_withNoParkingCreated() {
		strategy.findAllSlotsForCarWithColor("white");
	}

	@Test
	public void test_findAllSlotsForCarWithColor_withNoCarParkedWithProvidedColor() {
		strategy.create(1);
		strategy.park(createCar("KA-01-HH-9999", "black"));
		Assert.assertTrue(strategy.findAllSlotsForCarWithColor("white").isEmpty());
	}

	@Test
	public void test_findAllSlotsForCarWithColor_withValidProvidedColor() {
		strategy.create(1);
		strategy.park(createCar("KA-01-HH-9999", "black"));
		List<Slot> slots = strategy.findAllSlotsForCarWithColor("black");
		Assert.assertFalse(slots.isEmpty());
		Assert.assertTrue(slots.get(0).getSlotNo() == 1);
	}

	@Test(expectedExceptions = ApplicationException.class)
	public void test_findSlotForRegistrationNo_withNoParkingCreated() {
		strategy.findSlotForRegistrationNo("KA-01-HH-9999");
	}

	@Test
	public void test_findSlotForRegistrationNo_withNoCarParkedWithGivenRegistrationNo() {
		strategy.create(1);
		strategy.park(createCar("KA-01-HH-9999", "black"));
		Assert.assertTrue(strategy.findSlotForRegistrationNo("KA-01-HH-9998") == null);
	}

	@Test
	public void test_findSlotForRegistrationNo_withValidRegistrationNo() {
		strategy.create(1);
		strategy.park(createCar("KA-01-HH-9999", "black"));
		Slot slot = strategy.findSlotForRegistrationNo("KA-01-HH-9999");
		Assert.assertTrue(slot.getSlotNo() == 1);
	}
	
	private Vehicle createCar(String registrationNo, String color) {
		Car car = new Car();
		car.setColor(color);
		car.setRegistrationNo(registrationNo);
		return car;
	}
}
