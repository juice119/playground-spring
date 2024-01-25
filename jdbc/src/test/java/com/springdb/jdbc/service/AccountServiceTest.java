package com.springdb.jdbc.service;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.springdb.jdbc.domain.User;
import com.springdb.jdbc.repository.AccountRecordRepository;
import com.springdb.jdbc.repository.IAccountRecordRepository;
import com.springdb.jdbc.repository.UserRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootTest
class AccountServiceTest {
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAccountRecordRepository accountRecordRepository;
	@Autowired
	private DataSource dataSource;

	@BeforeEach
	void clearDB() throws SQLException {
		Connection connection = dataSource.getConnection();
		connection.prepareStatement("DELETE FROM users").execute();
		connection.prepareStatement("DELETE FROM account_records").execute();
		connection.close();
	}

	@Test
	@DisplayName("사용자는 입금 할 수 있다")
	void deposit() {
		// given
		User user = userService.create("test", "01011111111");

		// when
		int depositMoney = 1_000;
		accountService.deposit(user.getId(), depositMoney);

		// then
		assertThat(accountService.getAmount(user.getId())).isEqualTo(depositMoney);
	}

	@Test
	@DisplayName("사용자는 출금 할 수 있다")
	void withdrawal() {

	}

	@Test
	@DisplayName("사용자간 이체 할 수 있다")
	void transfer() {
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		DataSource dataSource() {
			HikariConfig hikariConfig = new HikariConfig();
			hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
			hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC&characterEncoding=UTF-8");
			hikariConfig.setUsername("test");
			hikariConfig.setPassword("1234");
			hikariConfig.setPoolName("TestHikariPool");
			return new HikariDataSource(hikariConfig);
		}

		@Bean
		IUserService userService() {
			return new UserService(new UserRepository(dataSource()));
		}

		@Bean
		IAccountRecordRepository accountRecordRepository() {
			return new AccountRecordRepository(dataSource());
		}

		@Bean
		IAccountService accountService() {
			return new AccountService(accountRecordRepository());
		}
	}
}
