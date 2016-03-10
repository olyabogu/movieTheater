package com.epam.dao.impl;

import com.epam.dao.Dao;
import com.epam.dao.UserDao;
import com.epam.domain.Ticket;
import com.epam.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao, Dao<User> {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM USER WHERE ID = :id";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
	    return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new UserMapper());
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM USER WHERE EMAIL = :email";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("email", email);
	    return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new UserMapper());
    }

    @Override
    public User getByName(String name) {
        String sql = "SELECT * FROM USER WHERE NAME =  :name";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("name", name);
	    return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new UserMapper());
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        int id = user.getId();
        String sql = "SELECT * FROM TICKET INNER JOIN USER_TICKET_MP WHERE USER_TICKET_MP.USER_ID = :id";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return (List<Ticket>) namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TicketMapper());
    }

    @Override
    public void create(User user) {
        String SQL = "INSERT INTO USER (NAME, BIRTHDATE, USER_ROLE, EMAIL) VALUES (:name, :birthdate, :userrole, :email)";
	    Map namedParameters = new HashMap();

	    namedParameters.put("name", user.getName());
	    namedParameters.put("birthdate", user.getBirthDate());
	    namedParameters.put("userrole", user.getRole().toString());
	    namedParameters.put("email", user.getEmail());
	    namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public void remove(User user) {
        int id = user.getId();
        String sql  = "DELETE FROM USER WHERE ID = :id";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
	    namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    private static class UserMapper implements RowMapper<User> {
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

    private static class TicketMapper implements RowMapper<Ticket> {

        @Override
        public Ticket mapRow(ResultSet rs, int i) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("ID"));
            ticket.setPurchased(rs.getBoolean("IS_PURCHASED"));
            return ticket;
        }
    }
}

