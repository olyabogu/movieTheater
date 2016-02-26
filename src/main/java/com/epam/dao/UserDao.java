package com.epam.dao;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.exception.MovieException;

import java.util.List;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
public interface UserDao {
    User getById(int id);

    User getUserByEmail(String email) throws MovieException;

    List<Ticket> getBookedTickets(User user);
}
