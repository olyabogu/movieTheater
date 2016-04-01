package com.epam.services;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.exception.MovieException;

import java.util.List;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
public interface UserService {

	void register(User user) throws MovieException;

	void update(User user) throws MovieException;

	void remove(User user) throws MovieException;

	User getUserByName(String name) throws MovieException;

    User getById(Integer id);

    User getUserByEmail(String email) throws MovieException;

    List<Ticket> getBookedTickets(User user);

	boolean isUserExists(String username, String email);
}
