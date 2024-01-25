package com.springdb.jdbc.domain;

import lombok.Data;

@Data
public class AccountRecord {
	private int id;
	private int balance;
	private int changeAmount;
	private int userId;
	private OperationType operationType;
	private int transferUserId;

	public static AccountRecord createWithDrawl(int userId, int amount, AccountRecord lastRecord) {
		AccountRecord accountRecord = new AccountRecord();
		accountRecord.setUserId(userId);
		accountRecord.setChangeAmount(amount);
		accountRecord.setBalance(lastRecord.balance - amount);
		accountRecord.setOperationType(OperationType.WITHDRAWAL);
		return accountRecord;
	}

	public static AccountRecord createTransferSend(AccountRecord senderRecord, int receiverId, int amount) {
		AccountRecord accountRecord = new AccountRecord();
		accountRecord.setUserId(senderRecord.getUserId());
		accountRecord.setChangeAmount(amount);
		accountRecord.setBalance(senderRecord.balance - amount);
		accountRecord.setTransferUserId(receiverId);
		accountRecord.setOperationType(OperationType.TRANSFER_SEND);
		return accountRecord;
	}

	public static AccountRecord createTransferReceive(AccountRecord receiveRecord, int senderId, int amount) {
		AccountRecord accountRecord = new AccountRecord();
		accountRecord.setUserId(receiveRecord.getUserId());
		accountRecord.setChangeAmount(amount);
		accountRecord.setBalance(receiveRecord.balance + amount);
		accountRecord.setTransferUserId(senderId);
		accountRecord.setOperationType(OperationType.TRANSFER_RECEIVE);
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
		TRANSFER_SEND,
		TRANSFER_RECEIVE
	}
}
