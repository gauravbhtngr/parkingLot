package com.practice.parkinglot.parkinglot.constant;


public interface ParkingConstant {
	String CREATE_PARKING = "create_parking_lot";
	String PARK = "park";
	String LEAVE = "leave";
	String STATUS = "status";
	String REGISTRATION_NO_WITH_COLOR = "registration_numbers_for_cars_with_colour";
	String SLOT_NO_WITH_COLOR = "slot_numbers_for_cars_with_colour";
	String SLOT_NO_FOR_REGISTRATION_NO = "slot_number_for_registration_number";

	String CREATE_MESSAGE = "Created a parking lot with %s slots";
	String PARK_MESSAGE = "Allocated slot number: %s";
	String LEAVE_MESSAGE = "Slot number %s is free";
	String NOT_FOUND = "Not found";
	String STATUS_HEADER = "Slot No.\tRegistration No\tColour";
}
