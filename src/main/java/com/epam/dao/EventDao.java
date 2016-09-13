package com.epam.dao;

import com.epam.domain.Auditorium;
import com.epam.domain.Event;
import com.epam.domain.Rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
@Repository
public class EventDao {
	private static final String ID = "EVENT_ID";
	private static final String NAME = "NAME";
	private static final String RATING = "RATING";
	private static final String BASE_PRICE = "BASE_PRICE";

	private static final String CREATE_EVENT = "INSERT INTO EVENT (NAME, RATING, BASE_PRICE) VALUES (?, ?, ?)";
	private static final String UPDATE_EVENT = "UPDATE EVENT SET NAME=?, RATING=?, BASE_PRICE=? WHERE EVENT_ID = ?";
	private static final String ADD_EVENT_DATE = "INSERT INTO EVENT_DATES VALUES (?, ?);";
	private static final String DELETE_EVENT = "DELETE FROM EVENT WHERE EVENT_ID=?";
	private static final String GET_BY_NAME = "SELECT * FROM EVENT JOIN EVENT_DATES ON EVENT.EVENT_ID=EVENT_DATES.EVENT_ID WHERE EVENT.NAME = ?";
	private static final String GET_ALL_EVENTS = "SELECT * FROM EVENT JOIN EVENT_DATES ON EVENT.EVENT_ID=EVENT_DATES.EVENT_ID ORDER BY EVENT.EVENT_ID;";
	private static final String ASSIGN_AUDITORIUM = "INSERT INTO ASSIGNED_AUDITORIUM (EVENT_ID, AUDITORIUM, DATE) VALUES (?, ?, ?)";
	private static final String GET_AUDITORIUM_FOR_EVENT = "SELECT AUDITORIUM FROM ASSIGNED_AUDITORIUM WHERE EVENT_ID = ? AND DATE = ?";
	private static final String EVENT_BY_ID = "SELECT * FROM EVENT JOIN EVENT_DATES ON EVENT.EVENT_ID=EVENT_DATES.EVENT_ID WHERE EVENT.EVENT_ID = ?";

	public final static RowMapper<Event> EVENT_ROW_MAPPER = new EventMapper();
	public final static RowMapper<Date> DATE_ROW_MAPPER = new DateMapper();

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(final Event event) {
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    jdbcTemplate.update(
			    new PreparedStatementCreator() {
				    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					    PreparedStatement pst =
							    con.prepareStatement(CREATE_EVENT, new String[]{"id"});
					    pst.setString(1, event.getName());
					    pst.setString(2, event.getRating().name());
					    pst.setDouble(3, event.getBasePrice());
					    return pst;
				    }
			    },
			    keyHolder);
	    int id = keyHolder.getKey().intValue();
	    for (Date date: event.getDates()){
		    jdbcTemplate.update(ADD_EVENT_DATE, id, date);
	    }
    }

	public void update(Event event) {
		jdbcTemplate.update(UPDATE_EVENT, event.getName(), event.getRating().name(), event.getBasePrice(), event.getId());
	}

    public void remove(Event event) {
        int id = event.getId();
        jdbcTemplate.update(DELETE_EVENT, id);
    }

    public Event getByName(String name) {
	    return jdbcTemplate.query(GET_BY_NAME,
			    new ResultSetExtractor<Event>() {
				    public Event extractData(ResultSet rs) throws SQLException, DataAccessException {
					    Event event = null;
					    int row = 0;
					    while (rs.next()) {
						    if (event == null) {
							    event = EVENT_ROW_MAPPER.mapRow(rs, row);
						    }
						    event.addDate(DATE_ROW_MAPPER.mapRow(rs, row));
						    row++;
					    }
					    return event;
				    }

			    }, name);
    }

	public List<Event> getAll() {
		return jdbcTemplate.query(GET_ALL_EVENTS,
				new ResultSetExtractor<List<Event>>() {
					public List<Event> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Event> events = new ArrayList<>();
						Long eventId = null;
						Event currentEvent = null;
						int eventIdx = 0;
						int itemIdx = 0;
						while (rs.next()) {
							// first row or when event changes
							if (currentEvent == null || !eventId.equals(rs.getLong(ID))) {
								eventId = rs.getLong(ID);
								currentEvent = EVENT_ROW_MAPPER.mapRow(rs, eventIdx++);
								itemIdx = 0;
								events.add(currentEvent);
							}
							currentEvent.addDate(DATE_ROW_MAPPER.mapRow(rs, itemIdx++));
						}
						return events;
					}

				});
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

	public Event getById(int eventId) {
		return jdbcTemplate.query(EVENT_BY_ID,
				new ResultSetExtractor<Event>() {
					public Event extractData(ResultSet rs) throws SQLException, DataAccessException {
						Event event = null;
						int row = 0;
						while (rs.next()) {
							if (event == null) {
								event = EVENT_ROW_MAPPER.mapRow(rs, row);
							}
							event.addDate(DATE_ROW_MAPPER.mapRow(rs, row));
							row++;
						}
						return event;
					}

				}, eventId);
	}

	private static class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setId(rs.getInt(ID));
            event.setName(rs.getString(NAME));
            event.setRating(Rating.valueOf(rs.getString(RATING)));
            event.setBasePrice(rs.getDouble(BASE_PRICE));
            return event;
        }
    }

	private static class DateMapper implements RowMapper<Date> {
		@Override
		public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getTimestamp("DATE");
		}
	}
}