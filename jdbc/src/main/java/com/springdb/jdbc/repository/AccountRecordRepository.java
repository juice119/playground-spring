package com.springdb.jdbc.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springdb.jdbc.domain.AccountRecord;

public class AccountRecordRepository implements IAccountRecordRepository {
	private JdbcTemplate jdbcTemplate;

	public AccountRecordRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int findLastAmount(int userId) {
		String sql = "SELECT balance FROM account_records ar WHERE user_id=? ORDER BY id DESC LIMIT 1";

		RowMapper<Integer> balanceMapper = (rs, rowNum) -> {
			return rs.getInt("balance");
		};

		List<Integer> query = jdbcTemplate.query(sql, balanceMapper, userId);
		return query.isEmpty() ? 0 : query.get(0);
	}

	@Override
	public void save(AccountRecord accountRecord) {
		String sql = "INSERT INTO account_records(balance, change_amount, user_id, operation_type) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, accountRecord.getBalance(), accountRecord.getChangeAmount(), accountRecord.getUserId(),
			accountRecord.getOperationType());
	}
}
