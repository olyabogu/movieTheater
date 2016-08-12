package com.epam.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.domain.Ticket;

/**
 * @author Olga_Bogutska.
 */
public class TicketMapper implements RowMapper<Ticket> {

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