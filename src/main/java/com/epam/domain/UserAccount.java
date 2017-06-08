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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserAccount)) return false;

		UserAccount that = (UserAccount) o;

		if (id != that.id) return false;
		if (!amount.equals(that.amount)) return false;
		return currency.equals(that.currency);
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + amount.hashCode();
		result = 31 * result + currency.hashCode();
		return result;
	}
}
