package com.epam.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
public class Event {

	private String name;
	private Double ticketPrice;
	private List<Date> dates;
	private List<Ticket> tickets;
	private Rating rating;

	public enum Rating {
		HIGH, MID, LOW
	}

	public Event(List<Date> dates, Double ticketPrice, String name, Rating rating) {
		this.dates = dates;
		this.ticketPrice = ticketPrice;
		this.name = name;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Event{" +
				"name='" + name + '\'' +
				", ticketPrice=" + ticketPrice +
				", dates=" + dates +
				'}';
	}
}
