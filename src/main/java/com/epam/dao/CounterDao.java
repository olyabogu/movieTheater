package com.epam.dao;

import com.epam.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@Repository
public class CounterDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createCounterByName(Event event) {
        String sql = "INSERT INTO EVENT_BY_NAME (EVENT_ID, COUNTER) VALUES (?, ?)";
        jdbcTemplate.update(sql, event.getId(), 0);
    }

    public void updateCounterByName(Event event) {
        String sql = "UPDATE EVENT_BY_NAME SET COUNTER = COUNTER + 1 WHERE EVENT_ID=?";
        jdbcTemplate.update(sql, event.getId());
    }

    public int getCounterByName(Event event) {
	    try {
		    String sql = "SELECT COUNTER FROM EVENT_BY_NAME WHERE EVENT_ID=?";
		    return jdbcTemplate.queryForObject(sql, new Object[]{event.getId()}, Integer.class);
	    } catch (EmptyResultDataAccessException e) {
		    return 0;
	    }
    }

    public void createCounterByPrice(Event event) {
        String sql = "INSERT INTO EVENT_BY_PRICE (EVENT_ID, COUNTER) VALUES (?, ?)";
        jdbcTemplate.update(sql, event.getId(), 0);
    }

    public void updateCounterByPrice(Event event) {
        String sql = "UPDATE EVENT_BY_PRICE SET COUNTER = COUNTER + 1 WHERE EVENT_ID=?";
        jdbcTemplate.update(sql, event.getId());
    }

    public int getCounterByPrice(Event event) {
	    try {
		    String sql = "SELECT COUNTER FROM EVENT_BY_PRICE WHERE EVENT_ID=?";
		    return jdbcTemplate.queryForObject(sql, new Object[]{event.getId()}, Integer.class);
	    } catch (EmptyResultDataAccessException e) {
		    return 0;
	    }
    }

	public Integer selectCountByName(Event event) {
		String sql = "SELECT COUNT(*) FROM EVENT_BY_NAME WHERE EVENT_ID=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { event.getId() }, Integer.class);
	}

	public Integer selectCountByPrice(Event event) {
		String sql = "SELECT COUNT(*) FROM EVENT_BY_NAME WHERE EVENT_ID=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { event.getId() }, Integer.class);
	}
}
