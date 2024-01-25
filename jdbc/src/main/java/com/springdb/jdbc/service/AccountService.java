package com.springdb.jdbc.service;

import org.springframework.transaction.annotation.Transactional;

import com.springdb.jdbc.domain.AccountRecord;
import com.springdb.jdbc.repository.IAccountRecordRepository;

public class AccountService implements IAccountService {
	private IAccountRecordRepository repository;

	public AccountService(IAccountRecordRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public void deposit(int userId, int amount) {
		AccountRecord record = AccountRecord.createDeposit(0, amount, userId);
		repository.save(record);
	}

	@Transactional
	@Override
	public AccountRecord withDrawl(int userId, int amount) {
		// TODO 사용자 밸리데이션 추가
		AccountRecord lastRecord = repository.findLastRecord(userId);
		// TODO 출금 시 잔금 확인 추가
		repository.save(AccountRecord.createWithDrawl(userId, amount, lastRecord));
		return repository.findLastRecord(userId);
	}

	@Transactional
	@Override
	public void transfer(int senderId, int receiverId, int amount) {
		AccountRecord senderRecord = repository.findLastRecord(senderId);
		AccountRecord receiverRecord = repository.findLastRecord(receiverId);

		repository.save(AccountRecord.createTransferSend(senderRecord, receiverId, amount));
		repository.save(AccountRecord.createTransferReceive(receiverRecord, receiverId, amount));
	}

	@Override
	public AccountRecord create(int userId) {
		return null;
	}

	@Override
	public int getAmount(int userId) {
		return repository.findLastRecord(userId).getBalance();
	}
}
