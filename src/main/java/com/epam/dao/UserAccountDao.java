package com.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.domain.UserAccount;

/**
 * @author Olga_Bogutska.
 */
@Repository
public class UserAccountDao {

	private static final String CREATE_USER_ACCOUNT = "INSERT INTO USER_ACCOUNT (AMOUNT, CURRENCY) VALUES (?, ?)";
	private static final String UPDATE_USER_ACCOUNT = "UPDATE USER_ACCOUNT SET AMOUNT=?, CURRENCY=? WHERE ACCOUNT_ID = ?";
	private static final String DELETE_USER_ACCOUNT = "DELETE FROM USER_ACCOUNT WHERE ACCOUNT_ID=?";
	private static final String USER_ACCOUNT_BY_ID = "SELECT * FROM USER_ACCOUNT WHERE ACCOUNT_ID = ?";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int create(final UserAccount account) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst =
								con.prepareStatement(CREATE_USER_ACCOUNT, new String[]{"id"});
						pst.setDouble(1, account.getAmount());
						pst.setString(2, account.getCurrency());
						return pst;
					}
				},
				keyHolder);
		int id = keyHolder.getKey().intValue();
		account.setId(id);
		return id;
	}

	public void update(UserAccount account) {
		jdbcTemplate.update(UPDATE_USER_ACCOUNT, account.getAmount(), account.getCurrency(), account.getId());
	}

	public void remove(int id) {
		jdbcTemplate.update(DELETE_USER_ACCOUNT, id);
	}

	public UserAccount getById(int id) {
		try {
			return jdbcTemplate.queryForObject(USER_ACCOUNT_BY_ID, new UserAccountMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected static class UserAccountMapper implements RowMapper<UserAccount> {

		private static final String ID = "ACCOUNT_ID";
		private static final String AMOUNT = "AMOUNT";
		private static final String CURRENCY = "CURRENCY";

		@Override
		public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserAccount account = new UserAccount();
			account.setId(rs.getInt(ID));
			account.setAmount(rs.getDouble(AMOUNT));
			account.setCurrency(rs.getString(CURRENCY));
			return account;
		}
	}
}
