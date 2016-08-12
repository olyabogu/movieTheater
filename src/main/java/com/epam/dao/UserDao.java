package com.epam.dao;

import com.epam.dao.mapper.TicketMapper;
import com.epam.dao.mapper.UserMapper;
import com.epam.domain.Ticket;
import com.epam.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Repository("userDao")
@Transactional
public class UserDao {

    private static final String GET_USER = "SELECT * FROM USER INNER JOIN USER_ACCOUNT ON USER.USER_ACCOUNT_ID = USER_ACCOUNT.ACCOUNT_ID";
    private static final String USER_BY_ID = GET_USER + " WHERE USER.USER_ID= ?";
    private static final String USER_BY_EMAIL = GET_USER + " WHERE EMAIL = ?";
    private static final String USER_BY_NAME = GET_USER + " WHERE NAME = ?";
    private static final String TICKET_FOR_USER = "SELECT * FROM TICKET INNER JOIN USER_TICKET_MP ON TICKET.TICKET_ID=USER_TICKET_MP.TICKET_ID WHERE USER_TICKET_MP.USER_ID = ?";
    private static final String CREATE_USER = "INSERT INTO USER (NAME, BIRTH_DATE, USER_ROLE, EMAIL,PASSWORD, USER_ACCOUNT_ID) VALUES (?, ?, ?, ?,?,?)";
    private static final String UPDATE_USER = "UPDATE USER SET NAME=?, BIRTH_DATE=?, USER_ROLE=?, EMAIL=? WHERE USER_ID = ?";
    private static final String DELETE_USER = "DELETE FROM USER WHERE USER_ID=?";
    private static final String CHECK_IF_USER_EXIST = "SELECT COUNT(*) FROM USER WHERE NAME = ? OR EMAIL =?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User getById(int id) {
        try {
            return jdbcTemplate.queryForObject(USER_BY_ID, new UserMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User getUserByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(USER_BY_EMAIL, new UserMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User getByName(String name) {
        try {
            return jdbcTemplate.queryForObject(USER_BY_NAME, new UserMapper(), name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> getBookedTickets(User user) {
        int id = user.getId();
	    try {
		    return (List<Ticket>) jdbcTemplate.queryForObject(TICKET_FOR_USER, new TicketMapper(), id);
	    } catch (EmptyResultDataAccessException e) {
		    return Collections.<Ticket>emptyList();
	    }
    }

    public void create(User user) {
        jdbcTemplate.update(CREATE_USER, user.getUsername(), user.getBirthDate(), Arrays.toString(user.getRoles().toArray()), user.getEmail(), user.getPassword(), user.getAccount().getId());
    }

    public void update(User user) {
        jdbcTemplate.update(UPDATE_USER, user.getUsername(), user.getBirthDate(), user.getRoles().toString(), user.getEmail(), user.getId());
    }

    public void remove(int id) {
        jdbcTemplate.update(DELETE_USER, id);
    }

    public boolean isUserExists(String name, String email) {
        try {
            return jdbcTemplate.queryForObject(CHECK_IF_USER_EXIST, new Object[] { name, email }, Integer.class) > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}

