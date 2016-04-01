package com.epam.domain;

/**
 * @author Olga_Bogutska.
 */

public enum UserRole {
	REGISTERED_USER("Registered"),
	BOOKING_MANAGER("Booking manager");

	public String getDescription() {
		return description;
	}

	private String description;

	private UserRole(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
}

