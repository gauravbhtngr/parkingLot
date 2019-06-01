package com.practice.parkinglot.core;


public class StringOperations {
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
}
