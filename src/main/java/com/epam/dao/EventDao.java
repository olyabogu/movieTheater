package com.epam.dao;

import com.epam.domain.Auditorium;
import com.epam.domain.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Repository
public class EventDao {
	private static final String ID = "ID";
	private static final String NAME = "NAME";
	private static final String RATING = "RATING";
	private static final String BASE_PRICE = "BASE_PRICE";

	private static final String CREATE_EVENT = "INSERT INTO EVENT (NAME, RATING, BASE_PRICE) VALUES (?, ?, ?)";
	private static final String UPDATE_EVENT = "UPDATE EVENT SET NAME=?, RATING=?, BASE_PRICE=? WHERE ID = ?";
	private static final String DELETE_EVENT = "DELETE FROM EVENT WHERE ID=?";
	private static final String GET_BY_NAME = "SELECT * FROM EVENT WHERE NAME = ?";
	private static final String GET_ALL_EVENTS = "SELECT * FROM EVENT";
	private static final String ASSIGN_AUDITORIUM = "INSERT INTO ASSIGNED_AUDITORIUM (EVENT_ID, AUDITORIUM, DATE) VALUES (?, ?, ?)";
	private static final String GET_AUDITORIUM_FOR_EVENT = "SELECT AUDITORIUM FROM ASSIGNED_AUDITORIUM WHERE EVENT_ID = ? AND DATE = ?";

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Event event) {
        jdbcTemplate.update(CREATE_EVENT, event.getName(), event.getRating().toString(), event.getBasePrice());
    }

	public void update(Event event) {
		jdbcTemplate.update(UPDATE_EVENT, event.getName(), event.getRating().toString(), event.getBasePrice(), event.getId());
	}

    public void remove(Event event) {
        int id = event.getId();
        jdbcTemplate.update(DELETE_EVENT, id);
    }

    public Event getByName(String name) {
	    try {
		    return jdbcTemplate.queryForObject(GET_BY_NAME, new EventMapper(), name);
	    } catch (EmptyResultDataAccessException e) {
		    return null;
	    }
    }

    public List<Event> getAll() {
        return jdbcTemplate.query(GET_ALL_EVENTS, new EventMapper());
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
	    jdbcTemplate.update(ASSIGN_AUDITORIUM, event.getId(), auditorium.getName(), date);
    }

	public String getAuditoriumForEvent(Event event, Date date) {
		try {
			return jdbcTemplate.queryForObject(GET_AUDITORIUM_FOR_EVENT, String.class, event.getId(), date);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

    private static class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setId(rs.getInt(ID));
            event.setName(rs.getString(NAME));
            event.setRating(Event.Rating.valueOf(rs.getString(RATING)));
            event.setBasePrice(rs.getDouble(BASE_PRICE));
            return event;
        }
    }
}