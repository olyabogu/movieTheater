package com.epam.dao;

import com.epam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@Repository
public class DiscountDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	public void createDiscountForUserStatistics(User user){
		String sql = "INSERT INTO USER_DISCOUNT(USER_ID, COUNTER) VALUES (?,?)";
		jdbcTemplate.update(sql, user.getId(), 0);
	}

    public void updateDiscountForUserStatistics(User user){
        String sql = "UPDATE USER_DISCOUNT SET COUNTER = COUNTER + 1 WHERE USER_ID=?";
        jdbcTemplate.update(sql, user.getId());
    }

    public void updateDiscountStatistics(String discount) {
        String sql = "UPDATE DISCOUNT_STATISTICS SET COUNTER = COUNTER + 1 WHERE DISCOUNT=?";
        jdbcTemplate.update(sql, discount);
    }

    public Integer getStatisticsForDiscount(String discount) {
        String sql = "SELECT COUNTER FROM DISCOUNT_STATISTICS WHERE DISCOUNT=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { discount }, Integer.class);
    }

    public Integer getStatisticsForUser(User user) {
        String sql = "SELECT COUNTER FROM USER_DISCOUNT WHERE USER_ID=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class);
    }
}
