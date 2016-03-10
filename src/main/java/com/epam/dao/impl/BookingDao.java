package com.epam.dao.impl;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@Repository
public class BookingDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void bookTicket(User user, Ticket ticket) {
        String sql = "INSERT INTO USER_TICKET_MP (USER_ID, TICKET_ID) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getId(), ticket.getId());
    }
}
