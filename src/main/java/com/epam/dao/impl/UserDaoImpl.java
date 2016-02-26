package com.epam.dao.impl;

import com.epam.dao.Dao;
import com.epam.dao.UserDao;
import com.epam.domain.Ticket;
import com.epam.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserDaoImpl implements UserDao, Dao<User> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);;
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM USER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM USER WHERE EMAIL = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
    }

    @Override
    public User getByName(String name) {
        String sql = "SELECT * FROM USER WHERE NAME = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new UserMapper());
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        int id = user.getId();
        String sql = "SELECT * FROM TICKET INNER JOIN USER_TICKET_MP WHERE USER_TICKET_MP.USER_ID = ?";
        return (List<Ticket>) jdbcTemplate.queryForObject(sql, new Object[]{id}, new TicketMapper());
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO USER (NAME, BIRTHDATE, USER_ROLE, EMAIL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getBirthDate(), user.getRole().toString(), user.getEmail());
    }

    @Override
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
            user.setBirthDate(rs.getDate("BIRTHDATE"));
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

