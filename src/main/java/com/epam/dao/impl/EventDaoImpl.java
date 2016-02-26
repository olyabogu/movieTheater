package com.epam.dao.impl;

import com.epam.dao.Dao;
import com.epam.dao.EventDao;
import com.epam.domain.Auditorium;
import com.epam.domain.Event;

import org.springframework.beans.factory.annotation.Autowired;
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
public class EventDaoImpl implements EventDao, Dao<Event> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);;
    }

    @Override
    public void create(Event event) {
        String sql = "INSERT INTO EVENT (NAME, RATING, BASEPRICE) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, event.getName(), event.getRating().toString(), event.getBasePrice());
    }

    @Override
    public void remove(Event event) {
        int id = event.getId();
        String sql  = "DELETE FROM EVENT WHERE ID=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Event getByName(String name) {
        String sql = "SELECT * FROM EVENT WHERE NAME = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new EventMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getAll() {
        String sql = "SELECT * FROM EVENT";
        return (List<Event>) jdbcTemplate.queryForObject(sql, new EventMapper());
    }

    @Override
    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {

    }

    private class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setId(rs.getInt("ID"));
            event.setName(rs.getString("NAME"));
            event.setRating(Event.Rating.valueOf(rs.getString("RATING")));
            event.setBasePrice(rs.getDouble("BASEPRICE"));
            return event;
        }
    }
}