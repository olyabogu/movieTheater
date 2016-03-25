package com.epam.dao;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.domain.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Repository("userDao")
public class UserDao {

	private static final String ID = "ID";
	private static final String NAME = "NAME";
	private static final String USER_ROLE = "USER_ROLE";
	private static final String BIRTH_DATE = "BIRTH_DATE";
	private static final String EMAIL = "EMAIL";
	private static final String IS_PURCHASED = "IS_PURCHASED";

	private static final String USER_BY_ID = "SELECT * FROM USER WHERE ID = ?";
	private static final String USER_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";
	private static final String USER_BY_NAME = "SELECT * FROM USER WHERE NAME = ?";
	private static final String TICKET_FOR_USER = "SELECT * FROM TICKET INNER JOIN USER_TICKET_MP WHERE USER_TICKET_MP.USER_ID = ?";
	private static final String CREATE_USER = "INSERT INTO USER (NAME, BIRTH_DATE, USER_ROLE, EMAIL) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_USER = "UPDATE USER SET NAME=?, BIRTH_DATE=?, USER_ROLE=?, EMAIL=? WHERE ID = ?";
	private static final String DELETE_USER = "DELETE FROM USER WHERE ID=?";

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
        return (List<Ticket>) jdbcTemplate.queryForObject(TICKET_FOR_USER, new TicketMapper(), id);
    }

    public void create(User user) {
        jdbcTemplate.update(CREATE_USER, user.getName(), user.getBirthDate(), user.getRole().toString(), user.getEmail());
    }

	public void update(User user) {
		jdbcTemplate.update(UPDATE_USER, user.getName(), user.getBirthDate(), user.getRole().toString(), user.getEmail(), user.getId());
	}

    public void remove(User user) {
        int id = user.getId();
        jdbcTemplate.update(DELETE_USER, id);
    }

    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt(ID));
            user.setName(rs.getString(NAME));
            user.setRole(UserRole.valueOf(rs.getString(USER_ROLE)));
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

