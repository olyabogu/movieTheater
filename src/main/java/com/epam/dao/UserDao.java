package com.epam.dao;

import com.epam.domain.Ticket;
import com.epam.domain.User;

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
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User getById(int id) {
	    try {
		    String sql = "SELECT * FROM USER WHERE ID = ?";
		    return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
	    } catch (EmptyResultDataAccessException e) {
		    return null;
	    }
    }

    public User getUserByEmail(String email) {
	    try {
		    String sql = "SELECT * FROM USER WHERE EMAIL = ?";
		    return jdbcTemplate.queryForObject(sql, new UserMapper(), email);
	    } catch (EmptyResultDataAccessException e) {
		    return null;
	    }
    }

    public User getByName(String name) {
	    try {
		    String sql = "SELECT * FROM USER WHERE NAME = ?";
		    return jdbcTemplate.queryForObject(sql, new UserMapper(), name);
	    } catch (EmptyResultDataAccessException e) {
		    return null;
	    }
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> getBookedTickets(User user) {
        int id = user.getId();
        String sql = "SELECT * FROM TICKET INNER JOIN USER_TICKET_MP WHERE USER_TICKET_MP.USER_ID = ?";
        return (List<Ticket>) jdbcTemplate.queryForObject(sql, new TicketMapper(), id);
    }

    public void create(User user) {
        String sql = "INSERT INTO USER (NAME, BIRTH_DATE, USER_ROLE, EMAIL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getBirthDate(), user.getRole().toString(), user.getEmail());
    }

	public void update(User user) {
		String sql = "UPDATE USER SET NAME=?, BIRTH_DATE=?, USER_ROLE=?, EMAIL=? WHERE ID = ?";
		jdbcTemplate.update(sql, user.getName(), user.getBirthDate(), user.getRole().toString(), user.getEmail(), user.getId());
	}

    public void remove(User user) {
        int id = user.getId();
        String sql  = "DELETE FROM USER WHERE ID=?";
        jdbcTemplate.update(sql, id);
    }

    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("ID"));
            user.setName(rs.getString("NAME"));
            user.setRole(User.UserRole.valueOf(rs.getString("USER_ROLE")));
            user.setBirthDate(rs.getDate("BIRTH_DATE"));
            user.setEmail(rs.getString("EMAIL"));
            return user;
        }
    }

    private class TicketMapper implements RowMapper<Ticket> {

        @Override
        public Ticket mapRow(ResultSet rs, int i) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("ID"));
            ticket.setPurchased(rs.getBoolean("IS_PURCHASED"));
            return ticket;
        }
    }
}

