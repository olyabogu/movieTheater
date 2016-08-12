package com.epam.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.epam.domain.User;
import com.epam.domain.UserAccount;

/**
 * @author Olga_Bogutska.
 */
public class UserMapper implements RowMapper<User> {

	private static final String ID = "USER_ID";
	private static final String NAME = "NAME";
	private static final String PASSWORD = "PASSWORD";
	private static final String USER_ROLE = "USER_ROLE";
	private static final String BIRTH_DATE = "BIRTH_DATE";
	private static final String EMAIL = "EMAIL";

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(ID));
		user.setName(rs.getString(NAME));
		user.setPassword(rs.getString(PASSWORD));
		String userRoles = rs.getString(USER_ROLE);
		Set<String> roles = new HashSet<>(Arrays.asList((userRoles.split(" , "))));
		user.setRoles(roles);
		user.setBirthDate(rs.getDate(BIRTH_DATE));
		user.setEmail(rs.getString(EMAIL));

		UserAccountMapper mapper = new UserAccountMapper();
		UserAccount account = mapper.mapRow(rs, rowNum);
		user.setAccount(account);
		return user;
	}
}
