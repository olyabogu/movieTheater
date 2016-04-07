package com.epam.domain;

/**
 * @author Olga_Bogutska.
 */
public enum Currency {
	UAH("UAH"),
	USD("USD"),
	EUR("EUR");

	public String getDescription() {
		return description;
	}

	private String description;

	private Currency(String description) {
		this.description = description;
	}
}
