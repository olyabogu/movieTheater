package com.epam.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Olga_Bogutska.
 */
@Aspect
@Component
public class CounterAspect {

	private int eventAccessedByName;
	private int counterForTicketPrice;

	@Pointcut("execution(* com.epam.services.EventService.getEventByName(..))")
	private void eventAccessedByName() {}

	@Before("eventAccessedByName()")
	public void eventAccessedByName(JoinPoint joinPoint) {
		eventAccessedByName++;
		System.out.println("Event was accessed by name " + eventAccessedByName + " times");
	}

	@Pointcut("execution(* com.epam.services.BookingService.getTicketPrice(..))")
	private void getTicketPrice() {
	}

	@Before("getTicketPrice()")
	public void getTicketPrice(JoinPoint joinPoint) {
		counterForTicketPrice++;
		System.out.println("Price for ticket for specified event on specific date and for specified seats was queried " + counterForTicketPrice + " times");
	}
}
