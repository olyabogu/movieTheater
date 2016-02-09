package com.epam.services;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserService {
    @Inject
    private UserDao userDao;

    public User getById(Integer id){
        return userDao.getById(id);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public User getUserByName(String name) {
       return userDao.getUsersByName(name);
    }

    public List<Ticket> getBookedTickets(User user) {
        return userDao.getBookedTickets(user);
    }

    public void register(User user) {
        userDao.register(user);
    }

    public void remove(User user) {
       userDao.remove(user);
    }
}
