package com.epam.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.domain.UserAccount;

/**
 * @author Olga_Bogutska.
 */
public class UserAccountMapper implements RowMapper<UserAccount> {

	public static final String ID = "ACCOUNT_ID";
	public static final String AMOUNT = "AMOUNT";
	public static final String CURRENCY = "CURRENCY";

	@Override
	public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserAccount account = new UserAccount();
		account.setId(rs.getInt(ID));
		account.setAmount(rs.getDouble(AMOUNT));
		account.setCurrency(rs.getString(CURRENCY));
		return account;
	}
}