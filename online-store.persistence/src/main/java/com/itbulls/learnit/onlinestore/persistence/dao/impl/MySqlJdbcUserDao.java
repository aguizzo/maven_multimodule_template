package com.itbulls.learnit.onlinestore.persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.dao.RoleDao;
import com.itbulls.learnit.onlinestore.persistence.dao.UserDao;
import com.itbulls.learnit.onlinestore.persistence.dto.UserDto;
import com.itbulls.learnit.onlinestore.persistence.utils.DBUtils;

public class MySqlJdbcUserDao implements UserDao {
	private final static String USER_TABLE = "user";
	// Table column names
	private final static String ID = "id";
	private final static String FIRST_NAME = "first_name";
	private final static String LAST_NAME = "last_name";
	private final static String EMAIL = "email";
	private final static String PASSWORD = "password";
	private final static String ROLE = "fk_user_role";
	private final static String MONEY = "money";
	private final static String CREDIT_CARD = "credit_card";
	private final static String PARTNER_CODE = "partner_code";
	private final static String REFERRER_USER_ID = "referrer_user_id";

	private RoleDao roleDao;

	public MySqlJdbcUserDao() {
		roleDao = new MySqlJdbcRoleDao();
	}

	@Override
	public boolean saveUser(UserDto user) {
		String query = "INSERT INTO " + USER_TABLE + "("
				+ FIRST_NAME + ","
				+ LAST_NAME + ","
				+ EMAIL + ","
				+ ROLE + ","
				+ MONEY	+ ","
				+ CREDIT_CARD + ","
				+ PASSWORD + ","
				+ PARTNER_CODE + ","
				+ REFERRER_USER_ID
				+ ") " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (var connection = DBUtils.getConnection();
				var ps = connection.prepareStatement(query);) {
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			if (user.getRoleDto() != null && user.getRoleDto().getId() != null)
				ps.setInt(4, user.getRoleDto().getId());
			else if (user.getRoleDto() != null && !user.getRoleDto().getRoleName().isEmpty())
				ps.setInt(4, roleDao.getRoleByRoleName(user.getRoleDto().getRoleName()).getId());
			else
				ps.setNull(4, java.sql.Types.NULL);
			ps.setBigDecimal(5, user.getMoney());
			ps.setString(6, user.getCreditCard());
			ps.setString(7, user.getPassword());
			ps.setString(8, user.getPartnerCode());
			if (user.getReferrerUser() != null) {
				ps.setInt(9, user.getReferrerUser().getId());
			}
			else {
				ps.setNull(9, java.sql.Types.NULL);
			}
			
			int result = ps.executeUpdate();
			return result != 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<UserDto> getUsers() {
		String query = "SELECT * FROM " + USER_TABLE;
		try (var connection = DBUtils.getConnection(); var ps = connection.prepareStatement(query);) {

			try (var rs = ps.executeQuery()) {
				List<UserDto> users = new ArrayList<UserDto>();
				while (rs.next())
					users.add(getUserDto(rs));
				return users;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDto getUserByEmail(String userEmail) {
		String query = "SELECT * FROM " + USER_TABLE +
				" WHERE " + EMAIL + " = ?";
		try (var connection = DBUtils.getConnection(); var ps = connection.prepareStatement(query);) {
			ps.setString(1, userEmail);

			try (var rs = ps.executeQuery()) {
				if (rs.next())
					return getUserDto(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDto getUserById(int id) {
		String query = "SELECT * FROM " + USER_TABLE +
				" WHERE "+ ID + " = ?";
		try (var connection = DBUtils.getConnection(); var ps = connection.prepareStatement(query);) {
			ps.setInt(1, id);

			try (var rs = ps.executeQuery()) {
				if (rs.next())
					return getUserDto(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDto getUserByPartnerCode(String partnerCode) {
		String query = "SELECT * FROM " + USER_TABLE +
				" WHERE " + PARTNER_CODE + " = ?";
		try (var connection = DBUtils.getConnection(); var ps = connection.prepareStatement(query);) {
			ps.setString(1, partnerCode);

			try (var rs = ps.executeQuery()) {
				if (rs.next())
					return getUserDto(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private UserDto getUserDto(ResultSet rs) throws SQLException {
		var userDto = new UserDto();
		
		userDto.setId(rs.getInt(ID));
		userDto.setFirstName(rs.getString(FIRST_NAME));
		userDto.setLastName(rs.getString(LAST_NAME));
		userDto.setEmail(rs.getString(EMAIL));
		userDto.setPassword(rs.getString(PASSWORD));
		userDto.setRoleDto(roleDao.getRoleById(rs.getInt(ROLE)));
		userDto.setMoney(rs.getBigDecimal(MONEY));
		userDto.setCreditCard(rs.getString(CREDIT_CARD));
		userDto.setPartnerCode(rs.getString(PARTNER_CODE));
		userDto.setReferrerUser(this.getUserById(rs.getInt(REFERRER_USER_ID)));
		
		return userDto;
	}


}
