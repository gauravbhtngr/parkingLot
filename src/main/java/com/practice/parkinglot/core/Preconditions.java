package com.practice.parkinglot.core;


public class Preconditions {
	
	public static void precondition(boolean condition, String message) {
		if (!condition) {
			throw new IllegalArgumentException(message);
		}
	}
}
