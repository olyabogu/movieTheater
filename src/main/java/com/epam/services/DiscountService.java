package com.epam.services;

import com.epam.domain.Discount;
import com.epam.domain.User;

import org.springframework.stereotype.Service;


@Service
public class DiscountService {

	public Discount getDiscount(User user, boolean isTodayUserBirthday) {

		int ticketsNumber = user.getBookedTickets().size();
		if (ticketsNumber % 10 == 0) {
			return new Discount(Discount.DiscountStrategy.TICKET);
		}

		if (isTodayUserBirthday) {
			return new Discount(Discount.DiscountStrategy.BIRTHDAY);
		}

		return null;
	}
}
