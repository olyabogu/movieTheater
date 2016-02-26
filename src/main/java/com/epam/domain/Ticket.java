package com.epam.domain;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
public class Ticket {
	private int id;
    private boolean isPurchased;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPurchased() {
		return isPurchased;
	}

	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

}
