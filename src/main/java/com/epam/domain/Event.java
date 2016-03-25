package com.epam.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
public class Event {

	private int id;
	private String name;
	private Double basePrice;
	private List<Date> dates;
	private List<Ticket> tickets;
	private Rating rating;

	public Event() {
		this.dates = new LinkedList<>();
		this.tickets = new LinkedList<>();
	}

	public Event(List<Date> dates, Double basePrice, String name, Rating rating) {
		this.dates = dates;
		this.basePrice = basePrice;
		this.name = name;
		this.rating = rating;
		this.tickets = new LinkedList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
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
				", basePrice=" + basePrice +
				", dates=" + dates +
				'}';
	}
}
