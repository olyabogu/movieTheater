package com.epam.services;

import com.epam.domain.Ticket;
import com.epam.domain.User;

import java.util.List;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
public interface UserService {

	void register(User user);

	void update(User user) ;

	void remove(int id) ;

	User getUserByName(String name) ;

    User getById(Integer id);

    User getUserByEmail(String email) ;

    List<Ticket> getBookedTickets(User user);

	boolean isUserExists(String username, String email);
}
