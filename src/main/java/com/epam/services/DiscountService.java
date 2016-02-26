package com.epam.services;

import java.util.List;

import com.epam.dao.impl.DiscountDao;
import com.epam.domain.Discount;
import com.epam.domain.Ticket;
import com.epam.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiscountService {
	@Autowired
	private UserService userService;
    @Autowired
    private DiscountDao discountDao;

	public Discount getDiscount(User user, boolean isTodayUserBirthday) {

		if (isTodayUserBirthday) {
			return new Discount(Discount.DiscountStrategy.BIRTHDAY);
		}

		List<Ticket> tickets = userService.getBookedTickets(user);

		if (!tickets.isEmpty()) {
			int ticketsNumber = tickets.size();
			if (ticketsNumber % 10 == 0) {
				return new Discount(Discount.DiscountStrategy.TICKET);
			}
		}
		return null;
	}

    public void updateDiscountStatistics(String discount) {
        discountDao.updateDiscountStatistics(discount);
    }

    public void updateDiscountForUserStatistics(User user) {
        discountDao.updateDiscountForUserStatistics(user);
    }

    public Integer getStatisticsForUser(User user) {
        return discountDao.getStatisticsForUser(user);
    }

    public Integer getStatisticsForDiscount(String discount) {
        return discountDao.getStatisticsForDiscount(discount);
    }
}
