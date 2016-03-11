package com.epam.services;

import com.epam.domain.Discount;
import com.epam.domain.User;

/**
 * @author Olga_Bogutska.
 */
public interface DiscountService {
	Discount getDiscount(User user, boolean isTodayUserBirthday);

	void updateDiscountStatistics(String discount);

	void updateDiscountForUserStatistics(User user);

	Integer getStatisticsForUser(User user);

	Integer getStatisticsForDiscount(String discount);
}
