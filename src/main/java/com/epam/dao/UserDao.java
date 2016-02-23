package com.epam.dao;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.exception.MovieException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Repository
public class UserDao {
    private static Map<Integer, User> users;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getById(int id) {
        String SQL = "SELECT * FROM User WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new UserMapper());
    }

    public User getUserByEmail(String email) throws MovieException {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        throw new MovieException("User with email " + email + " not exist");
    }

    public User getUsersByName(String name) throws MovieException {
        for (User user : users.values()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new MovieException("User with name " + name + " not exist");
    }

    public List<Ticket> getBookedTickets(User user) {
        return user.getBookedTickets();
    }

    public void register(User user) {
        String SQL = "INSERT INTO USER (NAME, BIRTHDATE, USER_ROLE, EMAIL) VALUES (?, ?, ?)";
        jdbcTemplate.update(SQL, user.getName(), user.getBirthDate(), user.getRole().toString(), user.getEmail());
    }

    public void remove(User user) throws MovieException {
        int id = user.getId();
        if (users.containsKey(id)) {
            users.remove(id);
        } else {
            throw new MovieException(user.toString() + " not exist");
        }
    }

    private class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setRole(User.UserRole.valueOf(rs.getString("user_role")));
//            user.setBirthDate(new SimpleDateFormat("dd-MM-yy").parse((rs.getString("birthdate"))));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }
}

