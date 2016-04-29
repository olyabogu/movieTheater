package com.epam.dao;

import com.epam.domain.Ticket;
import com.epam.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Repository("userDao")
@Transactional
public class UserDao {

    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String USER_ROLE = "USER_ROLE";
    private static final String BIRTH_DATE = "BIRTH_DATE";
    private static final String EMAIL = "EMAIL";
    private static final String IS_PURCHASED = "IS_PURCHASED";

    private static final String USER_BY_ID = "SELECT * FROM USER WHERE ID = ?";
    private static final String USER_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";
    private static final String USER_BY_NAME = "SELECT * FROM USER WHERE NAME = ?";
    private static final String TICKET_FOR_USER = "SELECT * FROM TICKET INNER JOIN USER_TICKET_MP WHERE USER_TICKET_MP.USER_ID = ?";
    private static final String CREATE_USER = "INSERT INTO USER (NAME, BIRTH_DATE, USER_ROLE, EMAIL,PASSWORD, USER_ACCOUNT_ID) VALUES (?, ?, ?, ?,?,?)";
    private static final String UPDATE_USER = "UPDATE USER SET NAME=?, BIRTH_DATE=?, USER_ROLE=?, EMAIL=? WHERE ID = ?";
    private static final String DELETE_USER = "DELETE FROM USER WHERE ID=?";
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

    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt(ID));
            user.setName(rs.getString(NAME));
            user.setPassword(rs.getString(PASSWORD));
            String userRoles = rs.getString(USER_ROLE);
            Set<String> roles = new HashSet<>(Arrays.asList((userRoles.split(" , "))));
            user.setRoles(roles);
            user.setBirthDate(rs.getDate(BIRTH_DATE));
            user.setEmail(rs.getString(EMAIL));
            return user;
        }
    }

    private class TicketMapper implements RowMapper<Ticket> {

        @Override
        public Ticket mapRow(ResultSet rs, int i) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt(ID));
            ticket.setPurchased(rs.getBoolean(IS_PURCHASED));
            return ticket;
        }
    }
}

