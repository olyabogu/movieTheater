package com.epam.services;

import com.epam.domain.User;

/**
 * @author Olga_Bogutska.
 */
public interface DiscountDao {

	 void updateDiscountForUserStatistics(User user);

	 void updateDiscountStatistics(String discount);

	 Integer getStatisticsForDiscount(String discount);

	 Integer getStatisticsForUser(User user);
}
