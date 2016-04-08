package com.epam.domain;

/**
 * Class to store the amount of prepaid money
 * that user has in the system.
 *
 * Created by Olga Bogutska.
 */
public class UserAccount {

	private int id;
	private Double amount;
	private String currency;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
