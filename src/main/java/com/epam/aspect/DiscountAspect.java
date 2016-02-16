package com.epam.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

	private int counterForBirthdayDiscount;
	private int counterForTicketDiscount;
	private Map<Integer, Integer> counterForUserDiscount = new HashMap<>();

	@Pointcut("execution(* com.epam.services.DiscountService.getDiscount(..))")
	private void getDiscount() {
	}

	@AfterReturning(value = "execution(* getDiscount(..)) && args(user, isTodayUserBirthday)", returning = "discount")
	public void getDiscountForUser(User user, boolean isTodayUserBirthday, Discount discount) {
		if (discount != null) {
			int counter = 0;
			int id = user.getId();
			if (counterForUserDiscount.containsKey(id)) {
				counter = counterForUserDiscount.get(id);
				counterForUserDiscount.put(id, counter++);
			} else {
				counterForUserDiscount.put(id, counter++);
			}
			System.out.println("Discount for user " + user.getName() + " was given " + counter + " times");
		}
	}

	@AfterReturning(value = "getDiscount()", returning = "discount")
	public void getTotalDiscount(Discount discount) {

		if (discount != null) {
			if (Discount.DiscountStrategy.BIRTHDAY.equals(discount.getDiscountStrategy())) {
				counterForBirthdayDiscount++;
				System.out.println(Discount.DiscountStrategy.BIRTHDAY.getName() + "discount was given " + counterForBirthdayDiscount + " times");
			}
			if (Discount.DiscountStrategy.TICKET.equals(discount.getDiscountStrategy())) {
				counterForTicketDiscount++;
				System.out.println(Discount.DiscountStrategy.TICKET.getName() + " discount was given " + counterForTicketDiscount + " times");
			}
		}
	}
}
