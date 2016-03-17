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
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private CounterDao counterDao;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Event event) {
        String sql = "INSERT INTO EVENT (NAME, RATING, BASE_PRICE) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, event.getName(), event.getRating().toString(), event.getBasePrice());
    }

	public void update(Event event) {
		String sql = "UPDATE EVENT SET NAME=?, RATING=?, BASE_PRICE=? WHERE ID = ?";
		jdbcTemplate.update(sql, event.getName(), event.getRating().toString(), event.getBasePrice(), event.getId());
	}

    public void remove(Event event) {
        int id = event.getId();
        String sql  = "DELETE FROM EVENT WHERE ID=?";
        jdbcTemplate.update(sql, id);
    }

    public Event getByName(String name) {
	    try {
		    String sql = "SELECT * FROM EVENT WHERE NAME = ?";
		    return jdbcTemplate.queryForObject(sql, new EventMapper(), name);
	    } catch (EmptyResultDataAccessException e) {
		    return null;
	    }
    }

    public List<Event> getAll() {
        String sql = "SELECT * FROM EVENT";
        return jdbcTemplate.query(sql, new EventMapper());
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
	    String sql = "INSERT INTO ASSIGNED_AUDITORIUM (EVENT_ID, AUDITORIUM, DATE) VALUES (?, ?, ?)";
	    jdbcTemplate.update(sql, event.getId(), auditorium.getName(), date);
    }

	public String getAuditoriumForEvent(Event event, Date date) {
		try {
			String sql = "SELECT AUDITORIUM FROM ASSIGNED_AUDITORIUM WHERE EVENT_ID = ? AND DATE = ?";
			return jdbcTemplate.queryForObject(sql, String.class, event.getId(), date);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

    private static class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setId(rs.getInt("ID"));
            event.setName(rs.getString("NAME"));
            event.setRating(Event.Rating.valueOf(rs.getString("RATING")));
            event.setBasePrice(rs.getDouble("BASE_PRICE"));
            return event;
        }
    }
}