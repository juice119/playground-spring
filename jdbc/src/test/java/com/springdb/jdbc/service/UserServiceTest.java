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
import com.springdb.jdbc.repository.UserRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootTest
class UserServiceTest {
	@Autowired
	private IUserService userService;

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
	@DisplayName("회원 생성")
	void create() {
		// given
		String name = "mr.robot";
		String phone = "01012341234";

		// when
		userService.create(name, phone);

		//  then
		User createdUser = userService.findByPhone(phone);
		assertThat(createdUser.getName()).isEqualTo(name);
		assertThat(createdUser.getPhone()).isEqualTo(phone);
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
	}

}
