package com.springdb.jdbc.repository;

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
	public AccountRecord findLastRecord(int userId) {
		String sql = "SELECT ar.* FROM account_records ar WHERE ar.user_id=? ORDER BY ar.id DESC LIMIT 1";

		RowMapper<AccountRecord> rowMapper = (rs, rowNum) -> {
			AccountRecord accountRecord = new AccountRecord();

			accountRecord.setId(rs.getInt("id"));
			accountRecord.setBalance(rs.getInt("balance"));
			accountRecord.setChangeAmount(rs.getInt("change_amount"));
			accountRecord.setUserId(rs.getInt("user_id"));

			return accountRecord;
		};

		return jdbcTemplate.queryForObject(sql, rowMapper, userId);
	}

	@Override
	public void save(AccountRecord accountRecord) {
		String sql = "INSERT INTO account_records(balance, change_amount, user_id, operation_type) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, accountRecord.getBalance(), accountRecord.getChangeAmount(), accountRecord.getUserId(),
			accountRecord.getOperationType());
	}
}
