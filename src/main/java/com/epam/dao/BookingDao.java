package com.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epam.domain.Ticket;
import com.epam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@Repository
public class BookingDao {
	private static final String CREATE_TICKET = "INSERT INTO TICKET (PRICE) VALUES (?)";
	private static final String BOOK_TICKET = "INSERT INTO USER_TICKET_MP (USER_ID, TICKET_ID) VALUES (?, ?)";

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void bookTicket(User user, Ticket ticket) {
        jdbcTemplate.update(BOOK_TICKET, user.getId(), ticket.getId());
    }

	public int create(final Ticket ticket) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst =
								con.prepareStatement(CREATE_TICKET, new String[]{"id"});
						pst.setDouble(1, ticket.getPrice());
						return pst;
					}
				},
				keyHolder);
		return keyHolder.getKey().intValue();
	}
}
