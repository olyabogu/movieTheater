package com.epam.services.impl;

import java.util.List;

import com.epam.domain.Discount;
import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.services.DiscountDao;
import com.epam.services.DiscountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiscountServiceImpl implements DiscountService {
	@Autowired
	private UserServiceImpl userService;
    @Autowired
    private DiscountDao discountDao;

	@Override
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

    @Override
    public void updateDiscountStatistics(String discount) {
        discountDao.updateDiscountStatistics(discount);
    }

    @Override
    public void updateDiscountForUserStatistics(User user) {
        discountDao.updateDiscountForUserStatistics(user);
    }

    @Override
    public Integer getStatisticsForUser(User user) {
        return discountDao.getStatisticsForUser(user);
    }

    @Override
    public Integer getStatisticsForDiscount(String discount) {
        return discountDao.getStatisticsForDiscount(discount);
    }
}
