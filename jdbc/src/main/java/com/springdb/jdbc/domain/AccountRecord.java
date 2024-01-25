package com.springdb.jdbc.domain;

import java.util.Optional;

import lombok.Data;

@Data
public class AccountRecord {
	private int id;
	private int balance;
	private int changeAmount;
	private OperationType operationType;
	private Optional<Integer> source_user_id = null;

	enum OperationType {
		DEPOSIT,
		WITHDRAWAL,
		TRANSFER_SENDER,
		TRANSFER_RECEIVER
	}
}
