package com.epam.domain;

/**
 * @author Olga_Bogutska.
 */
public enum Rating {
	HIGH("High"),
	MIDDLE("Middle"),
	LOW("Low");

	public String getDescription() {
		return description;
	}

	private String description;

	private Rating(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
}
