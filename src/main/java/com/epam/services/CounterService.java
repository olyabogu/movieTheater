package com.epam.services;

import com.epam.domain.Event;

/**
 * @author Olga_Bogutska.
 */
public interface CounterService {

	void updateCountForEventByName(Event event);

	void updateCountForEventByPrice(Event event) ;

	int getCounterByName(Event event);

	int getCounterByPrice(Event event);
}
