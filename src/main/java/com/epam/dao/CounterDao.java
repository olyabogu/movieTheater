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

	private static final String CREATE_COUNTER_BY_NAME = "INSERT INTO EVENT_BY_NAME (EVENT_ID, COUNTER) VALUES (?, ?)";
	private static final String UPDATE_COUNTER_BY_NAME = "UPDATE EVENT_BY_NAME SET COUNTER = COUNTER + 1 WHERE EVENT_ID=?";
	private static final String GET_COUNTER_BY_NAME = "SELECT COUNTER FROM EVENT_BY_NAME WHERE EVENT_ID=?";
	private static final String CREATE_COUNTER_BY_PRICE = "INSERT INTO EVENT_BY_PRICE (EVENT_ID, COUNTER) VALUES (?, ?)";
	private static final String UPDATE_COUNTER_BY_PRICE = "UPDATE EVENT_BY_PRICE SET COUNTER = COUNTER + 1 WHERE EVENT_ID=?";
	private static final String GET_COUNTER_BY_PRICE = "SELECT COUNTER FROM EVENT_BY_PRICE WHERE EVENT_ID=?";
	private static final String COUNT_BY_NAME = "SELECT COUNT(*) FROM EVENT_BY_NAME WHERE EVENT_ID=?";
	private static final String COUNT_BY_PRICE = "SELECT COUNT(*) FROM EVENT_BY_NAME WHERE EVENT_ID=?";

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createCounterByName(Event event) {
        jdbcTemplate.update(CREATE_COUNTER_BY_NAME, event.getId(), 0);
    }

    public void updateCounterByName(Event event) {
        jdbcTemplate.update(UPDATE_COUNTER_BY_NAME, event.getId());
    }

    public int getCounterByName(Event event) {
	    try {
		    return jdbcTemplate.queryForObject(GET_COUNTER_BY_NAME, new Object[]{event.getId()}, Integer.class);
	    } catch (EmptyResultDataAccessException e) {
		    return 0;
	    }
    }

    public void createCounterByPrice(Event event) {
        jdbcTemplate.update(CREATE_COUNTER_BY_PRICE, event.getId(), 0);
    }

    public void updateCounterByPrice(Event event) {
        jdbcTemplate.update(UPDATE_COUNTER_BY_PRICE, event.getId());
    }

    public int getCounterByPrice(Event event) {
	    try {
		    return jdbcTemplate.queryForObject(GET_COUNTER_BY_PRICE, new Object[]{event.getId()}, Integer.class);
	    } catch (EmptyResultDataAccessException e) {
		    return 0;
	    }
    }

	public Integer selectCountByName(Event event) {
		return jdbcTemplate.queryForObject(COUNT_BY_NAME, new Object[] { event.getId() }, Integer.class);
	}

	public Integer selectCountByPrice(Event event) {
		return jdbcTemplate.queryForObject(COUNT_BY_PRICE, new Object[] { event.getId() }, Integer.class);
	}
}
