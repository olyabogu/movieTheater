package com.epam.dao;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Repository
public class UserDao {
    private static Map<Integer, User> users;

    @PostConstruct
    public void init(){
        users = new HashMap<Integer, User>();
        users.put(1, new User("Olga", User.UserRole.ADMIN, "olga_bogu@mail.com"));
        users.put(2, new User("John", User.UserRole.CLIENT, "john_smith@mail.com"));
    }

    public User getById(int id) {
        return users.get(id);
    }

    public User getUserByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User getUsersByName(String name) {
        for (User user : users.values()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public List<Ticket> getBookedTickets(User user) {
        return user.getBookedTickets();
    }

    public void register(User user) {
        users.put(user.getId(), user);
    }

    public void remove(User user) {
        if (users.containsValue(user)) {
            users.remove(user);
        } else {
            System.out.println("user with email " + user.toString() + " not registered");
        }
    }
}


