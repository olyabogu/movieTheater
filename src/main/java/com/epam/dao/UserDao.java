package com.epam.dao;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import com.epam.domain.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void create(final User user) {
      KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst =
                                con.prepareStatement(CREATE_USER, new String[]{"id"});
                        pst.setString(1, user.getUsername());
                        pst.setDate(2, new java.sql.Date(user.getBirthDate().getTime()));
                        pst.setString(3, String.join(", ", user.getRoles()));
                        pst.setString(4, user.getEmail());
                        pst.setString(5, user.getPassword());
                        pst.setInt(6, user.getAccount().getId());
                        return pst;
                    }
                },
                keyHolder);
        int id = keyHolder.getKey().intValue();
        user.setId(id);
    }

    public void update(User user) {
        jdbcTemplate.update(UPDATE_USER, user.getUsername(), user.getBirthDate(), String.join(", ", user.getRoles()), user.getEmail(), user.getId());
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

        private static final String ID = "USER_ID";
        private static final String NAME = "NAME";
        private static final String PASS = "PASSWORD";
        private static final String USER_ROLE = "USER_ROLE";
        private static final String BIRTH_DATE = "BIRTH_DATE";
        private static final String EMAIL = "EMAIL";

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt(ID));
            user.setName(rs.getString(NAME));
            user.setPassword(rs.getString(PASS));
            String userRoles = rs.getString(USER_ROLE);
            List<String> roles = Arrays.asList((userRoles.split(", ")));
            user.setRoles(roles);
            user.setBirthDate(rs.getDate(BIRTH_DATE));
            user.setEmail(rs.getString(EMAIL));

            UserAccountDao.UserAccountMapper mapper = new UserAccountDao.UserAccountMapper();
            UserAccount account = mapper.mapRow(rs, rowNum);
            user.setAccount(account);
            return user;
        }
    }

    private class TicketMapper implements RowMapper<Ticket> {

        private static final String TICKET_ID = "TICKET_ID";
        private static final String IS_PURCHASED = "IS_PURCHASED";

        @Override
        public Ticket mapRow(ResultSet rs, int i) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt(TICKET_ID));
            ticket.setPurchased(rs.getBoolean(IS_PURCHASED));
            return ticket;
        }
    }
}

