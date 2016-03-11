package com.epam.services;

import java.util.List;

import com.epam.domain.Auditorium;

/**
 * @author Olga_Bogutska.
 */
public interface AuditoriumService {
	List<Auditorium> getAuditoriums();

	int getSeatsNumber();

	int getVipSeats();
}
