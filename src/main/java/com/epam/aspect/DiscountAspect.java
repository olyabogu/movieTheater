package com.epam.aspect;

import com.epam.services.DiscountService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.domain.Discount;
import com.epam.domain.User;

/**
 * Count how many times each discount was given total and for specific user
 *
 * @author Olga_Bogutska.
 */
@Aspect
@Component
public class DiscountAspect {
	@Autowired
	private DiscountService discountService;

	@Pointcut("execution(* com.epam.services.DiscountService.getDiscount(..))")
	private void getDiscount() {
	}

	@AfterReturning(value = "execution(* getDiscount(..)) && args(user, isTodayUserBirthday)", returning = "discount")
	public void getDiscountForUser(User user, boolean isTodayUserBirthday, Discount discount) {
		if (discount != null) {
			discountService.updateDiscountForUserStatistics(user);
			int counter = discountService.getStatisticsForUser(user);
			System.out.println("Discount for user " + user.getUsername() + " was given " + counter + " times");
		}
	}

	@AfterReturning(value = "getDiscount()", returning = "discount")
	public void getTotalDiscount(Discount discount) {
		if (discount != null) {
			String discountStrategy = discount.getDiscountStrategy().toString();
			discountService.updateDiscountStatistics(discountStrategy);
			int counter = discountService.getStatisticsForDiscount(discountStrategy);
			System.out.println(discountStrategy + "discount was given " + counter + " times");
		}
	}
}
