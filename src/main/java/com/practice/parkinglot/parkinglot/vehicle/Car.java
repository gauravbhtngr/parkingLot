package com.practice.parkinglot.parkinglot.vehicle;


public class Car implements Vehicle {
	
	private String registrationNo;
	private String color;

	@Override
	public String getRegistrationNo() {
		return this.registrationNo;
	}

	@Override
	public String getColor() {
		return this.color;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
