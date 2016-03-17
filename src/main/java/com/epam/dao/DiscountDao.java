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
	private static final String CREATE_USER_DISCOUNT_STATISTICS = "INSERT INTO USER_DISCOUNT(USER_ID, COUNTER) VALUES (?,?)";
	private static final String UPDATE_USER_DISCOUNT_STATISTICS = "UPDATE USER_DISCOUNT SET COUNTER = COUNTER + 1 WHERE USER_ID=?";
	private static final String UPDATE_DISCOUNT_STATISTICS = "UPDATE DISCOUNT_STATISTICS SET COUNTER = COUNTER + 1 WHERE DISCOUNT=?";
	private static final String GET_USER_DISCOUNT_STATISTICS = "SELECT COUNTER FROM DISCOUNT_STATISTICS WHERE DISCOUNT=?";
	private static final String GET_DISCOUNT_STATISTICS = "SELECT COUNTER FROM USER_DISCOUNT WHERE USER_ID=?";

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	public void createDiscountForUserStatistics(User user){
		jdbcTemplate.update(CREATE_USER_DISCOUNT_STATISTICS, user.getId(), 0);
	}

    public void updateDiscountForUserStatistics(User user){
        jdbcTemplate.update(UPDATE_USER_DISCOUNT_STATISTICS, user.getId());
    }

    public void updateDiscountStatistics(String discount) {
        jdbcTemplate.update(UPDATE_DISCOUNT_STATISTICS, discount);
    }

    public Integer getStatisticsForDiscount(String discount) {
        return jdbcTemplate.queryForObject(GET_DISCOUNT_STATISTICS, new Object[] { discount }, Integer.class);
    }

    public Integer getStatisticsForUser(User user) {
        return jdbcTemplate.queryForObject(GET_USER_DISCOUNT_STATISTICS, new Object[] { user.getId() }, Integer.class);
    }
}
