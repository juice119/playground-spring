package com.springdb.jdbc.domain;

import lombok.Data;

@Data
public class AccountRecord {
	private int id;
	private int balance;
	private int changeAmount;
	private int userId;
	private OperationType operationType;

	public static AccountRecord createWithDrawl(int userId, int amount, AccountRecord lastRecord) {
		AccountRecord accountRecord = new AccountRecord();
		accountRecord.setUserId(userId);
		accountRecord.setChangeAmount(amount);
		accountRecord.setBalance(lastRecord.balance - amount);
		accountRecord.setOperationType(OperationType.WITHDRAWAL);
		return accountRecord;
	}

	public String getOperationType() {
		return operationType.name();
	}

	public static AccountRecord createDeposit(int balance, int changeAmount, int userId) {
		AccountRecord accountRecord = new AccountRecord();
		accountRecord.balance = balance + changeAmount;
		accountRecord.changeAmount = changeAmount;
		accountRecord.userId = userId;
		accountRecord.operationType = OperationType.DEPOSIT;
		return accountRecord;
	}

	enum OperationType {
		DEPOSIT,
		WITHDRAWAL,
		TRANSFER_SENDER,
		TRANSFER_RECEIVER
	}
}
