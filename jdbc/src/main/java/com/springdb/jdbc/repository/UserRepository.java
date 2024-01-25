package com.springdb.jdbc.repository;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springdb.jdbc.domain.User;

public class UserRepository implements IUserRepository {
	private JdbcTemplate jdbcTemplate;

	public UserRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(String name, String phone) {
		String sql = "INSERT INTO users(name, phone) VALUES (?, ?)";
		jdbcTemplate.update(sql, name, phone);
		return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID() as id", (rs, rowNum) -> rs.getInt("id"));
	}

	@Override
	public User findByPhone(String phone) {
		String sql = "SELECT * FROM users WHERE phone=?";

		RowMapper<User> userRowMapper = (rs, rowNum) -> {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPhone(rs.getString("phone"));
			return user;
		};

		return jdbcTemplate.queryForObject(sql, userRowMapper, phone);
	}
}
