package com.epam.services;

import java.util.List;

import com.epam.domain.Discount;
import com.epam.domain.Ticket;
import com.epam.domain.User;

import org.springframework.stereotype.Service;


@Service
public class DiscountService {

	public Discount getDiscount(User user, boolean isTodayUserBirthday) {

		if (isTodayUserBirthday) {
			return new Discount(Discount.DiscountStrategy.BIRTHDAY);
		}

		List<Ticket> tickets = user.getBookedTickets();

		if (!tickets.isEmpty()) {
			int ticketsNumber = tickets.size();
			if (ticketsNumber % 10 == 0) {
				return new Discount(Discount.DiscountStrategy.TICKET);
			}
		}
		return null;
	}
}
