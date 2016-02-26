package com.epam.aspect;

import com.epam.domain.Event;
import com.epam.services.CounterService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Olga_Bogutska.
 */
@Aspect
@Component
public class CounterAspect {

	@Autowired
	private CounterService counterService;

	@Pointcut("execution(* com.epam.services.EventService.getEventByName(..))")
	private void eventAccessedByName() {}

	@AfterReturning(value = "eventAccessedByName()", returning = "event")
	public void getTotalDiscount(Event event) {
		counterService.updateCountForEventByName(event);
		System.out.println("Event was accessed by name " + counterService.getCounterByName(event) + " times");
	}

	@Pointcut("execution(* com.epam.services.BookingService.getTicketPrice(..))")
	private void getTicketPrice() {
	}

	@Before("getTicketPrice() ")
	public void getTicketPrice(JoinPoint joinPoint) {
		Event event = (Event) joinPoint.getArgs()[0];
		counterService.updateCountForEventByPrice(event);
		System.out.println("Price for ticket for specified event on specific date and for specified seats " +
				"was queried " + counterService.getCounterByPrice(event) + "  times");
	}
}
