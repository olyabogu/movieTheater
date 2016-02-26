package com.epam.services;

import com.epam.dao.impl.UserDaoImpl;
import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.exception.MovieException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDaoImpl userDao;

    public User getById(Integer id) {
        return userDao.getById(id);
    }

    public User getUserByEmail(String email) throws MovieException {
        if(StringUtils.isEmpty(email)) {
            throw new MovieException("User email didn't defined!");
        }
        return userDao.getUserByEmail(email);
    }

    public User getUserByName(String name) throws MovieException {
        if(StringUtils.isEmpty(name)) {
            throw new MovieException("User email didn't defined!");
        }
       return userDao.getByName(name);
    }

    public List<Ticket> getBookedTickets(User user) {
        return userDao.getBookedTickets(user);
    }

    public void register(User user) throws MovieException {
        if(user == null) {
            throw new MovieException("User didn't defined");
        }
        userDao.create(user);
    }

    public void remove(User user) throws MovieException {
        if(user == null) {
            throw new MovieException("User didn't defined");
        }
       userDao.remove(user);
    }
}
