package com.epam.services;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.dao.UserDao;
import com.epam.exception.MovieException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getById(Integer id){
        return userDao.getById(id);
    }

    public User getUserByEmail(String email) throws MovieException {
        return userDao.getUserByEmail(email);
    }

    public User getUserByName(String name) throws MovieException {
       return userDao.getUsersByName(name);
    }

    public List<Ticket> getBookedTickets(User user) {
        return userDao.getBookedTickets(user);
    }

    public void register(User user) {
        userDao.register(user);
    }

    public void remove(User user) throws MovieException {
       userDao.remove(user);
    }
}
