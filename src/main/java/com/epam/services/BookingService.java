package com.epam.services;

import com.epam.domain.Discount;
import com.epam.domain.Event;
import com.epam.domain.Ticket;
import com.epam.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {
	@Autowired
	private DiscountService discountService;

	/**
	 * @return Price for ticket for specified event
	 * on specific date and for specified seats.
	 */
	public String getTicketPrice(Event event, Date date, boolean isVipSeats, User user) {
		Double price = event.getBasePrice();
		Discount discount = calculateDiscount(date, user);
		if (isVipSeats) {
			price *= 2;
		}
		if (Event.Rating.HIGH.equals(event.getRating())) {
			price *= 1.2;
		}
		if (discount != null) {
			price *= discount.getDiscountStrategy().getPercent() / 100;
		}
		DecimalFormat format = new DecimalFormat("#0.00");
		return format.format(price);
	}

	public boolean bookTicket(User user, Ticket ticket) {
		user.getBookedTickets().add(ticket);
		return true;
	}

	public List<Ticket> getTicketsForEvent(Event event, Date date) {
		return event.getTickets();
	}

	private Discount calculateDiscount(Date date, User user) {

		Calendar calendarForEventDate = Calendar.getInstance();
		calendarForEventDate.setTime(date);

		Calendar calendarForUserBirthday = Calendar.getInstance();
		calendarForUserBirthday.setTime(user.getBirthDate());

		boolean sameMonth = calendarForEventDate.get(Calendar.MONTH) == calendarForUserBirthday.get(Calendar.MONTH);
		boolean sameDay = calendarForEventDate.get(Calendar.DAY_OF_MONTH) == calendarForUserBirthday.get(Calendar.DAY_OF_MONTH);
		boolean isTodayUserBirthday = sameDay && sameMonth;

		return discountService.getDiscount(user, isTodayUserBirthday);
	}
}
